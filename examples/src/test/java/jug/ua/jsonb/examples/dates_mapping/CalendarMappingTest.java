package jug.ua.jsonb.examples.dates_mapping;

import org.junit.Ignore;
import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CalendarMappingTest {

    Jsonb jsonb = JsonbBuilder.create();

    @Ignore("Gson has diff default format")
    @Test
    public void dateToJsonTest() throws Exception {
        //java.util.Date
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date parsedDate = sdf.parse("04.03.2015");

        //marshal to ISO format
        assertEquals("\"2015-03-04T00:00:00\"", jsonb.toJson(parsedDate));
    }

    @Ignore("Gson has diff default representation of Calendar")
    @Test
    public void calendarToJsonTest() throws Exception {
        //java.util.Calendar
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.clear();
        dateCalendar.set(2015, 3, 3);

        //marshal to ISO_DATE
        assertEquals("\"2015-04-03\"", jsonb.toJson(dateCalendar));

        //marshal to ISO_DATE_TIME
        Calendar dateTimeCalendar = new Calendar.Builder().setDate(2015, 3, 3).build();
        assertEquals("\"2015-04-03T00:00:00\"", jsonb.toJson(dateCalendar));
    }

    @Ignore("Gson has diff default representation of Calendar")
    @Test
    public void gregorianCalendarToJsonTest() throws Exception {
        //java.util.GregorianCalendar
        Calendar dateGregorianCalendar = GregorianCalendar.getInstance();
        dateGregorianCalendar.clear();
        dateGregorianCalendar.set(2015, 3, 3);

        //marshal to ISO_DATE
        assertEquals("\"2015-04-03\"", jsonb.toJson(dateGregorianCalendar));

        //marshal to ISO_DATE_TIME
        Calendar dateTimeGregorianCalendar = new Calendar.Builder().setDate(2015, 3, 3).build();
        assertEquals("\"2015-04-03T00:00:00\"", jsonb.toJson(dateTimeGregorianCalendar));
    }

    @Ignore("Gson doesn't have specific handling of TimeZone class")
    @Test
    public void timeZoneToJsonTest() throws Exception {
        //java.util.TimeZone
        assertEquals("\"Europe/Prague\"", jsonb.toJson(TimeZone.getTimeZone("Europe/Prague")));

        //java.util.SimpleTimeZone
        assertEquals("\"Europe/Prague\"", jsonb.toJson(SimpleTimeZone.getTimeZone("Europe/Prague")));
    }

    @Ignore("All new Data/Time classes should be marshalled as toString()")
    @Test
    public void toJsonTest() throws ParseException {
        //java.time.Instant
        assertEquals("\"2015-03-03T23:00:00Z\"", jsonb.toJson(Instant.parse("2015-03-03T23:00:00Z")));

        //java.time.Duration
        assertEquals("\"PT5H4M\"", jsonb.toJson(Duration.ofHours(5).plusMinutes(4)));

        //java.time.Period
        assertEquals("\"P10Y\"", jsonb.toJson(Period.between(LocalDate.of(1960, Month.JANUARY, 1), LocalDate.of(1970, Month.JANUARY, 1))));

        //java.time.LocalDate ISO_LOCAL_DATE
        assertEquals("\"2013-08-10\"", jsonb.toJson(LocalDate.of(2013, Month.AUGUST, 10)));

        //java.time.LocalTime ISO_LOCAL_TIME
        assertEquals("\"22:33:00\"", jsonb.toJson(LocalTime.of(22, 33)));

        //java.time.LocalDateTime ISO_LOCAL_DATE_TIME
        assertEquals("\"2015-02-16T13:21:00\"", jsonb.toJson(LocalDateTime.of(2015, 2, 16, 13, 21)));

        //java.time.ZonedDateTime ISO_ZONED_DATE_TIME
        assertEquals("\"2015-02-16T13:21:00+01:00[Europe/Prague]\"",
                jsonb.toJson(ZonedDateTime.of(2015, 2, 16, 13, 21, 0, 0, ZoneId.of("Europe/Prague"))));

        //java.time.ZoneId
        assertEquals("\"Europe/Prague\"", jsonb.toJson(ZoneId.of("Europe/Prague")));

        //java.time.ZoneOffset XXX
        assertEquals("\"+02:00\"", jsonb.toJson(ZoneOffset.of("+02:00")));

        //java.time.OffsetDateTime ISO_OFFSET_DATE_TIME
        assertEquals("\"2015-02-16T13:21:00+02:00\"",
                jsonb.toJson(OffsetDateTime.of(2015, 2, 16, 13, 21, 0, 0, ZoneOffset.of("+02:00"))));

        //java.time.OffsetTime
        assertEquals("\"13:21:15.000000016+02:00\"", jsonb.toJson(OffsetTime.of(13, 21, 15, 16, ZoneOffset.of("+02:00"))));
    }

    @Ignore("Gson doesn't support unmarshalling of Date/Time classes from these defaults values")
    @Test
    public void fromJsonToDataAndTimeTypes() throws Exception {
        //java.util.Date
        Date date = jsonb.fromJson("\"2015-03-04T00:00:00\"", Date.class);
        assertNotNull(date);

        //java.util.Calendar
        Calendar dateCalendar = jsonb.fromJson("\"2015-04-03\"", Calendar.class);
        assertNotNull(dateCalendar);

        Calendar dateTimeCalendar = jsonb.fromJson("\"2015-04-03T00:00:00\"", Calendar.class);
        assertNotNull(dateTimeCalendar);

        //java.util.GregorianCalendar
        GregorianCalendar gregorianCalendar = jsonb.fromJson("\"2015-04-03T00:00:00\"", GregorianCalendar.class);
        assertNotNull(gregorianCalendar);

        //java.util.TimeZone
        TimeZone timeZone = jsonb.fromJson("\"Europe/Prague\"", TimeZone.class);
        assertNotNull(timeZone);

        //java.util.SimpleTimeZone
        SimpleTimeZone simpleTimeZone = jsonb.fromJson("\"Europe/Prague\"", SimpleTimeZone.class);
        assertNotNull(simpleTimeZone);

        //java.time.Instant
        Instant instant = jsonb.fromJson("\"2015-03-03T23:00:00Z\"", Instant.class);
        assertNotNull(instant);

        //java.time.Duration
        Duration duration = jsonb.fromJson("\"PT5H4M\"", Duration.class);
        assertNotNull(duration);

        //java.time.Period
        Period period = jsonb.fromJson("\"P10Y\"", Period.class);
        assertNotNull(period);

        //java.time.LocalDate
        LocalDate localDate = jsonb.fromJson("\"2013-08-10\"", LocalDate.class);
        assertNotNull(localDate);

        //java.time.LocalTime
        LocalTime localTime = jsonb.fromJson("\"22:33:00\"", LocalTime.class);
        assertNotNull(localTime);

        //java.time.LocalDateTime
        LocalDateTime localDateTime = jsonb.fromJson("\"2015-02-16T13:21:00\"", LocalDateTime.class);
        assertNotNull(localDateTime);

        //java.time.ZonedDateTime
        ZonedDateTime zonedDateTime = jsonb.fromJson("\"2015-02-16T13:21:00+01:00[Europe/Prague]\"", ZonedDateTime.class);
        assertNotNull(zonedDateTime);

        //java.time.ZoneId
        ZoneId zoneId = jsonb.fromJson("\"Europe/Prague\"", ZoneId.class);
        assertNotNull(zoneId);

        //java.time.ZoneOffset
        ZoneOffset zoneOffset = jsonb.fromJson("\"+02:00\"", ZoneOffset.class);
        assertNotNull(zoneOffset);

        //java.time.OffsetDateTime
        OffsetDateTime offsetDateTime = jsonb.fromJson("\"2015-02-16T13:21:00+02:00\"", OffsetDateTime.class);
        assertNotNull(offsetDateTime);

        //java.time.OffsetTime
        OffsetTime offsetTime = jsonb.fromJson("\"13:21:15.000000016+02:00\"", OffsetTime.class);
        assertNotNull(offsetTime);
    }
}
