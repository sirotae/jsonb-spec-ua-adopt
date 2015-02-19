package myexamples.usecases;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.json.*;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.spi.JsonbProvider;
import javax.json.spi.JsonProvider;
import java.text.DateFormat;

/**
 * Created by sirotae on 2/14/2015.
 */
public class CreateNCustomize {

    public void createGson() {
        Gson gson1 = new Gson();

        Gson gson = new GsonBuilder()
                     .enableComplexMapKeySerialization()
                     .serializeNulls()
                     .setDateFormat(DateFormat.LONG)
                     .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                     .setPrettyPrinting()
                     .setVersion(1.0)
                     .create();    }


    public void createCustomJsonbWithSeperateClass() {
        // 1) who is main developer of new CustomJsonbBuilder - programmer or JSON-B SPI developer?
        // 2) which special behavior should be implemented in CustomBuilder with methods withConfig, withProvider, build
        Jsonb json3 = new CustomJsonbBuilder().build();
    }

    public void createCustomJsonbBuilderWithProvider() {

        // 1) why do I need provider class to call new CustomJsonbBuilder
        // 2) what is tha main case for creating builder in this way
        Jsonb jsonb = JsonbBuilder.newBuilder(new JsonbProvider() {
                public JsonbBuilder create() {
                    return new CustomJsonbBuilder();
                }
            }).build();
    }

    public void createCustomJsonbWithStandardBuilder() {
        JsonProvider jsonProcProv = null;
        JsonbProvider jsonBindProvider = null;
        // confusing:
        // 1) provider parameter in method withProvider is JSON-P provider. I initially set JSON-B provider. Too many providers in JSON-B API
        // 2)confusing method build in JsonbBuilder instance. Intuitively I want to call .create() - the same method name as was in call JsonbBuilder.create()
        // 3) I would propose to reject from newBuilder method and use constructor - like it is done in gson - example below
        Jsonb json3 = JsonbBuilder.newBuilder(jsonBindProvider)
                .withConfig(new JsonbConfig().toJsonFormatting(true))
                .withProvider(jsonProcProv)
                .build();

        /*// I would propose to reject from newBuilder method and use constructor - like it is done in gson
        Jsonb json4 = new JsonbBuilder(jsonBindProvider)
                .withConfig(new JsonbConfig().toJsonFormatting(true))
                .withProvider(jsonProcProv)
                .build();
                */

    }


    class CustomJsonbBuilder implements JsonbBuilder{

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
            return null;
        }
    }



}
