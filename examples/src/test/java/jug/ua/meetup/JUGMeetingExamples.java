package jug.ua.meetup;

import com.google.gson.Gson;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.fail;

/**
 * Created by Olena_Syrota on 4/16/2015.
 */
public class JUGMeetingExamples {

    // 10 разные прикольные разнообразные примеры на которых будет видна разница разных API
    // advanced штуки 5 штук
    // gson, jackson, genson
    //примеры тестов
    //gson
    // инстанцирование дефолтное, инстанцирование с кастомизацией

    // POJO
    // primitives, null value
    // generics (TypeToken)
    // когда передаем невалидные экзамплы
    // advanced через type adapters - OptionalMapping - не сапортиться сейчас из коробки
    // Dates с type adapter (custom serializer)

    private Gson gson = new Gson();

    private static class ContainsReferenceToSelfType {
        Collection<ContainsReferenceToSelfType> children = new ArrayList<ContainsReferenceToSelfType>();
    }

    @Test
    public void testCircularSerialization() throws Exception {
        ContainsReferenceToSelfType a = new ContainsReferenceToSelfType();
        ContainsReferenceToSelfType b = new ContainsReferenceToSelfType();
        a.children.add(b);
        b.children.add(a);
        try {
            gson.toJson(a);
            fail("Circular types should not get printed!");
        } catch (StackOverflowError expected) {
        }
    }

}
