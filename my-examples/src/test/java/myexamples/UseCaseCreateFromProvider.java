package myexamples;

import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.spi.JsonbProvider;
import javax.json.spi.JsonProvider;

/**
 * Created by sirotae on 2/22/2015.
 */
public class UseCaseCreateFromProvider {

    private final String PROVIDER = "ua.jug.json.bind.gsonri.JsonbGsonProvider";

    @Test
    public void createStandardJsonb1() {
        JsonbBuilder builder = JsonbProvider.provider(PROVIDER).create();
        Jsonb jsonb = builder.build();
    }

    @Test
    public void createStandardJsonb2() {
        JsonbBuilder builder = JsonbBuilder.newBuilder(PROVIDER);
        Jsonb jsonb = builder.build();
    }

    public void createNCustomize() {
        Jsonb json3 = JsonbBuilder.newBuilder(PROVIDER)
                .withConfig(new JsonbConfig().toJsonFormatting(true))
                .build();
    }
}
