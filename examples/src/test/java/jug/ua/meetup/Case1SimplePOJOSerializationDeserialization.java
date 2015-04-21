package jug.ua.meetup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.owlike.genson.Genson;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Olena_Syrota on 4/18/2015.
 *
 * scenario:
 * simple serialization
 *  - oops, different fields order
 *  - constructors, setters for private fields
 */
public class Case1SimplePOJOSerializationDeserialization {

    //GSON
    private Gson gson = new Gson();

    //JACKSON
    private ObjectMapper jackson = new ObjectMapper();

    // genson
    private Genson genson = new Genson();
    //private Genson genson = new GensonBuilder().useConstructorWithArguments(false).create();

    // apache johnzone ?

    @Test
    public void simplePOJOSerialization() throws Exception {
        Book book = new Book("Super Book", "Super Author");

        //GSON
        String gsonJson = gson.toJson(book);

        //JACKSON
        String jacksonJson = jackson.writeValueAsString(book);

        //GENSON
        String gensonJson = genson.serialize(book);

        String expectedJson = "{\"title\":\"Super Book\",\"author\":\"Super Author\"}";
        assertEquals(expectedJson, gsonJson);
        assertEquals(expectedJson, jacksonJson);
        String gensonDiffOrderJson = "{\"author\":\"Super Author\",\"title\":\"Super Book\"}";
        assertEquals(gensonDiffOrderJson, gensonJson);
    }

    @Test
    public void simplePOJODeserialization() throws Exception {

        String json = "{\"title\":\"Super Book\",\"author\":\"Super Author\"}";

        //GSON
        Book gsonBook = gson.fromJson(json, Book.class);

        //JACKSON
        //Requires default constructor to be present
        Book jacksonBook = jackson.readValue(json, Book.class);

        //GENSON
        //Requires default constructor to be present
        //Requires setters for private fields
        Book gensonBook = genson.deserialize(json, Book.class);

        Book bookExp = new Book("Super Book","Super Author");
        assertEquals(bookExp, gsonBook);
        assertEquals(bookExp, jacksonBook);
        assertEquals(bookExp, gensonBook);
    }


}
