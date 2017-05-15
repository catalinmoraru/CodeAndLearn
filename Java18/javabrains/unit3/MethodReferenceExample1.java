package unit3;

public class MethodReferenceExample1 {
    public static void main(String args[]){
        Thread t = new Thread(() -> printMessage());
        t.start();

        Thread t2 = new Thread((MethodReferenceExample1::printMessage)); // () -> method()
        // identical with above ; shortcut when just executing a method
        // jsut pass through





    }

    public static void  printMessage(){
        System.out.print("Hello");
    }


}
