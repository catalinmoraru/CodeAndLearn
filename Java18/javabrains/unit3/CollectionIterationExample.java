package unit3;

import unit1.Person;

import java.util.Arrays;
import java.util.List;

public class CollectionIterationExample {
    public static void main(String args[]) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Lewis ", "Carol", 60),
                new Person("Thomas", "Carlyle", 60),
                new Person("Charlotte", "Bronte", 60),
                new Person("Mathew", "Arnold", 60)
        );

        // External Iterators - do it imperativelly
        System.out.println("Using for loop");

        for (int i = 0 ; i < people.size() ; i ++){
            System.out.println(people.get(i));
        }

        System.out.println("Using for in/each loop");

        for (Person p : people){
            System.out.println(p);
        }

        // Internal iterators - tell it that you intent

        System.out.println("Using foreach lambdas");
        people.forEach( System.out::println);




    }
}
