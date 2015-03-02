package jug.ua.jsonb.examples.default_mapping;

import org.junit.Before;
import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by sirotae on 3/2/2015.
 */
public class ArraysMappingTest {

    Jsonb jsonb;

    @Before
    public void init() {
        jsonb = JsonbBuilder.create();
    }

    @Test
    public void byteArrayFromJsonTest() {
        Byte[] act = jsonb.fromJson("[1,2]", Byte[].class);
        Byte[] exp = {1,2};
        assertArrayEquals(exp, act);
    }

    @Test
    public void byteArrayToJsonTest() {
        byte[] value = {1, 2};
        String js = jsonb.toJson(value);
        assertEquals("[1,2]",js);
    }

}
