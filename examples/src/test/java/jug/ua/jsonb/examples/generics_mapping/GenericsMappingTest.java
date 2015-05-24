package jug.ua.jsonb.examples.generics_mapping;

import com.google.gson.reflect.TypeToken;
import org.junit.Ignore;
import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * @author Oleg Tsal-Tsalko
 */
public class GenericsMappingTest {

    private Jsonb jsonb = JsonbBuilder.create();

    @Test
    public void serializeStandardGenericClass() throws Exception {
        MyGenericClass<String, Integer> myGenericClassField = new MyGenericClass<>();
        myGenericClassField.field1 = "value1";
        myGenericClassField.field2 = 3;

        assertEquals("{\"field1\":\"value1\",\"field2\":3}", jsonb.toJson(myGenericClassField));
    }

    @Test
    public void serializeCyclicGenericClass() throws Exception {
        MyCyclicGenericClass<CyclicSubClass> myCyclicGenericClass = new MyCyclicGenericClass<>();
        CyclicSubClass cyclicSubClass = new CyclicSubClass();
        cyclicSubClass.subField = "subFieldValue";
        myCyclicGenericClass.field1 = cyclicSubClass;

        assertEquals("{\"field1\":{\"subField\":\"subFieldValue\"}}", jsonb.toJson(myCyclicGenericClass));
    }

    @Ignore("Gson seems doesn't support second use case and returns NULL in second case")
    @Test
    public void serializeFunctionalInterfaceImplementations() throws Exception {
        FunctionalInterface<String> myFunction = () -> "value1";
        assertEquals("{\"value\":\"value1\"}", jsonb.toJson(myFunction));

        myFunction = new FunctionalInterface<String>() {
            private String value = "initValue";
            @Override
            public String getValue() {
                return value;
            }
            public void setValue(String value) {
                this.value = value;
            }
        };
        assertEquals("{\"value\":\"initValue\"}", jsonb.toJson(myFunction));
        //assertEquals("{}", jsonb.toJson(myFunction, new TypeToken<FunctionalInterface<String>>(){}.getType()));
    }

    @Test
    public void serializeNestedGenericWithConcreteParameterType() throws Exception {
        NestedGenericConcreteClass nestedGenericConcreteClass = new NestedGenericConcreteClass();
        nestedGenericConcreteClass.list = new ArrayList<>();
        nestedGenericConcreteClass.list.add("value1");

        assertEquals("{\"list\":[\"value1\"]}", jsonb.toJson(nestedGenericConcreteClass));
    }

    @Test
    public void serializeGenericWithWildcard() throws Exception {
        GenericWithWildcardClass genericWithWildcardClass = new GenericWithWildcardClass();
        List<Map<String, String>> list = new ArrayList<>();
        genericWithWildcardClass.wildcardList = list;
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("k1", "v1");
        list.add(stringMap);

        assertEquals("{\"wildcardList\":[{\"k1\":\"v1\"}]}", jsonb.toJson(genericWithWildcardClass));
    }

    @Test
    public void serializeMultiLevelGenerics() throws Exception {
        MyGenericClass<MyGenericClass<String, String>, Integer> multiLevelGeneric = new MyGenericClass<>();

        MyGenericClass<String, String> myGenericClass = new MyGenericClass<>();
        myGenericClass.field1 = "f1";
        myGenericClass.field2 = "f2";

        multiLevelGeneric.field1 = myGenericClass;
        multiLevelGeneric.field2 = 3;

        assertEquals("{\"field1\":{\"field1\":\"f1\",\"field2\":\"f2\"},\"field2\":3}", jsonb.toJson(multiLevelGeneric));
    }

    @Ignore("This example doesn't work and should be fixed!")
    @Test
    public void serializeBoundedGenerics() throws Exception {
        BoundedGenericClass<HashSet<Integer>, Circle> boundedGenericClass = new BoundedGenericClass<>();
        List<Shape> shapeList = new ArrayList<>();
        Circle circle = new Circle();
        circle.setRadius(2.5);
        shapeList.add(circle);
        boundedGenericClass.superList = shapeList;

        HashSet<Integer> intSet = new HashSet<>();
        intSet.add(3);

        boundedGenericClass.boundedSet = intSet;

        assertEquals("{\"boundedSet\":[3],\"superList\":[{\"radius\":2.5}]}", jsonb.toJson(boundedGenericClass));
    }

    @Test
    public void serializeListOfOptionals() throws Exception {
        List<Optional<String>> expected = Arrays.asList(Optional.empty(), Optional.ofNullable("first"), Optional.of("second"));
        String json = jsonb.toJson(expected);
        assertEquals("[null,\"first\",\"second\"]",json);
    }

