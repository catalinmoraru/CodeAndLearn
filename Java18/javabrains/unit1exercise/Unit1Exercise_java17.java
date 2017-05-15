package unit1exercise;

import unit1.Person;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Unit1Exercise_java17 {
    public static void main(String args[]){
        List<Person> people = Arrays.asList(
                new Person ("Charles","Dickens",60),
                new Person ("Lewis ","Carol",60),
                new Person ("Thomas","Carlyle",60),
                new Person ("Charlotte","Bronte",60),
                new Person ("Mathew","Arnold",60)
        );



        // Step 1 : sort list by last name

        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getLastName().compareTo(o2.getLastName());
            }
        });

        // Step 2 : Create a method that prints all elements in the list

        ListAllLambda listAllLambda = (peopleList) -> {
            for ( Person p : peopleList ) {
                System.out.println(p);

            }
        };

        System.out.println("Print all persons Java 1.7");
        printAll(people);
        System.out.println("Print all persons Java 1.8");
        listAllLambda.listAll(people);


        // Step 3 : Create a method that prints all people that have last name beginning with C

        System.out.println("Print all persons with last name beginning with C Java 1.7");
        printLastNameBeginningWithC(people);


        printConditionally(people, new Condition() {
            @Override
            public boolean test(Person p) {
                return p.getLastName().startsWith("C");
            }
        });

        System.out.println("Print all persons with first name beginning with C Java 1.7");
        printConditionally(people, new Condition() {
            @Override
            public boolean test(Person p) {
                return p.getFirstName().startsWith("C");
            }
        });



    }


    public static void printAll(List<Person> people){
        for (Person p : people){
            System.out.println(p);
        }
    }

    public static void printLastNameBeginningWithC(List<Person> people){
        for (Person p : people){
            if ( p.getLastName().startsWith("C"))
                System.out.println(p);
        }
    }

    public static void printConditionally(List<Person> people, Condition condition){
        for (Person p : people){
            if ( condition.test(p))
                System.out.println(p);
        }
    }
}

