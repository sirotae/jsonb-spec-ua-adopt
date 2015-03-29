package jug.ua.jsonb.examples.default_mapping;

import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.Optional;
import java.util.OptionalInt;

import static org.junit.Assert.*;

/**
 * @author Oleg Tsal-Tsalko
 */
public class OptionalMappingTest {

    Jsonb jsonb = JsonbBuilder.create();

    @Test
    public void serializeFromOptional() throws Exception {
        assertEquals("\"strValue\"", jsonb.toJson(Optional.of("strValue")));
        assertEquals("null", jsonb.toJson(Optional.ofNullable(null)));
        assertEquals("null", jsonb.toJson(Optional.empty()));
    }

    @Test
    public void serializeFromOptionalClass() throws Exception {
        //assertEquals("{\"optionalField\":null}", jsonb.toJson(new OptionalClass())); //This doesn't work in Gson, cause it's not consistent
        assertEquals("{}", jsonb.toJson(new OptionalClass()));

        OptionalClass optionalClass = new OptionalClass();
        optionalClass.optionalField = Optional.of("value");

        assertEquals("{\"optionalField\":\"value\"}", jsonb.toJson(optionalClass));

        OptionalClass nullOptionalClass = new OptionalClass();
        nullOptionalClass.optionalField = Optional.ofNullable(null);

        //assertEquals("{\"optionalField\":null}", jsonb.toJson(nullOptionalClass)); //This doesn't work in Gson, cause it's not consistent
        assertEquals("{}", jsonb.toJson(new OptionalClass()));

        OptionalClass nullOptionalField = new OptionalClass();
        nullOptionalField.optionalField = null;

        assertEquals("{}", jsonb.toJson(nullOptionalField));
    }

    @Test
    public void serializeFromSpecializedOptionalClass() throws Exception {
        //OptionalInt
        assertEquals("1", jsonb.toJson(OptionalInt.of(1)));
        assertEquals("null", jsonb.toJson(OptionalInt.empty()));
    }

    @Test
    public void deserializeToOptional() throws Exception {
        Optional<String> stringValue = jsonb.fromJson("\"optionalString\"", Optional.class);
        assertNotNull(stringValue);
        assertTrue(stringValue.isPresent());
        assertEquals("optionalString", stringValue.get());

        Optional<String> nullStringValue = jsonb.fromJson("null", Optional.class);
        assertNotNull(stringValue);
        assertFalse(nullStringValue.isPresent());
    }

    @Test
    public void deserializeToOptionalClass() throws Exception {
        OptionalClass optionalClass = jsonb.fromJson("{\"optionalField\":\"value\"}", OptionalClass.class);
        assertNotNull(optionalClass.optionalField);
        assertTrue(optionalClass.optionalField.isPresent());
        assertEquals("value", optionalClass.optionalField.get());

        OptionalClass emptyOptionalClass = jsonb.fromJson("{}", OptionalClass.class);
        assertNotNull(emptyOptionalClass.optionalField);
        assertFalse(emptyOptionalClass.optionalField.isPresent());

        OptionalClass nullOptionalClass = jsonb.fromJson("{\"optionalField\":null}", OptionalClass.class);
        assertNotNull(emptyOptionalClass.optionalField);
        assertFalse(emptyOptionalClass.optionalField.isPresent());
    }

    @Test
    public void deserializeToSpecializedOptionalClass() throws Exception {
        //OptionalInt
        OptionalInt optionalInt = jsonb.fromJson("1", OptionalInt.class);
        assertTrue(optionalInt.isPresent());
        assertTrue(optionalInt.getAsInt() == 1);

        OptionalInt emptyOptionalInt = jsonb.fromJson("null", OptionalInt.class);
        assertFalse(emptyOptionalInt.isPresent());
    }

    static class OptionalClass {
        public Optional<String> optionalField = Optional.empty();

        public OptionalClass() {}
    }
}
