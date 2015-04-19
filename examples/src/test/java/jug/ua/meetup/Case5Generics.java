package jug.ua.meetup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Created by Olena_Syrota on 4/18/2015.
 */
public class Case5Generics {

    private Gson gson;

    private ObjectMapper jackson;

    private Genson genson = new GensonBuilder().useRuntimeType(true).create();
}
