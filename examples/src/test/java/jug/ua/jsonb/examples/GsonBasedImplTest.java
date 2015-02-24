package jug.ua.jsonb.examples;

import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author Oleg Tsal-Tsalko
 */
public class GsonBasedImplTest {

    @Test
    public void simpleTransformationBackAndForth() throws Exception {
        Jsonb jsonb = JsonbBuilder.newBuilder()
                .withConfig(new JsonbConfig().toJsonFormatting(true))
                .build();
        String json = jsonb.toJson(new DomainObject());
        System.out.println(json);
        DomainObject obj = jsonb.fromJson(json, DomainObject.class);
        assertEquals(new DomainObject(), obj);
    }

    class DomainObject{
        private String name = "DomainObject";
        private Collection<Integer> collection = Arrays.asList(1, 2, 3, 4, 5);
        private String[] array = {"Oleg", "Yulya"};
        private Map<String, String> map;

        public DomainObject(){
            map = new HashMap<>();
            map.put("key1", "value1");
            map.put("key2", "777");
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof DomainObject)) return false;

            DomainObject that = (DomainObject) o;

            if (!Arrays.equals(array, that.array)) return false;
            if (collection != null ? !collection.equals(that.collection) : that.collection != null) return false;
            if (map != null ? !map.equals(that.map) : that.map != null) return false;
            if (name != null ? !name.equals(that.name) : that.name != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (collection != null ? collection.hashCode() : 0);
            result = 31 * result + (array != null ? Arrays.hashCode(array) : 0);
            result = 31 * result + (map != null ? map.hashCode() : 0);
            return result;
        }
    }
}
