package unit1;

public class TypeInferenceExample {
    public static void main(String[] args){
        StringLengthLamba myLambda = s -> s.length();

        System.out.println(myLambda.getLength("Hello Lambda"));

        printLambda(myLambda);
        printLambda(s -> s.length());

    }

    public static void printLambda(StringLengthLamba l){
        System.out.println(l.getLength("Heloooooo Lambda!"));
    }

    interface StringLengthLamba{
        int getLength(String s);
    }

}
