package myexamples;

import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;

/**
 * Created by sirotae on 2/23/2015.
 */
public class UseCaseCreate {

    @Test
    public void createStandardJsonb1() {

        Jsonb json = JsonbBuilder.create();
    }

    @Test
    public void createStandardJsonb2() {
        JsonbBuilder builder = JsonbBuilder.newBuilder();
        Jsonb json = builder.build();
    }

    public void createNCustomize() {
        Jsonb json3 = JsonbBuilder.newBuilder()
                .withConfig(new JsonbConfig().toJsonFormatting(true))
                .build();
    }
}
