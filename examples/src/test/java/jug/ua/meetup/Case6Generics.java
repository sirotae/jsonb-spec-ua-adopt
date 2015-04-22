package jug.ua.meetup;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.owlike.genson.GenericType;
import com.owlike.genson.Genson;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Olena_Syrota on 4/18/2015.
 *
 * scenario
 * - implementing deser on the basis of TypeToken
 */
public class Case6Generics {

    private Gson gson = new Gson();

    private ObjectMapper jackson = new ObjectMapper();

    private Genson genson = new Genson();

    @Test
    public void gsonDeserToGeneric() throws IOException {
        String jsonString = "[{\n" +
                "  \"title\": \"Super Book1\",\n" +
                "  \"author\": \"Super Author1\"\n" +
                "},\n" +
                "{" +
                "  \"title\": \"Super Book2\",\n" +
                "  \"author\": \"Super Author2\"\n" +
                "}]";

        //GSON
        Type fooType = new TypeToken<List<Book>>(){}.getType();
        List<Book> gsonRes = gson.fromJson(jsonString, fooType);

        //JACKSON
        JavaType collectionType = jackson.getTypeFactory().constructCollectionType(List.class, Book.class);
        List<Book> jacksonRes = jackson.readValue(jsonString, collectionType);

        //GENSON
        List<Book> gensonRes = genson.deserialize(jsonString, new GenericType<List<Book>>(){});

        List<Book> exp = new ArrayList<>();
        exp.add(new Book("Super Book1", "Super Author1"));
        exp.add(new Book("Super Book2", "Super Author2"));

        assertEquals(exp, gsonRes);
        assertEquals(exp, jacksonRes);
        assertEquals(exp, gensonRes);
    }

}
