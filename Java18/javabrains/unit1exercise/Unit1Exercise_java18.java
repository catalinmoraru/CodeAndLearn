package unit1exercise;

import unit1.Person;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;


public class Unit1Exercise_java18 {
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

        ListAllLambda listAllLambda = (peopleList) -> {
            for ( Person p : peopleList ) {
                System.out.println(p);

            }
        };

        System.out.println("Print all persons Java 1.8");
        /*listAllLambda.listAll(people);*/
        printConditionally(people, p -> true);


        // Step 3 : Create a method that prints all people that have last name beginning with C

        System.out.println("Print all persons with last name beginning with C Java 1.8");
        printLastNameBeginningWithC(people);


        printConditionally(people, p -> p.getLastName().startsWith("C"));

        System.out.println("Print all persons with first name beginning with C Java 1.8");
        printConditionally(people, p -> p.getFirstName().startsWith("C"));



    }


    public static void printLastNameBeginningWithC(List<Person> people){
        for (Person p : people){
            if ( p.getLastName().startsWith("C"))
                System.out.println(p);
        }
    }

    public static void printConditionally2(List<Person> people, Condition condition){
        for (Person p : people){
            if ( condition.test(p))
                System.out.println(p);
        }
    }

    public static void printConditionally(List<Person> people, Predicate<Person> predicate){
        for (Person p : people){
            if ( predicate.test(p))
                System.out.println(p);
        }
    }
}






