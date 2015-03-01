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
    public void intFromJsonTest() {
        int i = jsonb.fromJson("1", int.class);
        assertEquals(i,1);
    }

    @Test
    public void intToJsonTest() {
        String i = jsonb.toJson(1);
        assertEquals(i,"1");
    }
}
