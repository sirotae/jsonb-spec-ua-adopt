package jug.ua.jsonb.examples.dates_mapping;

import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import java.util.Calendar;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by Olena_Syrota on 4/13/2015.
 */
public class CalendarMappingTest {

    Jsonb jsonb = JsonbBuilder.create();

    @Test
    public void calendarFromJsonTest() {
        //Calendar act = jsonb.fromJson("\"2015-04-03\"", Calendar.class);

        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.clear();
        dateCalendar.set(2015, 3, 3);

        //assertEquals(dateCalendar, act);
    }

    @Test
    public void calendarToJsonTest() {
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.clear();
        dateCalendar.set(2015, 3, 3);
        //assertEquals("\"2015-04-03\"", jsonb.toJson(dateCalendar));
    }
}
