import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PrintingStreams {


    public static void main(String[] args) {
        Stream<Integer> infiniteStream = Stream.iterate(1,x -> x +1);
        Stream<String> finiteStream = Stream.of("Alex","Tibi","Darius","Cata","Thomas");
        Stream<String> finiteStream1 = Stream.of("1","2","3","4","5");
        Stream<String> finiteStream2 = Stream.of("Alex","Tibi","Darius","Cata","Thomas2");



        finiteStream.forEach(System.out::println);

        finiteStream1.peek(System.out::println).count();

        System.out.println(finiteStream2.collect(Collectors.toList()));


        infiniteStream.limit(5).forEach(System.out::print);



    }




}
