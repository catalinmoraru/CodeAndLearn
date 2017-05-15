package unit3;

import unit1.Person;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamsExample1 {
    public static void main(String args[]) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Lewis ", "Carol", 60),
                new Person("Thomas", "Carlyle", 60),
                new Person("Charlotte", "Bronte", 60),
                new Person("Mathew", "Arnold", 60)
        );

        // one loop for detecting ; one loop for printing the sublist

        // use assembly line = stream
        // each step can change the stream before it gets to the next step
        // stream think of it as a view

        // source of the stream + operations on the stream + terminal operation wich gives the start to act
        // each operation takes a lambda expression

        people.stream().forEach(System.out::println);

        people.stream()
                .filter(p -> p.getLastName().startsWith("C"))
                .forEach(p -> System.out.println(p.getFirstName()));


        System.out.println(people.stream()
                .filter(p -> p.getLastName().startsWith("D"))
                .count());

        // lambda expressions enable parallel processing
        long count = people.parallelStream().count();
        System.out.println(count);


    }
}
