package jug.ua.meetup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.junit.Assert.assertEquals;

/**
 * Created by Olena_Syrota on 4/18/2015.
 *
 * scenario:
 *  - create custom mapper instance via builder
 *      jackson - builder pattern is not used
 *  - oops, "pretty" notion is different
 *
 */
public class Case2CustomizingInstance {

    //GSON
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    //JACKSON
    //No builder available. ObjectMapper class is all in one.
    private ObjectMapper jackson = new ObjectMapper()
            .configure(SerializationFeature.INDENT_OUTPUT, true);

    //GENSON
    private Genson genson = new GensonBuilder().useIndentation(true).create();


    // demonstrates that every mapper has own notion of "pretty"
    @Test
    public void indentedSerializationExample() throws Exception {
        Book book = new Book("Super Book", "Super Author");

        String expOrdered =
                "{\n" +
                        "  \"title\": \"Super Book\",\n" +
                        "  \"author\": \"Super Author\"\n" +
                        "}";

        String gsonStr = gson.toJson(book);
        JSONAssert.assertEquals(expOrdered, gsonStr, true);

        String jacksonStr = jackson.writeValueAsString(book);
        JSONAssert.assertEquals(expOrdered, jacksonStr, true);

        String expLexic =
                "{\n" +
                        "  \"author\":\"Super Author\",\n" +
                        "  \"title\":\"Super Book\"\n" +
                        "}";
        String gensonStr = genson.serialize(book);
        JSONAssert.assertEquals(expLexic, gensonStr, true);
    }

}
