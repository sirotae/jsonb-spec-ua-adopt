package jug.ua.meetup;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.owlike.genson.Genson;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


/**
 * Created by Olena_Syrota on 4/19/2015.
 *
 * scenario:
 *   deserializing null to primitive
 *   - jackson can fail on nulls
 */
public class Case4PrimitiveNulls {

    //GSON
    private Gson gson = new Gson();

    //JACKSON
    private ObjectMapper jackson = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);

    //GENSON
    private Genson genson = new Genson();


    @Test
    public void gsonDeserNullPrimitive() {
        String val = "{\"primitive\":null}";
        ClassWithPrimitive ins = gson.fromJson(val, ClassWithPrimitive.class);
        assertEquals(0, ins.getPrimitive());
    }

    @Test(expected = JsonMappingException.class)
    public void jacksonDeserNullPrimitive() throws IOException {
        String val = "{\"primitive\":null}";
        ClassWithPrimitive ins = jackson.readValue(val, ClassWithPrimitive.class);
        assertEquals(0, ins.getPrimitive());
    }

    @Test()
    public void gensonDeserNullPrimitive() throws IOException {
        String val = "{\"primitive\":null}";
        ClassWithPrimitive ins = genson.deserialize(val, ClassWithPrimitive.class);
        assertEquals(0, ins.getPrimitive());
    }


    public static class ClassWithPrimitive {

        private int primitive;

        public int getPrimitive() {
            return primitive;
        }

        public void setPrimitive(int primitive) {
            this.primitive = primitive;
        }
    }



}
