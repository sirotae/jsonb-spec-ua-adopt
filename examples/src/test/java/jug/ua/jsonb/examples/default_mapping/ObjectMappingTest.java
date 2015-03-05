package jug.ua.jsonb.examples.default_mapping;

import org.junit.Before;
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
public class ObjectMappingTest {

    Jsonb jsonb;

    @Before
    public void init() {
        jsonb = JsonbBuilder.create();
    }

    public void objectAsMapFromJsonTest() {
        Map<String, String> act = (Map)jsonb.fromJson("{\"name 1\":\"2\"}", Object.class);
        Map<String, String> exp = new LinkedHashMap();
        exp.put("name 1", "2");
        exp.put("name 2", "4");
        exp.put("name 3", "6");
        exp.put("name 4", "8");
        assertEquals(exp, act);
    }

    public void objectAsListFromJsonTest() {
        List<String> act = (List)jsonb.fromJson("[\"value 1\", \"value 2\"]", Object.class);
        List<String> exp = new ArrayList();
        exp.add("value 1");
        exp.add("value 2");
        assertEquals(exp, act);
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