    @Test
    public void deserializeIntoStandardGenericClass() throws Exception {
        MyGenericClass<String, Integer> myGenericInstance = jsonb.fromJson("{\"field1\":\"value1\", \"field2\":1}",
                new TypeToken<MyGenericClass<String, Integer>>(){}.getType());
        assertEquals("value1", myGenericInstance.field1);
        assertEquals(1, myGenericInstance.field2.intValue());

        MyGenericClass<MyGenericClass<String, String>, Integer> myGenericClass = jsonb.fromJson("{\"field1\":{\"field1\":\"f1\",\"field2\":\"f2\"},\"field2\":3}",
                new TypeToken<MyGenericClass<MyGenericClass<String, String>, Integer>>(){}.getType());
        assertEquals("f1", myGenericClass.field1.field1);
        assertEquals("f2", myGenericClass.field1.field2);
        assertEquals(3, myGenericClass.field2.intValue());
    }

    @Test
    public void deserializeIntoCyclicGenericClass() throws Exception {
        MyCyclicGenericClass<CyclicSubClass> myCyclicGenericClass = jsonb.fromJson("{\"field1\":{\"subField\":\"subFieldValue\"}}",
                new TypeToken<MyCyclicGenericClass<CyclicSubClass>>(){}.getType());
        assertEquals("subFieldValue", myCyclicGenericClass.field1.subField);
    }

    @Test
    public void deserializeIntoNestedGenericClass() throws Exception {
        NestedGenericConcreteClass nestedGenericConcreteClass = jsonb.fromJson("{\"list\":[\"value1\"]}", NestedGenericConcreteClass.class);
        assertEquals("value1", nestedGenericConcreteClass.list.get(0));
    }

    @Test
    public void deserializeIntoWildcardGenericClass() throws Exception {
        GenericWithWildcardClass genericWithWildcardClass = jsonb.fromJson("{\"wildcardList\":[{\"k1\":\"v1\"}]}", GenericWithWildcardClass.class);
        assert(genericWithWildcardClass.wildcardList.get(0) instanceof Map);
    }

    @Ignore("Gson default numeric type is Double, where JSON-B spec tends to Integer")
    @Test
    public void deserializeIntoMultiLevelGenericClass() throws Exception {
        MyGenericClass multiLevelGeneric = jsonb.fromJson("{\"field1\":{\"field1\":\"f1\",\"field2\":\"f2\"},\"field2\":3}", MyGenericClass.class);

        assert(multiLevelGeneric.field1 instanceof Map);
        assert(multiLevelGeneric.field2 instanceof Integer);
    }

    @Test
    public void deserializeIntoBoundedGenericClass() throws Exception {
        BoundedGenericClass<HashSet<Integer>, Circle> boundedGeneric = jsonb.fromJson("{\"boundedSet\":[3],\"superList\":[{\"radius\":2.5}]}",
                new TypeToken<BoundedGenericClass<HashSet<Integer>, Circle>>(){}.getType());
    }

    //For some reason in spec they are expecting JsonbException here, however it's more likely a bug
    @Test(expected = ClassCastException.class)
    public void runtimeClassCastException() throws Exception {
        BoundedGenericClass<HashSet<Integer>, Circle> boundedGeneric = jsonb.fromJson("{\"boundedSet\":[3],\"superList\":[{\"radius\":2.5}]}",
                new TypeToken<BoundedGenericClass<HashSet<Double>, Circle>>(){}.getType());
        HashSet<Integer> intSet = boundedGeneric.boundedSet;
        Integer intValue = intSet.iterator().next();
    }

    static class BoundedGenericClass<T extends Set<? extends Number>, U> {
        public T boundedSet;
        public List<? super U> superList;

        public BoundedGenericClass() {}
    }

    interface FunctionalInterface<T> {
        public T getValue();
    }

    static class MyGenericClass<T,U> {
        public T field1;
        public U field2;

        public MyGenericClass() {}
    }

    static class MyCyclicGenericClass<T extends MyCyclicGenericClass<? extends T>> {
        public T field1;

        public MyCyclicGenericClass() {}
    }

    static class CyclicSubClass extends MyCyclicGenericClass<CyclicSubClass> {
        public String subField;

        public CyclicSubClass() {}
    }

    static class NestedGenericConcreteClass {
        public List<String> list;

        public NestedGenericConcreteClass() {}
    }

    static class GenericWithWildcardClass {
        public List<?> wildcardList;

        public GenericWithWildcardClass() {}
    }

    static class Shape {
        private double area;

        public Shape() {
        }

        public double getArea() {
            return area;
        }

        public void setArea(double area) {
            this.area = area;
        }
    }

    static class Circle extends Shape {
        private double radius;

        public Circle() {
            super();
        }

        public double getRadius() {
            return radius;
        }

        public void setRadius(double radius) {
            this.radius = radius;
        }
    }
}
