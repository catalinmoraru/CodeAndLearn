package unit2;

import unit1.Person;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.function.Consumer;
import java.util.function.Predicate;


public class StandardFunctionalInterfacesExample {
    public static void main(String args[]){
        List<Person> people = Arrays.asList(
                new Person ("Charles","Dickens",60),
                new Person ("Lewis ","Carol",60),
                new Person ("Thomas","Carlyle",60),
                new Person ("Charlotte","Bronte",60),
                new Person ("Mathew","Arnold",60)
        );



        // Step 1 : sort list by last name

        Collections.sort(people, (p1, p2) -> p1.getLastName().compareTo(p2.getLastName()));

        // Step 2 : Create a method that prints all elements in the list


        System.out.println("Print all persons Java 1.8");
        /*listAllLambda.listAll(people);*/
        performConditionally(people, p -> true, p -> System.out.println(p));


        // Step 3 : Create a method that prints all people that have last name beginning with C

        System.out.println("Print all persons with last name beginning with C Java 1.8");
        printLastNameBeginningWithC(people);


        performConditionally(people, p -> p.getLastName().startsWith("C"),p -> System.out.println(p));

        System.out.println("Print all persons with first name Java 1.8");
        performConditionally(people, p -> p.getFirstName().startsWith("C"),p -> System.out.println(p.getFirstName()));
    }


    public static void printLastNameBeginningWithC(List<Person> people){
        for (Person p : people){
            if ( p.getLastName().startsWith("C"))
                System.out.println(p);
        }
    }


    public static void performConditionally(List<Person> people, Predicate<Person> predicate, Consumer<Person> consumer){
        for (Person p : people){
            if ( predicate.test(p))
                consumer.accept(p);
        }
    }
}







