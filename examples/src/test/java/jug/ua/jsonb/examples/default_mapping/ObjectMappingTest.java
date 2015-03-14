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

}
