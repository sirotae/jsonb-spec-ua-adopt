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
        String act = jsonb.toJson(value);
        assertEquals("[1,2]",act);
    }

    @Test
    public void stringArrayFromJsonTest() {
        String[] act = jsonb.fromJson("[\"string1\", \"string2\"]", String[].class);
        String[] exp = {"string1","string2"};
        assertArrayEquals(exp, act);
    }

    @Test
    public void stringArrayToJsonTest() {
        String[] value = {"string1","string2"};
        String act = jsonb.toJson(value);
        assertEquals("[\"string1\",\"string2\"]", act);
    }


    @Test
    public void string2DimArrayFromJsonTest() {
        String[][] act = jsonb.fromJson("[[\"string11\", \"string12\"],[\"string21\", \"string22\"]]", String[][].class);
        String[][] exp = {{"string11","string12"},{"string21","string22"}};
        assertArrayEquals(exp, act);
    }

    @Test
    public void string2DimArrayToJsonTest() {
        String[][] value = {{"string11","string12"},{"string21","string22"}};
        String act = jsonb.toJson(value);
        assertEquals("[[\"string11\",\"string12\"],[\"string21\",\"string22\"]]", act);
    }

}
