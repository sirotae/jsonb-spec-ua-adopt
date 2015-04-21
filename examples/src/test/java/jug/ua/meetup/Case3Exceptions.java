package jug.ua.meetup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.owlike.genson.Genson;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Olena_Syrota on 4/19/2015.
 * <p>
 * scenario
 * - demonstrate different exceptions for not well formed json
 */
public class Case3Exceptions {

    //GSON
    private Gson gson = new Gson();

    //JACKSON
    private ObjectMapper jackson = new ObjectMapper();

    //GENSON
    private Genson genson = new Genson();


    @Test(expected = com.google.gson.JsonSyntaxException.class)
    public void gsonNotWellFormedJson() {
        gson.fromJson("[1,2", int[].class);
    }

    @Test(expected = com.fasterxml.jackson.databind.JsonMappingException.class)
    public void jacksonNotWellFormedJson() throws IOException {
        jackson.readValue("[1,2", int[].class);
    }

    @Test(expected = com.owlike.genson.JsonBindingException.class)
    public void gensonNotWellFormedJson() {
        genson.deserialize("[1,2", int[].class);
    }

}
