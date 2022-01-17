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

        for (List<Integer> query : queries) {
            int a = query.get(0);
            int b = query.get(1);
            long k = query.get(2);

            for (int i = a; i < b + 1; i++) {
                arr[i - 1] = arr[i - 1] + k;
                max = Math.max(max, arr[i - 1]);
            }
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
            List<Integer> query = queries.get(i);
            int a = query.get(0);
            int b = query.get(1);
            int k = query.get(2);
            arr[a - 1] += k;
            arr[b] -= k;
        }

        long sum = 0;
        long max = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            max = Math.max(max, sum);
        }

        return max;
    }
}
