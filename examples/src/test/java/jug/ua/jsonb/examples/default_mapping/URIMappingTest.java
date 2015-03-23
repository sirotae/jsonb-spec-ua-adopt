package jug.ua.jsonb.examples.default_mapping;

import org.junit.Ignore;
import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import static org.junit.Assert.assertEquals;

/**
 * @author Oleg Tsal-Tsalko
 */
public class URIMappingTest {

    private Jsonb jsonb = JsonbBuilder.create();

    @Test
    public void fromJsonToURL() throws Exception {
        java.net.URL url = jsonb.fromJson("\"https://www.jcp.org/en/jsr/detail?id=367#3\"", java.net.URL.class);
        java.net.URI uri = jsonb.fromJson("\"mailto:users@jsonb-spec.java.net\"", java.net.URI.class);
    }

    @Ignore
    @Test
    public void urlToJson() throws Exception {
        java.net.URL url = new java.net.URL("https://www.jcp.org/en/jsr/detail?id=367#3");
        assertEquals("\"https://www.jcp.org/en/jsr/detail?id=367#3\"", jsonb.toJson(url)); //I think Gson bug here?
        java.net.URI uri = new java.net.URI("mailto:users@jsonb-spec.java.net");
        assertEquals("\"mailto:users@jsonb-spec.java.net\"", jsonb.toJson(uri));
    }

}
