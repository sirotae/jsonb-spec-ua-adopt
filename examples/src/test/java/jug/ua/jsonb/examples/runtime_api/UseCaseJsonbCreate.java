package jug.ua.jsonb.examples.runtime_api;

import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.spi.JsonbProvider;

import static org.junit.Assert.assertNotNull;

/**
 * Created by sirotae on 2/22/2015.
 */
public class UseCaseJsonbCreate {

    @Test
    public void createJsonbViaProvider() {
        JsonbBuilder builder = JsonbProvider.provider().create();
        Jsonb jsonb = builder.build();
    }

    @Test
    public void createJsonbViaBuilder() {
        JsonbBuilder builder = JsonbBuilder.newBuilder();
        Jsonb jsonb = builder.build();
    }

    @Test
    public void createJsonbViaBuilderShortcut() {
        Jsonb jsonb = JsonbBuilder.create();
        assertNotNull(jsonb);
    }


    @Test
    public void createJsonbViaBuilderWithConfig() {
        Jsonb json3 = JsonbBuilder.newBuilder()
                .withConfig(new JsonbConfig().toJsonFormatting(true))
                .build();
    }

}
