package jug.ua.jsonb.examples.default_mapping;

import org.junit.Ignore;
import org.junit.Test;

import javax.json.JsonException;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import static org.junit.Assert.assertEquals;

/**
 * @author Oleg Tsal-Tsalko
 */
public class DiffModifiersTest {

    private Jsonb jsonb = JsonbBuilder.create();

    @Test
    public void showsWhatFieldsBeingMarshalled() throws Exception {
        ModifiersClass modifiersClass = new ModifiersClass();
        assertEquals("{\"finalField\":\"finalValue\",\"regularField\":\"regularValue\"}", jsonb.toJson(modifiersClass));
    }

    @Ignore("Gson supports it")
    @Test(expected = JsonException.class)
    public void unmarshalFinalFieldIsNotSupported() throws Exception {
        ModifiersClass modifiersClass = jsonb.fromJson("{\"finalField\":\"finalValue\",\"regularField\":\"regularValue\"}", ModifiersClass.class);
    }

    @Ignore("Gson supports it")
    @Test(expected = JsonException.class)
    public void unmarshalStaticFieldIsNotSupported() throws Exception {
        ModifiersClass modifiersClass = jsonb.fromJson("{\"staticField\":\"staticValue\",\"regularField\":\"regularValue\"}", ModifiersClass.class);
    }

    @Ignore("Gson supports it")
    @Test(expected = JsonException.class)
    public void unmarshalTransientFieldIsNotSupported() throws Exception {
        ModifiersClass modifiersClass = jsonb.fromJson("{\"transientField\":\"transientValue\",\"regularField\":\"regularValue\"}", ModifiersClass.class);
    }

    /**
     * Why not ignore all not mapped content of JSON doc during unmarshalling
     * other then dictate strict one-to-one correspondence of JSON doc to POJO
     * It will give user much more flexibility in mapping only part of JSON doc he/she interested in
     */
    @Ignore("Gson supports it")
    @Test(expected = JsonException.class)
    public void unmarshalUnknownFieldIsNotSupported() throws Exception {
        ModifiersClass modifiersClass = jsonb.fromJson("{\"unknownField\":\"unknownValue\",\"regularField\":\"regularValue\"}", ModifiersClass.class);
    }

    static class ModifiersClass {
        public final String finalField = "finalValue";
        public static String staticField = "staticValue";
        public transient String transientField = "transientValue";
        public String regularField = "regularValue";

        public ModifiersClass() {}
    }
}
