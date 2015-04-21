package jug.ua.meetup;

import com.fasterxml.jackson.core.JsonParseException;
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
 *   - ser/deser value
 *   - deserializing null to primitive
 *      - jackson can fail on nulls
 */
public class Case4Primitives {

    //GSON
    private Gson gson = new Gson();

    //JACKSON
    private ObjectMapper jackson = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);

    //GENSON
    private Genson genson = new Genson();


    @Test
    public void gsonSerializeIntPrimitive() throws IOException {
        int val = 1;
        String exp = "1";

        String gsonRes = gson.toJson(val);
        assertEquals(exp, gsonRes);

        String jacksonRes = jackson.writeValueAsString(val);
        assertEquals(exp, jacksonRes);

        String gensonRes = genson.serialize(val);
        assertEquals(exp, gensonRes);
    }

    @Test
    public void gsonDeserializeIntPrimitive() throws IOException {
        String val = "1";
        int exp = 1;

        int gsonRes = gson.fromJson(val, int.class);
        assertEquals(exp, gsonRes);

        int jacksonRes = jackson.readValue(val, int.class);
        assertEquals(exp, jacksonRes);

        int gensonRes = genson.deserialize(val, int.class);
        assertEquals(exp, gensonRes);
    }


    @Test
    public void gsonDeserializeBoolTrue() throws IOException {
        String val = "true";
        boolean exp = true;

        boolean gsonRes = gson.fromJson(val, boolean.class);
        assertEquals(exp, gsonRes);

        boolean jacksonRes = jackson.readValue(val, boolean.class);
        assertEquals(exp, jacksonRes);

        boolean gensonRes = genson.deserialize(val, boolean.class);
        assertEquals(exp, gensonRes);
    }

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

    @Test
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
