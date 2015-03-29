package jug.ua.jsonb.examples.default_mapping;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import static org.junit.Assert.assertEquals;

/**
 * Tests of String values according to http://www.ecma-international.org/publications/files/ECMA-ST/ECMA-404.pdf
 * Created by sirotae on 3/2/2015.
 */
public class StringMappingTest {

    Jsonb jsonb = JsonbBuilder.create();

    @Test
    public void charFromJsonTest() {
        char actual = jsonb.fromJson("\"1\"", char.class);
        assertEquals('1', actual);
    }

    @Test
    public void charToJsonTest() {
        String actual = jsonb.toJson('a');
        assertEquals("\"a\"", actual);
    }


    @Test
    public void stringFromJsonTest() {
        String actual = jsonb.fromJson("\"String mapping\"", String.class);
        assertEquals("String mapping", actual);
    }

    @Test
    public void stringToJsonTest() {
        String actual = jsonb.toJson("String mapping");
        assertEquals("\"String mapping\"", actual);
    }


    @Test
    public void emptyStringFromJsonTest() {
        String actual = jsonb.fromJson("\"\"", String.class);
        assertEquals("", actual);

    }

    @Test
    public void emptyStringToJsonTest() {
        String actual = jsonb.toJson("");
        assertEquals("\"\"", actual);
    }

    @Test
    public void quoteFromJsonTest() {
        String actual = jsonb.fromJson("\"\\\"\"", String.class);
        assertEquals("\"", actual);

    }

    @Test
    public void quoteToJsonTest() {
        String actual = jsonb.toJson("\"");
        assertEquals("\"\\\"\"", actual);
    }

    @Test
    public void backslashFromJsonTest() {
        String actual = jsonb.fromJson("\"\\\\\"", String.class);
        assertEquals("\\", actual);

    }

    @Test
    public void backslashToJsonTest() {
        String actual = jsonb.toJson("\\");
        assertEquals("\"\\\\\"", actual);
    }

    @Test
    public void slashFromJsonTest() {
        String actual = jsonb.fromJson("\"\\/\"", String.class);
        assertEquals("/", actual);
    }

    @Test
    @Ignore("this test is failed in gson implementation. slash should be escaped with \\ in json value")
    public void slashToJsonTest() {
        String actual = jsonb.toJson("/");
        assertEquals("\"\\/\"", actual);
    }

    @Test
    public void backspaceFromJsonTest() {
        String actual = jsonb.fromJson("\"\\b\"", String.class);
        assertEquals("\b", actual);
    }

    @Test
    public void backspaceToJsonTest() {
        String actual = jsonb.toJson("\b");
        assertEquals("\"\\b\"", actual);
    }

    @Test
    public void formfeedFromJsonTest() {
        String actual = jsonb.fromJson("\"\\f\"", String.class);
        assertEquals("\f", actual);
    }

    @Test
    public void formfeedToJsonTest() {
        String actual = jsonb.toJson("\f");
        assertEquals("\"\\f\"", actual);
    }

    @Test
    public void linefeedFromJsonTest() {
        String actual = jsonb.fromJson("\"\\n\"", String.class);
        assertEquals("\n", actual);
    }

    @Test
    public void linefeedToJsonTest() {
        String actual = jsonb.toJson("\n");
        assertEquals("\"\\n\"", actual);
    }

    @Test
    public void carriageReturnFromJsonTest() {
        String actual = jsonb.fromJson("\"\\r\"", String.class);
        assertEquals("\r", actual);
    }

    @Test
    public void carriageReturnToJsonTest() {
        String actual = jsonb.toJson("\r");
        assertEquals("\"\\r\"", actual);
    }

    @Test
    public void tabReturnFromJsonTest() {
        String actual = jsonb.fromJson("\"\\t\"", String.class);
        assertEquals("\t", actual);
    }

    @Test
    public void tabReturnToJsonTest() {
        String actual = jsonb.toJson("\t");
        assertEquals("\"\\t\"", actual);
    }

    @Test
    public void hexCodeFromJsonTest() {
        String actual = jsonb.fromJson("\"\\u0039\"", String.class);
        assertEquals("9", actual);
    }

    @Test
    public void hexCodeReturnToJsonTest() {
        String actual = jsonb.toJson("\u0039");
        assertEquals("\"9\"", actual);
    }

    @Test
    public void fullEscapingFromJsonTest() {
        String escapedString = jsonb.fromJson("\" \\\" \\\\ \\/ \\b \\f \\n \\r \\t \\u0039\"", String.class);
        assertEquals(" \" \\ / \b \f \n \r \t 9", escapedString);
    }

    @Test
    public void fullEscapingToJsonTest() {
        String actual = jsonb.toJson(" \" \\ / \b \f \n \r \t \u0039");
        assertEquals("\" \\\" \\\\ / \\b \\f \\n \\r \\t 9\"", actual);
    }

}
