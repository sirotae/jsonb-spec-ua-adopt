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
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

/**
 * Created by Olena_Syrota on 6/3/2015.
 *
 * discussion:
 * - ser/deser standard Java Date/Time/Zones types
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

        Date date = new GregorianCalendar(2015, 6, 1).getTime();

        //GSON
        String gsonRes = gson.toJson(date);

        //JACKSON
        String jacksonRes = jackson.writeValueAsString(date);

        //GENSON
        String gensonRes = genson.serialize(date);

        System.out.println(gsonRes);
        System.out.println(jacksonRes);
        System.out.println(gensonRes);
    }

    @Test
    public void serializePojoWithDate() throws Exception {
        
        PojoWithDate pojoWithDate = new PojoWithDate(new GregorianCalendar(2015, 6, 1).getTime());
        
        //GSON
        String gsonRes = gson.toJson(pojoWithDate);

        //JACKSON
        String jacksonRes = jackson.writeValueAsString(pojoWithDate);

        //GENSON
        String gensonRes = genson.serialize(pojoWithDate);

        System.out.println(gsonRes);
        System.out.println(jacksonRes);
        System.out.println(gensonRes);
    }

    static class PojoWithDate{
        
        public Date date;

        public PojoWithDate(Date date) {
            this.date = date;
        }
    }

}
