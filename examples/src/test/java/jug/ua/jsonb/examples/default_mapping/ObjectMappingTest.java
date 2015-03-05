package jug.ua.jsonb.examples.default_mapping;

import org.junit.Before;
import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by sirotae on 3/5/2015.
 */
public class ObjectMappingTest {

    Jsonb jsonb;

    @Before
    public void init() {
        jsonb = JsonbBuilder.create();
    }

    @Test
    public void mapFromJsonTest() {
        Map<String, String> act = jsonb.fromJson("{\"name 1\":\"2\", \"name 2\":\"4\",\"name 3\":\"6\",\"name 4\":\"8\"}", Map.class);
        Map<String, String> exp = new LinkedHashMap();
        exp.put("name 1", "2");
        exp.put("name 2", "4");
        exp.put("name 3", "6");
        exp.put("name 4", "8");
        assertEquals(exp, act);
    }

    @Test
    public void mapToJsonTest() {
        Map<String, String> value = new LinkedHashMap();
        value.put("name 1", "2");
        value.put("name 2", "4");
        value.put("name 3", "6");
        value.put("name 4", "8");
        String act = jsonb.toJson(value);
        assertEquals("{\"name 1\":\"2\",\"name 2\":\"4\",\"name 3\":\"6\",\"name 4\":\"8\"}",act);
    }

    @Test
    public void arrayOfMapsFromJsonTest() {
        Map<String, String>[] act = jsonb.fromJson("[{\"name 1\":\"2\", \"name 2\":\"4\"},{\"name 3\":\"6\",\"name 4\":\"8\"}]", Map[].class);
        Map<String, String> exp1 = new LinkedHashMap();
        exp1.put("name 1", "2");
        exp1.put("name 2", "4");

        Map<String, String> exp2 = new LinkedHashMap();
        exp2.put("name 3", "6");
        exp2.put("name 4", "8");
        Map[] exp = {exp1, exp2};
        assertArrayEquals(exp, act);
    }

    @Test
    public void arrayOfMapsToJsonTest() {
        Map<String, String> val1 = new LinkedHashMap();
        val1.put("name 1", "2");
        val1.put("name 2", "4");
        Map<String, String> val2 = new LinkedHashMap();
        val2.put("name 3", "6");
        val2.put("name 4", "8");

        Map[] value = {val1, val2};

        String act = jsonb.toJson(value);
        assertEquals("[{\"name 1\":\"2\",\"name 2\":\"4\"},{\"name 3\":\"6\",\"name 4\":\"8\"}]",act);
    }



}
