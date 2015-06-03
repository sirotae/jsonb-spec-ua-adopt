package jug.ua.jsonb.examples.default_mapping;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by sirotae on 3/5/2015.
 */
public class UntypedMappingTest {

    Jsonb jsonb = JsonbBuilder.create();

    @Test
    public void objectAsMapFromJsonTest() {
        Map<String, String> act = (Map<String, String>)jsonb.fromJson("{\"name 1\":\"2\"}", Object.class);
        Map<String, String> exp = new LinkedHashMap<>();
        exp.put("name 1", "2");
        assertEquals(exp, act);
    }

    @Test
    public void objectAsListFromJsonTest() {
        List<String> act = (List<String>)jsonb.fromJson("[\"value 1\", \"value 2\"]", Object.class);
        List<String> exp = new ArrayList<>();
        exp.add("value 1");
        exp.add("value 2");
        assertEquals(exp, act);
    }

    @Ignore("gson deserialize unmapped numeric to Double")
    @Test
    public void intFromJsonTest() {
        Object o = jsonb.fromJson("1", Object.class);
        Integer i = (Integer)o;
        assertEquals(1, i.intValue());
    }

    @Test
    public void doubltFromJsonTest() {
        Object o = jsonb.fromJson("1.0", Object.class);
        Double i = (Double)o;
        assertEquals(1.0, i.doubleValue(), 0.0f);
    }

    @Test
    public void booleanFromJsonTest() {
        Object o = jsonb.fromJson("true", Object.class);
        Boolean i = (Boolean)o;
        assertEquals(true, i.booleanValue());
    }

    @Test
    public void nullFromJsonTest() {
        Object val = jsonb.fromJson("null", Object.class);
        assertEquals(null, val);
    }

}
