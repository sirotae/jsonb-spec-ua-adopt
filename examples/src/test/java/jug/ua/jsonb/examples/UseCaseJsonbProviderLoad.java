package jug.ua.jsonb.examples;

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
public class UseCaseJsonbProviderLoad {

    private final String PROVIDER = "jug.ua.jsonb.impl.gson.spi.GsonJsonbProvider";

    @Test
    public void createProviderFromUnderlyingRI() {
        JsonbProvider provider = JsonbProvider.provider();
        assertEquals(provider.getClass().getName(),PROVIDER);
    }

    @Test
    public void createProviderByClassName() {
        JsonbProvider provider = JsonbProvider.provider(PROVIDER);
        assertNotNull(provider);
    }


}
