package jug.ua.meetup;

import com.google.gson.Gson;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static jug.ua.meetup.POJO.Book;

/**
 * Created by Olena_Syrota on 4/18/2015.
 *
 * scenario:
 * simple serialization
 *  - oops, different fields order
 */
public class Case1SimpeSerializationDeserialization {

    // gson
    private Gson gson = new Gson();

    //jackson
    private ObjectMapper jackson = new ObjectMapper();

    // genson
    private Genson genson = new Genson();
    //private Genson genson = new GensonBuilder().useConstructorWithArguments(false).create();

    // apache johnzone ?

    @Test
    public void simpleSerializeTest() throws Exception {
        Book book = new Book();
        book.setAuthor("Super Author");
        book.setTitle("Super Book");

        String expOrder = "{\"title\":\"Super Book\",\"author\":\"Super Author\"}";

        String gsonStr = gson.toJson(book);
        assertEquals(expOrder, gsonStr);

        String jacksonStr = jackson.writeValueAsString(book);
        assertEquals(expOrder, jacksonStr);


        String expLexic = "{\"author\":\"Super Author\",\"title\":\"Super Book\"}";
        // oops, fields are serialized in order other then field sequence.
        String gensonStr = genson.serialize(book);
        assertEquals(expLexic, gensonStr);
    }

    @Test
    public void simpleDeserializeTest() throws Exception {

        String serialized = "{\"title\":\"Super Book\",\"author\":\"Super Author\"}";

        Book bookGson = gson.fromJson(serialized, Book.class);

        Book bookJackson = jackson.readValue(serialized, Book.class);

        // oops, non-default constructor required by default. On demo - use builder or comment non-default constructor
        Book bookGenson = genson.deserialize(serialized, Book.class);

        Book bookExp = new Book();
        bookExp.setAuthor("Super Author");
        bookExp.setTitle("Super Book");

        assertEquals(bookExp, bookGson);
        assertEquals(bookExp, bookJackson);
        assertEquals(bookExp, bookGenson);
    }


}
