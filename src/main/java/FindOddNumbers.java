import java.util.ArrayList;

public class FindOddNumbers {


    public static void main( String[] args) {

        System.out.print (oddNumbers(2,5).toString() );

    }

    static int[] oddNumbers(int l, int r) {
        int[] result = new int[100000];
        int j = 0;
        for ( int i = l ; i <= r ; i++ ){
            if ( i % 2 != 0){
                result[j] = i;
                j++;
            }
        }
        // result[0] = l;
        // result [j] = r;


        int[] goodresult = new int[j];

        for ( int i = 0; i < j ; i ++){
            goodresult[i] = result[i];
        }

        return goodresult;
    }


    static String findNumber(int[] arr, int k) {
        String result = "NO";
        for ( int i = 0; i < arr.length ; i ++ ){
            if ( arr[i] == k ){
                result = "YES";
                break;
            }
        }
        return result;
    }


}