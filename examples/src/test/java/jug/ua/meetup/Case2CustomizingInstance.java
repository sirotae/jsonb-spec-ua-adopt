package jug.ua.meetup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static jug.ua.meetup.POJO.Book;

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

    // gson
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    //jackson, no builder pattern used
    private ObjectMapper jackson = new ObjectMapper()
            .configure(SerializationConfig.Feature.INDENT_OUTPUT, true);

    // genson
    private Genson genson = new GensonBuilder().useIndentation(true).create();


    // demonstrates that every mapper has own notion of "pretty"
    @Test
    public void indentedSerializeTest() throws Exception {
        Book book = new Book();
        book.setAuthor("Super Author");
        book.setTitle("Super Book");

        String expGson =
                "{\n" +
                        "  \"title\": \"Super Book\",\n" +
                        "  \"author\": \"Super Author\"\n" +
                        "}";

        String gsonStr = gson.toJson(book);
        assertEquals(expGson, gsonStr);

        String expJackson =
                "{\r\n" +
                        "  \"title\" : \"Super Book\",\r\n" +
                        "  \"author\" : \"Super Author\"\r\n" +
                        "}";

        String jacksonStr = jackson.writeValueAsString(book);
        assertEquals(expJackson, jacksonStr);


        String expLexic =
                "{\n" +
                        "  \"author\":\"Super Author\",\n" +
                        "  \"title\":\"Super Book\"\n" +
                        "}";
        String gensonStr = genson.serialize(book);
        assertEquals(expLexic, gensonStr);
    }

}
