package jug.ua.meetup;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.owlike.genson.GenericType;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Olena_Syrota on 4/18/2015.
 *
 * scenario
 * - implementing deser on the basis of TypeToken
 */
public class Case5Generics {

    private Gson gson;

    private ObjectMapper jackson = new ObjectMapper();

    private Genson genson = new GensonBuilder().useRuntimeType(true).create();


    public void gsonDeserToGeneric() {

    }

    @Test
    public void jacksonDeserToGeneric() throws IOException {
        String jsonString = "[{\n" +
                "  \"title\": \"Super Book1\",\n" +
                "  \"author\": \"Super Author1\"\n" +
                "},\n" +
                "{" +
                "  \"title\": \"Super Book2\",\n" +
                "  \"author\": \"Super Author2\"\n" +
                "}]";


        JavaType collectionType = jackson.getTypeFactory().constructCollectionType(List.class, Book.class);
        List<Book> readValue = jackson.readValue(jsonString, collectionType);

        List<Book> exp = new ArrayList<Book>();
        exp.add(new Book("Super Book1", "Super Author1"));
        exp.add(new Book("Super Book2", "Super Author2"));

        assertEquals(exp, readValue);
    }

    @Test
    public void gensonDeserToGeneric() throws IOException {
        String jsonString = "[{\n" +
                "  \"title\": \"Super Book1\",\n" +
                "  \"author\": \"Super Author1\"\n" +
                "},\n" +
                "{" +
                "  \"title\": \"Super Book2\",\n" +
                "  \"author\": \"Super Author2\"\n" +
                "}]";

        List<Book> readValue = genson.deserialize(jsonString, new GenericType<List<Book>>(){});

        List<Book> exp = new ArrayList<Book>();
        exp.add(new Book("Super Book1", "Super Author1"));
        exp.add(new Book("Super Book2", "Super Author2"));

        assertEquals(exp, readValue);
    }

}
