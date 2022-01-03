package arrays.crush;

import java.util.Arrays;
import java.util.List;

class Crush {

    /*
     * Complete the 'arrayManipulation' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY queries
     */
    public static long arrayManipulation(int n, List<List<Integer>> queries) {
        long[] arr = new long[n];
        long max = 0;
        System.out.println(Arrays.toString(arr));
        System.out.println("Size: " + queries.size());

        for (int i = 0; i < queries.size(); i++) {
            System.out.println("Query: " + i + " " + queries.get(i));
            List<Integer> query = queries.get(i);
            int start = query.get(0).intValue();
            int end = query.get(1).intValue();
            long val = query.get(2).longValue();

            for (int j = start; j < end + 1; j++) {
                arr[j - 1] = arr[j - 1] + val;
                if (arr[j - 1] > max) {
                    max = arr[j - 1];
                }
            }
            System.out.println(Arrays.toString(arr));
        }
        return max;
    }

}
