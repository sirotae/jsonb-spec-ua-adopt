package gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * @author Oleg Tsal-Tsalko
 */
public class GsonSimpleExamplesTest {

    private Gson gson = new Gson();

    @Test
    public void primitiveTypesSerializationUsingDefaultGsonInstance() throws Exception {
        print(gson.toJson(1));
        print(gson.toJson("abcd"));
        print(gson.toJson(new Long(10)));
        int[] values = { 1 };
        print(gson.toJson(values));
    }

    @Test
    public void primitiveTypesDeserializationUsingDefaultGsonInstance() throws Exception {
        int one = gson.fromJson("1", int.class);
        Integer oneInteger = gson.fromJson("1", Integer.class);
        Long oneLong = gson.fromJson("1", Long.class);
        Boolean false_ = gson.fromJson("false", Boolean.class);
        String str = gson.fromJson("\"abc\"", String.class);
        String[] anotherStr = gson.fromJson("[\"abc\"]", String[].class);
    }

    /**
     * It is perfectly fine (and recommended) to use private fields
     * There is no need to use any annotations to indicate a field is to be included for serialization and deserialization. All fields in the current class (and from all super classes) are included by default.
     * If a field is marked transient, (by default) it is ignored and not included in the JSON serialization or deserialization.
     * This implementation handles nulls correctly
     * While serialization, a null field is skipped from the output
     * While deserialization, a missing entry in JSON results in setting the corresponding field in the object to null
     * If a field is synthetic, it is ignored and not included in JSON serialization or deserialization
     * Fields corresponding to the outer classes in  inner classes, anonymous classes, and local classes are ignored and not included in serialization or deserialization
     */
    @Test
    public void simpleObjectSerializingAndDeserializing() throws Exception {
        BagOfPrimitives obj = new BagOfPrimitives();
        String json = gson.toJson(obj);
        print(json);
        assertEquals(obj, gson.fromJson(json, BagOfPrimitives.class));
    }

    @Test
    public void collectionsSerializingAndDeserializing() throws Exception {
        Collection<Integer> ints = Arrays.asList(1, 2, 3, 4, 5);
        String json = gson.toJson(ints);
        print(json);
        Type collectionType = new TypeToken<Collection<Integer>>(){}.getType();
        Collection<Integer> ints2 = gson.fromJson(json, collectionType);
        assertEquals(ints, ints2);
    }

    @Test
    public void serializingJsonUsingGsonBuilder() throws Exception {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(new DomainObject());
        print(json);
        // There is no possibility to create generic Object structure without explicitly passing type argument
        DomainObject obj = gson.fromJson(json, DomainObject.class);
    }

    class DomainObject{
        private String name = "DomainObject";
        private BagOfPrimitives bag = new BagOfPrimitives();
        private Collection<Integer> collection = Arrays.asList(1, 2, 3, 4, 5);
        private String[] array = {"Oleg", "Yulya"};
        private Map<String, Object> map;

        public DomainObject(){
            map = new HashMap<>();
            map.put("key1", "value1");
            map.put("key2", 777);
        }
    }

    class BagOfPrimitives {
        private int value1 = 1;
        private String value2;
        private transient int value3 = 3;
        BagOfPrimitives() {
            // no-args constructor
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof BagOfPrimitives)) return false;

            BagOfPrimitives that = (BagOfPrimitives) o;

            if (value1 != that.value1) return false;
            if (value2 != null ? !value2.equals(that.value2) : that.value2 != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = value1;
            result = 31 * result + (value2 != null ? value2.hashCode() : 0);
            return result;
        }
    }

    static void print(String str){
        System.out.println(str);
    }
}
