package jug.ua.jsonb.examples.default_mapping;

import org.junit.Before;
import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by sirotae on 3/5/2015.
 */
public class PojoMappingTest {

    Jsonb jsonb;

    @Before
    public void init() {
        jsonb = JsonbBuilder.create();
    }


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

    public void compositeFromJsonTest() {
        ClassWithOneToOneComposite pojo = new ClassWithOneToOneComposite();
        Composited composited = new Composited();
        composited.setCompositedField1("c1");
        composited.setCompositedField2("c2");
        pojo.setField1(1);
        pojo.setComposited(composited);

        POJO pojoAct = jsonb.fromJson("{\"field1\":1, \"composited\": {\"compositedField1\":\"c1\", \"compositedField2\":\"c2\"}}", POJO.class);
        assertEquals(pojo,pojoAct);
    }

    public void compositeToJsonTest() {
        ClassWithOneToOneComposite pojo = new ClassWithOneToOneComposite();
        Composited composited = new Composited();
        composited.setCompositedField1("c1");
        composited.setCompositedField2("c2");
        pojo.setField1(1);
        pojo.setComposited(composited);

        String act = jsonb.toJson(pojo);
        assertEquals("{\"field1\":1, \"composited\": {\"compositedField1\":\"c1\", \"compositedField2\":\"c2\"}}", act);
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


    public static class ClassWithOneToOneComposite {
        private int field1;
        private Composited composited;

        public int getField1() {
            return field1;
        }

        public void setField1(int field1) {
            this.field1 = field1;
        }

        public Composited getComposited() {
            return composited;
        }

        public void setComposited(Composited composited) {
            this.composited = composited;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ClassWithOneToOneComposite that = (ClassWithOneToOneComposite) o;

            if (field1 != that.field1) return false;
            if (composited != null ? !composited.equals(that.composited) : that.composited != null) return false;

            return true;
        }
    }

    public class Composited {
        private String compositedField1;
        private String compositedField2;

        public String getCompositedField1() {
            return compositedField1;
        }

        public void setCompositedField1(String compositedField1) {
            this.compositedField1 = compositedField1;
        }

        public String getCompositedField2() {
            return compositedField2;
        }

        public void setCompositedField2(String compositedField2) {
            this.compositedField2 = compositedField2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Composited that = (Composited) o;

            if (compositedField1 != null ? !compositedField1.equals(that.compositedField1) : that.compositedField1 != null)
                return false;
            if (compositedField2 != null ? !compositedField2.equals(that.compositedField2) : that.compositedField2 != null)
                return false;

            return true;
        }

    }
}
