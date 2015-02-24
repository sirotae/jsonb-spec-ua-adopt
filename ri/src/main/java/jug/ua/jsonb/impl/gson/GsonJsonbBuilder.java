package jug.ua.jsonb.impl.gson;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.spi.JsonProvider;

/**
 * @author Oleg Tsal-Tsalko
 */
public class GsonJsonbBuilder implements JsonbBuilder{

    private JsonbConfig config;

    @Override
    public JsonbBuilder withConfig(JsonbConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public JsonbBuilder withProvider(JsonProvider jsonpProvider) {
        throw new UnsupportedOperationException("This impl not gonna support custom JsonProvider");
    }

    @Override
    public Jsonb build() {
        return new GsonJsonbWrapper(config);
    }
}
