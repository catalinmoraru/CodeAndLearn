package unit2;

public class ClosuresExample {
    public static void main(){
        int a = 10;
        int b = 20;

        // b is effectivelly final ; and it's value is passed
        // you don't have to be final I trust you,but I'll catch you if you can
        doProcess(a, new Process() {

            @Override
            public void process(int i) {
                System.out.println(i + b);
            }
        });

        // closure; b is frozen and carried whenever the lambda is executed
        doProcess(a, i -> System.out.println(i + b));

    }

    public static void doProcess(int i, Process p){
        p.process(i);
    }
}

interface Process{
    void process(int i);
}
