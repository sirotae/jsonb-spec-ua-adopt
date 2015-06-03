package jug.ua.meetup;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import org.junit.Test;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by Olena_Syrota on 6/3/2015.
 */
public class Case8Dates {

    //GSON
    private Gson gson = new GsonBuilder()
                        .create();

    //JACKSON
    private ObjectMapper jackson = new ObjectMapper();

    //GENSON
    private Genson genson = new GensonBuilder()
            .create();


    @Test
    public void serializeDate() throws JsonProcessingException {

        Date val = new Calendar.Builder().setDate(2015, 06, 01).build().getTime();

        //GSON
        String gsonRes = gson.toJson(val);

        //JACKSON
        String jacksonRes = jackson.writeValueAsString(val);

        //GENSON
        String gensonRes = genson.serialize(val);

        System.out.println(gsonRes);
        System.out.println(jacksonRes);
        System.out.println(gensonRes);
    }

}
