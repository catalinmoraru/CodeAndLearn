import java.util.Arrays;

public class KMP {


    public static int[] create(String needle) {
        int[] backFunc = new int[needle.length() + 1];
        backFunc[0] = backFunc[1] = 0;
        for (int i = 1; i < needle.length(); ++i) {
            int testing = i - 1;
            while (backFunc[testing] != testing) {
                if (needle.charAt(backFunc[testing]) == needle.charAt(i-1)) {
                    backFunc[i] = backFunc[testing] + 1;
                    break;
                } else {
                    testing = backFunc[testing];
                }
            }
        }
        return backFunc;
    }

    public static int find(String needle, String haystack) {
        // some unused character to ensure that we always return back and never reach the end of the
        // needle
        needle = needle + "$";
        int[] backFunc = create(needle);
        System.out.println(Arrays.toString(backFunc));
        int curpos = 0;
        for (int i = 0; i < haystack.length(); ++i) {
            while (curpos != backFunc[curpos]) {
                if (haystack.charAt(i) == needle.charAt(curpos)) {
                    ++curpos;
                    break;
                } else {
                    curpos = backFunc[curpos];
                }
            }
            if (curpos == 0 && needle.charAt(0) == haystack.charAt(i)) {
                ++curpos;
            }
            System.out.println(curpos);
        }
        return curpos;
    }

    public static void main(String[] args) {
        String[] tests = {"abababa", "tsttst", "acblahac", "aaaaa"};
        for (String test : tests) {
            System.out.println("Length is : " + find(test, test.substring(1)));
        }
    }
}
