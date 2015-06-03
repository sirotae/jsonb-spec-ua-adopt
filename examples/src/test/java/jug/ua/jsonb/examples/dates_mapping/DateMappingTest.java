package jug.ua.jsonb.examples.dates_mapping;

import org.junit.Ignore;
import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
Tests for mapping of dates classes from java.util.*
 */
public class DateMappingTest {

    Jsonb jsonb = JsonbBuilder.create();

    @Test
    public void dateToJsonTest() throws Exception {
        //marshal to ISO format

        //java.util.Date without time
        Calendar dateCalendar = new Calendar.Builder().setDate(2015, 3, 3).build();
        assertEquals("\"2015-04-03\"", jsonb.toJson(dateCalendar.getTime()));

        //java.util.Date with time
        Calendar datetimeCalendar = new Calendar.Builder().setDate(2015, 3, 3).setTimeOfDay(14, 30, 0).build();
        assertEquals("\"2015-04-03T14:30:00.000Z\"", jsonb.toJson(datetimeCalendar.getTime()));
    }


    @Test
    public void calendarToJsonTest() throws Exception {
        //java.util.Date without time
        Calendar dateCalendar = new Calendar.Builder().setDate(2015, 3, 3).build();
        assertEquals("\"2015-04-03\"", jsonb.toJson(dateCalendar));

        //java.util.Date with time
        Calendar datetimeCalendar = new Calendar.Builder().setDate(2015, 3, 3).setTimeOfDay(14, 30, 0).build();
        assertEquals("\"2015-04-03T14:30:00.000Z\"", jsonb.toJson(datetimeCalendar));
    }

    @Test
    public void gregorianCalendarToJsonTest() throws Exception {

        Calendar dateCalendar = new Calendar.Builder()
                .setCalendarType("gregory")
                .setDate(2015, 3, 3)
                .build();
        assertEquals("\"2015-04-03\"", jsonb.toJson(dateCalendar));

        //java.util.Date with time
        Calendar datetimeCalendar = new Calendar.Builder()
                .setCalendarType("gregory")
                .setDate(2015, 3, 3)
                .setTimeOfDay(14, 30, 0).build();
        assertEquals("\"2015-04-03T14:30:00.000Z\"", jsonb.toJson(datetimeCalendar));
    }

    @Test
    public void fromJsonToDateTypes() throws Exception {
        //java.util.Date
        Date date = jsonb.fromJson("\"2015-03-04\"", Date.class);
        assertNotNull(date);

        Date dateTime = jsonb.fromJson("\"2015-03-04T00:00:00.000Z\"", Date.class);
        assertNotNull(date);

        //java.util.Calendar
        Calendar dateCalendar = jsonb.fromJson("\"2015-04-03\"", Calendar.class);
        assertNotNull(dateCalendar);

        Calendar dateTimeCalendar = jsonb.fromJson("\"2015-04-03T00:00:00\"", Calendar.class);
        assertNotNull(dateTimeCalendar);

        //java.util.GregorianCalendar
        GregorianCalendar gregorianCalendar = jsonb.fromJson("\"2015-04-03T00:00:00\"", GregorianCalendar.class);
        assertNotNull(gregorianCalendar);
    }

    @Ignore("Gson doesn't have specific handling of TimeZone class")
    @Test
    public void timeZoneToJsonTest() throws Exception {
        //java.util.TimeZone
        assertEquals("\"Europe/Prague\"", jsonb.toJson(TimeZone.getTimeZone("Europe/Prague")));

        //java.util.SimpleTimeZone
        assertEquals("\"Europe/Prague\"", jsonb.toJson(SimpleTimeZone.getTimeZone("Europe/Prague")));
    }

    @Ignore("Gson doesn't support specific unmarshalling of TimeZone")
    @Test
    public void fromJsonToTimeZoneTypes() throws Exception {

        //java.util.TimeZone
        TimeZone timeZone = jsonb.fromJson("\"Europe/Prague\"", TimeZone.class);
        assertNotNull(timeZone);

        //java.util.SimpleTimeZone
        SimpleTimeZone simpleTimeZone = jsonb.fromJson("\"Europe/Prague\"", SimpleTimeZone.class);
        assertNotNull(simpleTimeZone);
    }
}
