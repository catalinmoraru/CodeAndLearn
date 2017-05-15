package unit3;

import unit1.Person;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class MethodReferenceExample2 {
    public static void main(String args[]){
        List<Person> people = Arrays.asList(
                new Person ("Charles","Dickens",60),
                new Person ("Lewis ","Carol",60),
                new Person ("Thomas","Carlyle",60),
                new Person ("Charlotte","Bronte",60),
                new Person ("Mathew","Arnold",60)
        );






        System.out.println("Print all persons Java 1.8");
        /*listAllLambda.listAll(people);*/
        performConditionally(people, p -> true, System.out::println); // p -> method(p)

    }




    public static void performConditionally(List<Person> people, Predicate<Person> predicate, Consumer<Person> consumer){
        for (Person p : people){
            if ( predicate.test(p))
                consumer.accept(p);
        }
    }
}
