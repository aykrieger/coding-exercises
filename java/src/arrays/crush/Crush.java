package arrays.crush;

import java.util.Arrays;
import java.util.List;

/**
 * This problem is from HackerRank:
 * <a href="https://www.hackerrank.com/challenges/crush/problem">Algorithmic Crush</a>
 *
 *
 */
class Crush {

    public static long arrayManipulationSimple(int n, List<List<Integer>> queries) {
        long[] arr = new long[n];
        long max = 0;
        System.out.println(Arrays.toString(arr));
        System.out.println("Size: " + queries.size());

        for (List<Integer> query : queries) {
            int start = query.get(0);
            int end = query.get(1);
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

    /*
     * Complete the 'arrayManipulation' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY queries
     */
    public static long arrayManipulationImproved(int n, List<List<Integer>> queries) {
        long[] arr = new long[n + 1];

        for (int i = 0; i < queries.size(); i++) {
            System.out.println(Arrays.toString(arr));
            List<Integer> query = queries.get(i);
            int a = query.get(0);
            int b = query.get(1);
            int k = query.get(2);
            arr[a - 1] += k;
            arr[b] -= k;
        }
        System.out.println(Arrays.toString(arr));

        long sum = 0;
        long max = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            max = Math.max(max, sum);
        }

        return max;
    }
}
