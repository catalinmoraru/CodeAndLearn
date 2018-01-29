import java.util.Arrays;

public class Main {
    public static void main( String[] args) {

        System.out.println("Hello");
        int[] A = new int[]{23171, 21011, 21123, 21366, 21013, 21367};
        System.out.println(solution(A));

    }

        // find the cost
        public static int solution(int[] A) {
            if (A.length == 0)
                return 0;
            int[] array = new int[A.length];
            array[0] = 0;
            for (int i = 1; i < A.length; i++) {
                array[i] = A[i] - A[i-1];
            }
            return goldenMaxSlice(array);
        }

        public static int goldenMaxSlice(int[] A) {
            int arrMax = Arrays.stream(A).max().getAsInt();
            if (arrMax < 0)
                return arrMax;
            int maxEnding = 0;
            int maxSlice = 0;
            for (int i = 0; i < A.length; i++) {
                maxEnding = (maxEnding + A[i])>0? (maxEnding + A[i]): 0;
                maxSlice = maxSlice>maxEnding? maxSlice:maxEnding;
            }
            return maxSlice;
        }

}
