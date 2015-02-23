package myexamples.usecases;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.spi.JsonProvider;

/**
 * Created by sirotae on 2/14/2015.
 */
public class Create{

    public void createStandardJsonb() {
        Jsonb json3 = JsonbBuilder.create();
        Jsonb json4 = JsonbBuilder.newBuilder().build();
    }

    public void createGson() {
        Gson gson1 = new Gson();
        Gson gson2 = new GsonBuilder().create();
    }



}
