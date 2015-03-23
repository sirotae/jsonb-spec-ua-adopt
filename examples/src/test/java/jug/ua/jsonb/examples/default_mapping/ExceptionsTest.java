package jug.ua.jsonb.examples.default_mapping;

import org.junit.Ignore;
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

    @Ignore("Gson allows absence of default constructor")
    @Test(expected = JsonbException.class)
    public void pojoWithoutDefaultConstructor() throws Exception {
        POJOWithoutDefaultConstructor pojo = jsonb.fromJson("{\"id\":\"1\"}", POJOWithoutDefaultConstructor.class);
    }

    @Ignore("Gson allows private constructor")
    @Test(expected = JsonbException.class)
    public void pojoWithPrivateConstructor() throws Exception {
        POJOWithPrivateConstructor pojo = jsonb.fromJson("{\"id\":\"1\"}", POJOWithPrivateConstructor.class);
    }

    static class POJOWithoutDefaultConstructor {

        private int id;

        POJOWithoutDefaultConstructor(int id) {
            this.id = id;
        }
    }

    static class POJOWithPrivateConstructor {
        private String id;

        private POJOWithPrivateConstructor() {
        }
    }

}
