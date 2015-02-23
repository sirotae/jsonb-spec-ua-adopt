package ua.jug.json.bind.gsonri;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.spi.JsonbProvider;

/**
 * Created by sirotae on 2/22/2015.
 */
public class JsonbGsonProvider extends JsonbProvider {

    @Override
    public JsonbBuilder create() {
        return new JsonbGsonBuilder();
    }
}
