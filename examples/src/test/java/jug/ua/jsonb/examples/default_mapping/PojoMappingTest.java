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
        Dog dogExp = new Dog();
        dogExp.setAge(1);
        dogExp.setName("Dog");

        Dog dogAct = jsonb.fromJson("{\"age\":1, \"name\":\"Dog\"}", Dog.class);
        assertEquals(dogExp.getAge(),dogAct.getAge());
        assertEquals(dogExp.getName(),dogAct.getName());
    }

    @Test
    public void inheritanceToJsonTest() {
        Dog pojo = new Dog();
        pojo.setAge(1);
        pojo.setName("Dog");

        String act = jsonb.toJson(pojo);
        assertEquals("{\"age\":1,\"name\":\"Dog\"}", act);
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


    public static class Animal {
        private int age;

        public Animal() {
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    public static class Dog extends Animal {
        private String name;

        public Dog() {
            super();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


    }

}
