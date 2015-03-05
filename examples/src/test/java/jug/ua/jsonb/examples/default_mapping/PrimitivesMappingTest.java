package jug.ua.jsonb.examples.default_mapping;

import org.junit.Before;
import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import static org.junit.Assert.assertEquals;

/**
 * Created by sirotae on 3/1/2015.
 */

public class PrimitivesMappingTest {

    Jsonb jsonb;

    @Before
    public void init() {
        jsonb = JsonbBuilder.create();
    }

    @Test
    public void byteFromJsonTest() {
        byte i = jsonb.fromJson("1", byte.class);
        assertEquals(1,i);
    }

    @Test
    public void byteToJsonTest() {
        byte value = 1;
        String js = jsonb.toJson(value);
        assertEquals("1",js);
    }

    @Test
    public void shortFromJsonTest() {
        short i = jsonb.fromJson("1", short.class);
        assertEquals(1,i);
    }

    @Test
    public void shortToJsonTest() {
        short value = 1;
        String js = jsonb.toJson(value);
        assertEquals("1",js);
    }


    @Test
    public void intFromJsonTest() {
        int i = jsonb.fromJson("1", int.class);
        assertEquals(1,i);
    }

    @Test
    public void intToJsonTest() {
        String js = jsonb.toJson(1);
        assertEquals("1",js);
    }

    @Test
    public void longFromJsonTest() {
        long i = jsonb.fromJson("1", long.class);
        assertEquals(1,i);
    }

    @Test
    public void longToJsonTest() {
        String js = jsonb.toJson(1L);
        assertEquals("1",js);
    }

    @Test
    public void floatFromJsonTest() {
        float i = jsonb.fromJson("1.25", float.class);
        assertEquals(1.25f, i, 0.0f);
    }

    @Test
    public void floatToJsonTest() {
        String js = jsonb.toJson(1.25f);
        assertEquals("1.25", js);
    }

    @Test
    public void doubleFromJsonTest() {
        double i = jsonb.fromJson("1.25", double.class);
        assertEquals(1.25, i, 0.0);
    }

    @Test
    public void doubleToJsonTest() {
        String js = jsonb.toJson(1.25);
        assertEquals("1.25", js);
    }


    @Test
    public void booleanFromJsonTest() {
        boolean i = jsonb.fromJson("true", boolean.class);
        assertEquals(true, i);
    }

    @Test
    public void booleanToJsonTest() {
        String js = jsonb.toJson(true);
        assertEquals("true", js);
    }

    @Test
    public void nullFromJsonTest() {
        Object val = jsonb.fromJson("null", Object.class);
        assertEquals(null, val);
    }

    @Test
    public void nullValueToJsonTest() {
        String act = jsonb.toJson(null);
        assertEquals("null", act);
    }

    @Test
    public void nullRefToJsonTest() {
        Object val = null;
        String act = jsonb.toJson(val);
        assertEquals("null", act);
    }

}
