package unit1;

public class RunnableExample {
    public static void main(String args[]){
        Thread myTread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Printed inside runnable");
            }
        });

        myTread.start();

        Thread myLambdaThread = new Thread(() -> System.out.println("Printed inside lambda runnable"));

        myLambdaThread.start();
    }

}
