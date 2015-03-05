package jug.ua.jsonb.examples.default_mapping;

import org.junit.Before;
import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by sirotae on 3/5/2015.
 */
public class CollectionMappingTest {

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
    public void listFromJsonTest() {
        List<String> act = jsonb.fromJson("[\"name 1\", \"name 2\"]", List.class);
        List<String> exp = new ArrayList();
        exp.add("name 1");
        exp.add("name 2");
        assertEquals(exp, act);
    }

    @Test
    public void listToJsonTest() {
        List<String> value = new ArrayList();
        value.add("name 1");
        value.add("name 2");
        String act = jsonb.toJson(value);
        assertEquals("[\"name 1\",\"name 2\"]",act);
    }


}
