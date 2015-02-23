package ua.jug.json.bind.gsonri;

import com.google.gson.GsonBuilder;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.spi.JsonProvider;

/**
 * Created by sirotae on 2/22/2015.
 */
public class JsonbGsonBuilder implements JsonbBuilder {

    private GsonBuilder gsonBuilder;

    JsonbGsonBuilder () {
        gsonBuilder = new GsonBuilder();
    }

    @Override
    public JsonbBuilder withConfig(JsonbConfig config) {
        return null;
    }

    @Override
    public JsonbBuilder withProvider(JsonProvider jsonpProvider) {
        return null;
    }

    @Override
    public Jsonb build() {
        return new JsonbGson(gsonBuilder.create());
    }

}
