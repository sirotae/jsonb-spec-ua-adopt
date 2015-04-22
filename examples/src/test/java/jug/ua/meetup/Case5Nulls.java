package jug.ua.meetup;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.owlike.genson.Genson;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.junit.Assert.assertNull;

/**
 * @author Oleg Tsal-Tsalko
 */
public class Case5Nulls {

    //GSON
    private Gson gson = new Gson();

    //JACKSON
    private ObjectMapper jackson = new ObjectMapper();

    //GENSON
    private Genson genson = new Genson();

    @Test
    public void serializeClassWithNullFields() throws Exception {
        POJO pojo = new POJO();
        pojo.setField1("bla");

        //GSON
        //By default doesn't serialize null fields as we decided to do in spec
        String gsonJson = gson.toJson(pojo);
        System.out.println(gsonJson);

        //JACKSON
        String jacksonJson = jackson.writeValueAsString(pojo);
        System.out.println(jacksonJson);

        //GENSON
        String gensonJson = genson.serialize(pojo);
        System.out.println(gensonJson);

        String expectedJson = "{\"field1\":\"bla\"}";
        JSONAssert.assertEquals(expectedJson, gsonJson, true);
        String expectedJsonWillNulls = "{\"field1\":\"bla\", \"field2\":null}";
        JSONAssert.assertEquals(expectedJsonWillNulls, jacksonJson, true);
        JSONAssert.assertEquals(expectedJsonWillNulls, gensonJson, true);
    }

    @Test
    public void deserializeJsonIntoPOJOWithBothMissingFieldsAndRedundantFields() throws Exception {
        String json = "{\"field1\":\"bla\", \"field3\":\"foo\"}";

        //GSON
        POJO gsonPOJO = gson.fromJson(json, POJO.class);

        //JACKSON
        //By default Jackson strive to strict correspondence of JSON to POJO
        POJO jacksonPOJO = jackson.readValue(json, POJO.class);

        //GENSON
        POJO gensonPOJO = genson.deserialize(json, POJO.class);

        assertNull(gsonPOJO.field2);
        assertNull(jacksonPOJO.field2);
        assertNull(gensonPOJO.field2);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class POJO{

        private String field1;
        private String field2;

        public String getField1() {
            return field1;
        }

        public void setField1(String field1) {
            this.field1 = field1;
        }

        public String getField2() {
            return field2;
        }

        public void setField2(String field2) {
            this.field2 = field2;
        }
    }
}
