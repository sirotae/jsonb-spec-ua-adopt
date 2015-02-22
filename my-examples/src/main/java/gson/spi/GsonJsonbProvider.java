package gson.spi;

import gson.GsonJsonbBuilder;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.spi.JsonbProvider;

/**
 * This is an example of draft {@link JsonbProvider} implementation
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
