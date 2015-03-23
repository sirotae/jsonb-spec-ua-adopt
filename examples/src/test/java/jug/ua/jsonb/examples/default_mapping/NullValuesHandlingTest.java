package jug.ua.jsonb.examples.default_mapping;

import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Oleg Tsal-Tsalko
 */
public class NullValuesHandlingTest {

    Jsonb jsonb = JsonbBuilder.create();

    @Test
    public void collectionWithNullsMarshalling() throws Exception {
        List<String> stringList = new ArrayList<>();
        stringList.add("value1");
        stringList.add(null);
        stringList.add("value3");

        assertEquals("[\"value1\",null,\"value3\"]", jsonb.toJson(stringList));
    }

    @Test
    public void pojoWithNullsMarshalling() throws Exception {
        POJO pojo = new POJO();
        pojo.id = 1;
        pojo.name = null;

        assertEquals("{\"id\":1}", jsonb.toJson(pojo));
    }

    /**
     * This one is missing in examples I guess, but very common
     */
    @Test
    public void unmarshallingFromJsonWithMissingFields() throws Exception {
        POJO pojo = jsonb.fromJson("{\"id\":1}", POJO.class);
        assertEquals(1, pojo.id);
        assertNull(pojo.name);
    }

    @Test
    public void unmarshallingFromJsonWithNullValues() throws Exception {
        POJO pojo = jsonb.fromJson("{\"id\":1, \"name\":null}", POJO.class);
        assertEquals(1, pojo.id);
        assertNull(pojo.name);
    }

    /**
     * I haven't seen any examples of POJO with private fields without getters/setters
     * Is it supposed to be supported
     */
    static class POJO{
        private int id;
        private String name;
    }
}
