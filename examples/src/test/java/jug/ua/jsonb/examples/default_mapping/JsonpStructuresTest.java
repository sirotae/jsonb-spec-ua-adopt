package jug.ua.jsonb.examples.default_mapping;

import org.junit.Ignore;
import org.junit.Test;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import static org.junit.Assert.assertNotNull;

/**
 * @author Oleg Tsal-Tsalko
 */
public class JsonpStructuresTest {

    Jsonb jsonb = JsonbBuilder.create();

    @Test
    public void fromJsonStructure() throws Exception {
        JsonStructure jsonStructure = jsonb.fromJson("{\"name\":\"unknown object\"}", JsonStructure.class);
        assertNotNull(jsonStructure);
    }

    @Test
    public void fromJsonObject() throws Exception {
        JsonObject jsonObject = jsonb.fromJson("{\"name\":\"unknown object\"}", JsonObject.class);
        assertNotNull(jsonObject);
    }

    @Test
    public void fromJsonArray() throws Exception {
        JsonArray jsonArray = jsonb.fromJson("[{\"value\":\"first\"},{\"value\":\"second\"}]", JsonArray.class);
        assertNotNull(jsonArray);
    }

    @Test
    @Ignore("Will be supported in JSON-P 1.1 and doesn't work right now cause we are using JSON-P 1.0 underneath")
    public void fromJsonValue() throws Exception {
        JsonValue jsonValue = jsonb.fromJson("1", JsonValue.class);
        assertNotNull(jsonValue);
    }

}
