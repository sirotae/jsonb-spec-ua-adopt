package jug.ua.jsonb.examples.runtime_api;

import jug.ua.jsonb.impl.gson.spi.GsonJsonbProvider;
import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.spi.JsonbProvider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by sirotae on 2/26/2015.
 */
public class CreateJsonbUsingCustomProvider {

    private final String PROVIDER = "jug.ua.jsonb.impl.gson.spi.GsonJsonbProvider";

    @Test
    public void testProviderLookupFromClasspath() {
        JsonbProvider provider = JsonbProvider.provider();
        assertEquals(provider.getClass().getName(),PROVIDER);
    }

    @Test
    public void createProviderByClassName() {
        JsonbProvider provider = JsonbProvider.provider(PROVIDER);
        assertNotNull(provider);
    }

    @Test
    public void createJsonbUsingCustomProvider() {
        Jsonb jsonb = JsonbBuilder.newBuilder(PROVIDER).build();
        assertNotNull(jsonb);
    }

    @Test
    public void createJsonbUsingExplicitlyCreatedCustomProvider() {
        Jsonb jsonb = JsonbBuilder.newBuilder(new GsonJsonbProvider()).build();
        assertNotNull(jsonb);
    }
}
