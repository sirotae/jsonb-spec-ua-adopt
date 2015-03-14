package jug.ua.jsonb.examples.default_mapping;

import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by sirotae on 3/5/2015.
 */
public class PojoMappingTest {

    Jsonb jsonb = JsonbBuilder.create();

    @Test
    public void pojoFromJsonTest() {
        POJO pojoExp = new POJO();
        pojoExp.setId(1);
        pojoExp.setName("pojoName");

        POJO pojoAct = jsonb.fromJson("{\"id\":1, \"name\":\"pojoName\"}", POJO.class);
        assertEquals(pojoExp,pojoAct);
    }

    @Test
    public void pojoToJsonTest() {
        POJO pojo = new POJO();
        pojo.setId(1);
        pojo.setName("pojoName");

        String act = jsonb.toJson(pojo);
        assertEquals("{\"id\":1,\"name\":\"pojoName\"}", act);
    }

    @Test
    public void inheritanceFromJsonTest() {
        SubClass exp = new SubClass();
        exp.setSuperclassField1(1);
        exp.setSubclassField1("Dog");

        SubClass act = jsonb.fromJson("{\"superclassField1\":1, \"subclassField1\":\"Dog\"}", SubClass.class);
        assertEquals(exp.getSubclassField1(),act.getSubclassField1());
        assertEquals(exp.getSuperclassField1(),act.getSuperclassField1());
    }

    @Test
    public void inheritanceToJsonTest() {
        SubClass pojo = new SubClass();
        pojo.setSuperclassField1(1);
        pojo.setSubclassField1("Dog");

        String act = jsonb.toJson(pojo);
        assertEquals("{\"subclassField1\":\"Dog\",\"superclassField1\":1}", act);
    }

    @Test
    public void compositeFromJsonTest() {
        OneToOneCompositeClass pojo = new OneToOneCompositeClass();
        InjectedClass injected = new InjectedClass();
        injected.setInjectedObjectField1("c1");
        injected.setInjectedObjectField2("c2");
        pojo.setField1(1);
        pojo.setInjectedObject(injected);

        OneToOneCompositeClass pojoAct = jsonb.fromJson("{\"field1\":1, \"injectedObject\": {\"injectedObjectField1\":\"c1\", \"injectedObjectField2\":\"c2\"}}", OneToOneCompositeClass.class);
        assertEquals(pojo,pojoAct);
    }

    @Test
    public void compositeToJsonTest() {
        OneToOneCompositeClass pojo = new OneToOneCompositeClass();
        InjectedClass injected = new InjectedClass();
        injected.setInjectedObjectField1("c1");
        injected.setInjectedObjectField2("c2");
        pojo.setField1(1);
        pojo.setInjectedObject(injected);

        String act = jsonb.toJson(pojo);
        assertEquals("{\"field1\":1,\"injectedObject\":{\"injectedObjectField1\":\"c1\",\"injectedObjectField2\":\"c2\"}}", act);
    }


    private static class POJO {
        private Integer id;
        private String name;

        public POJO() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof POJO)) return false;

            POJO pojo = (POJO) o;

            if (id != null ? !id.equals(pojo.id) : pojo.id != null) return false;
            if (name != null ? !name.equals(pojo.name) : pojo.name != null) return false;

            return true;
        }

        //other supported attributes
    }


    public static class SuperClass {
        private int superclassField1;

        public SuperClass() {
        }

        public int getSuperclassField1() {
            return superclassField1;
        }

        public void setSuperclassField1(int superclassField1) {
            this.superclassField1 = superclassField1;
        }

    }

    public static class SubClass extends SuperClass {
        private String subclassField1;

        public SubClass() {
            super();
        }

        public String getSubclassField1() {
            return subclassField1;
        }

        public void setSubclassField1(String subclassField1) {
            this.subclassField1 = subclassField1;
        }
    }


    public static class OneToOneCompositeClass {

        private int field1;
        private InjectedClass injectedObject;

        public int getField1() {
            return field1;
        }

        public void setField1(int field1) {
            this.field1 = field1;
        }

        public InjectedClass getInjectedObject() {
            return injectedObject;
        }

        public void setInjectedObject(InjectedClass injectedObject) {
            this.injectedObject = injectedObject;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            OneToOneCompositeClass that = (OneToOneCompositeClass) o;

            if (field1 != that.field1) return false;
            if (injectedObject != null ? !injectedObject.equals(that.injectedObject) : that.injectedObject != null) return false;

            return true;
        }
    }

    public class InjectedClass {

        private String injectedObjectField1;
        private String injectedObjectField2;

        public String getInjectedObjectField1() {
            return injectedObjectField1;
        }

        public void setInjectedObjectField1(String injectedObjectField1) {
            this.injectedObjectField1 = injectedObjectField1;
        }

        public String getInjectedObjectField2() {
            return injectedObjectField2;
        }

        public void setInjectedObjectField2(String injectedObjectField2) {
            this.injectedObjectField2 = injectedObjectField2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            InjectedClass that = (InjectedClass) o;

            if (injectedObjectField1 != null ? !injectedObjectField1.equals(that.injectedObjectField1) : that.injectedObjectField1 != null)
                return false;
            if (injectedObjectField2 != null ? !injectedObjectField2.equals(that.injectedObjectField2) : that.injectedObjectField2 != null)
                return false;

            return true;
        }

    }
}
