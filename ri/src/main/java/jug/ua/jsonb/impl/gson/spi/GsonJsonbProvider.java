package jug.ua.jsonb.impl.gson.spi;

import jug.ua.jsonb.impl.gson.GsonJsonbBuilder;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.spi.JsonbProvider;

/**
 * This is an example of draft {@link javax.json.bind.spi.JsonbProvider} implementation
 * which uses Gson under the hood
 *
 * @author Oleg Tsal-Tsalko
 */
public class GsonJsonbProvider extends JsonbProvider{
    @Override
    public JsonbBuilder create() {
        return new GsonJsonbBuilder();
    }
}
