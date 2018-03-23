import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class PrintCats {


    public static void main(String[] args) {
        List<String> cats = Arrays.asList("Annie", "Ripley");
        for(String cat: cats)
            System.out.println(cat);

        cats.forEach(System.out::println);

        String value ="s";
        Optional o = Optional.ofNullable(value);

        System.out.println(o.get());

        Stream<String> infinite = Stream.generate(() -> "chimp");

        Stream<String> s3 = Stream.of("Monkey", "Gorilla", "Bonobo");

        // infinite.forEach(System.out::print);
        s3.forEach(System.out::print);

        Stream<String> stream = Stream.of("w", "o", "l", "f");
        String word = stream.reduce("", (s, c) -> s + c);
        System.out.println(word);

        Stream<Integer> integerStream = Stream.of(1,2,3,4,5,6,7,0);
        Integer prod = integerStream.reduce(1, ( a1, b1 ) -> a1 * b1 );
        System.out.println(prod);



        Stream<String> bearStream = Stream.of("black bear", "brown bear", "grizzly");
        long count = bearStream.filter(s -> s.startsWith("g")) // grizzly
                .peek(System.out::println).count(); // 1
        System.out.println(count);


        // express what not how
        // get the first two names alphabetically that are four characters long
        List<String> list = Arrays.asList("Toby", "Anna", "Leroy", "Alex");
        stream.filter(n -> n.length() == 4)
                .sorted()
                .limit(2)
                .forEach(System.out::println);

    }


}
