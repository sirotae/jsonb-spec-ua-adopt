package jug.ua.jsonb.examples.default_mapping;

import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbException;

/**
 * @author Oleg Tsal-Tsalko
 */
public class ExceptionsTest {

    Jsonb jsonb = JsonbBuilder.create();

    @Test(expected = JsonbException.class)
    public void notValidNumber() throws Exception {
        jsonb.fromJson("not_a_number", Integer.class);
    }

    /**
     * Gson throws IllegalArgumentException here
     */
    @Test(expected = JsonbException.class)
    public void notValidArray() throws Exception {
        jsonb.fromJson("[null,1]", int[].class);
    }

    @Test(expected = JsonbException.class)
    public void notWellFormedJson() throws Exception {
        jsonb.fromJson("[1,2", int[].class);
    }

    @Test(expected = JsonbException.class)
    public void valueOutOfRange() throws Exception {
        jsonb.fromJson("["+new Integer(Byte.MAX_VALUE + 1)+"]", Byte.class);
    }

}
