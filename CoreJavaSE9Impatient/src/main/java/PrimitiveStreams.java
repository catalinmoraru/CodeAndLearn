import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrimitiveStreams {
    public static void main(String[] args) {

        Stream<Integer> stream = Stream.of(1, 2, 3);
        System.out.println(stream.reduce(0, (s, n) -> s + n));

        Stream<Integer> stream1 = Stream.of(1, 2, 3);
        System.out.println(stream1.mapToInt(x -> x).sum());

        IntStream intStream = IntStream.of(1, 2, 3, 4);
        OptionalDouble avg = intStream.average();
        System.out.println(avg.getAsDouble());
        System.out.println();

        DoubleStream oneValue = DoubleStream. of (3.14);
        DoubleStream varargs = DoubleStream. of (1.0, 1.1, 1.2);
        oneValue.forEach(System.out::println);

        varargs.forEach(System.out::println);


        System.out.println();
        DoubleStream random = DoubleStream. generate (Math::random);
        DoubleStream fractions = DoubleStream. iterate (2, d -> d * 2);
        random.limit(3).forEach(System.out::println);
        fractions.limit(10).forEach(System.out::println);

        System.out.println();
        IntStream range = IntStream.range(1, 6);
        range.forEach(System.out::print);
        System.out.println();
        IntStream rangeClosed = IntStream.rangeClosed(1, 5);
        rangeClosed.forEach(System.out::print);
        System.out.println();
        System.out.println();


        Stream<String> objStream = Stream.of("penguin", "fish");
        IntStream intStream2 = objStream.mapToInt(s -> s.length());

        intStream2.forEach(System.out::println);


        IntStream stream2 = IntStream.rangeClosed(1,1111111111);
        OptionalDouble optionalDouble = stream2.average();
        // System.out.println("average:"+optionalDouble);

        optionalDouble.ifPresent(System.out::println);
        System.out.println(optionalDouble.getAsDouble());
        System.out.println(optionalDouble.orElseGet(() -> Double.NaN));

        System.out.println();
        System.out.println();

        DoubleStream doubles = DoubleStream.generate(() -> Math.PI);
        doubles.limit(12).forEach(System.out::println);
        System.out.println();
        System.out.println(range(IntStream.rangeClosed(1,10)));

        Optional<Integer> opt = Optional.of(123);
        threeDigit(opt);


    }

    private static int range(IntStream ints) {
        IntSummaryStatistics stats = ints.summaryStatistics();
        if (stats.getCount() == 0) throw new RuntimeException();
        return stats.getMax() - stats.getMin();
    }


    private static void threeDigit(Optional<Integer> optional) {
        optional.map(n -> "" + n) // part 1
                .filter(s -> s.length() == 3) // part 2
                .ifPresent(System.out::println); // part 3
    }
}
