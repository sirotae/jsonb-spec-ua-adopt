package jug.ua.meetup;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.fail;

/**
 * Created by Olena_Syrota on 4/19/2015.
 *
 * scenario
 * - demonstrate different exceptions for not well formed json
 */
public class Case3Exceptions {

    // gson
    private Gson gson = new GsonBuilder().create();

    //jackson, no builder pattern used
    private ObjectMapper jackson = new ObjectMapper();

    // genson
    private Genson genson = new GensonBuilder().create();


    @Test(expected = com.google.gson.JsonSyntaxException.class)
    public void gsonNotWellFormedJson() {
        gson.fromJson("[1,2", int[].class);
    }

    @Test(expected = JsonParseException.class)
    public void jacksonNotWellFormedJson() throws IOException {
        jackson.readValue("[1,2", int[].class);
    }

    @Test(expected=com.owlike.genson.JsonBindingException.class)
    public void gensonNotWellFormedJson() {
        genson.deserialize("[1,2", int[].class);
    }

}
