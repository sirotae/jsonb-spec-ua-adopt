package jug.ua.meetup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


/**
 * Created by Olena_Syrota on 4/19/2015.
 *
 * scenario:
 *   deserializing null to primitive
 *   -
 */
public class Case4Nulls {

    // gson
    private Gson gson = new GsonBuilder().create();

    //jackson, no builder pattern used
    private ObjectMapper jackson = new ObjectMapper()
            .configure(DeserializationConfig.Feature.FAIL_ON_NULL_FOR_PRIMITIVES, true)
            ;

    // genson
    private Genson genson = new GensonBuilder().create();


    @Test
    public void gsonDeserNullPrimitive() {
        String val = "{\"primitive\":null}";
        ClazzWithPrimitive ins = gson.fromJson(val, ClazzWithPrimitive.class);
        assertEquals(0, ins.getPrimitive());
    }

    @Test(expected = org.codehaus.jackson.map.JsonMappingException.class)
    public void jacksonDeserNullPrimitive() throws IOException {
        String val = "{\"primitive\":null}";
        ClazzWithPrimitive ins = jackson.readValue(val, ClazzWithPrimitive.class);
        assertEquals(0, ins.getPrimitive());
    }

    @Test()
    public void gensonDeserNullPrimitive() throws IOException {
        String val = "{\"primitive\":null}";
        ClazzWithPrimitive ins = genson.deserialize(val, ClazzWithPrimitive.class);
        assertEquals(0, ins.getPrimitive());
    }


    public static class ClazzWithPrimitive {
        private int primitive;

        public int getPrimitive() {
            return primitive;
        }

        public void setPrimitive(int primitive) {
            this.primitive = primitive;
        }
    }



}
