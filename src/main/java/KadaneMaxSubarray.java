import java.util.Arrays;

public class KadaneMaxSubarray {

    private static void maxSubArray(int[] input) {

        int localMax = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int index = 0; index < input.length; index++) {

            localMax = localMax + input[index];
            localMax = Math.max(0, localMax);
            maxSum = Math.max(maxSum, localMax);
        }

        System.out.printf("%d",maxSum);
    }

    public static void main(String[] args) {

        int array[] = { -2, 2, 1, -3, 1};
        String sArray = Arrays.toString(array);

        System.out.printf("1. Max sum sub array in %s is : ",sArray);
        maxSubArray(array);

        array = new int[]{ -2, 2, 3, -3, 4};
        sArray = Arrays.toString(array);

        System.out.printf("\n2. Max sum sub array in %s is : ",sArray);
        maxSubArray(array);

        array = new int[]{ 1 , 2, -4, 4, -1, -2, 4};
        sArray = Arrays.toString(array);
        System.out.printf("\n3. Max sum sub array in %s is : ",sArray);
        maxSubArray(array);
    }
}
