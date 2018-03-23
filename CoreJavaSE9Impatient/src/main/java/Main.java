public class Main {

    public static void main(String[] args) {
        System.out.print("Hello");

        repeat(10, () -> System.out.println("Hello,	World!"));
        repeat2(10, i -> System.out.println("Countdown:	" + ( 9 - i)));
    }

    public static void repeat(int n, Runnable action) {
        for (int i = 0; i < n; i++)
            action.run();
    }

    public static void repeat2(int n, IntConsumer action) {
        for (int i = 0; i < n; i++)
            action.accept(i);
    }
}


interface IntConsumer {
    void accept(int value);
}
