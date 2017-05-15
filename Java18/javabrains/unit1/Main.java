package unit1;

public class Main {

    public void greet(Greeting greeting){
        greeting.perform();
    }

    public static void main(String args[]){
        Main greeter = new Main();

/*        greetingFunction = () -> System.out.print("Hello world");
        doubleNumber = ( int a ) -> a * 2;

        addFunction = ( int a , int b) -> a + b;
        safeDivideFunction = ( int a , int b ) -> {
            if ( b == 0 ) return 0;
            return a / b;
        };

        stringLengthCountFunction = ( String s ) -> s.length();*/


        Greeting myLamdaFunction = () -> System.out.print("Hello world");
        MyAdd addFunction = ( int a, int b) -> a + b;

        Greeting innerClassGreeting = new Greeting(){
            public void perform(){
                System.out.println("Hello world!");
            }

        };

        myLamdaFunction.perform();
        innerClassGreeting.perform();

        greeter.greet(myLamdaFunction);
        greeter.greet(innerClassGreeting);
    }
}

interface MyLambda {
    void foo();
}

interface MyAdd{
    int add(int a,int b);
}

@FunctionalInterface
interface Greeting{
    public void perform();
    /*public void perform2();*/
}

class GreetingClass implements Greeting{

    @Override
    public void perform() {

    }
}



