package arrays.crush;

import java.util.List;

/**
 * This problem is from HackerRank:
 * <a href="https://www.hackerrank.com/challenges/crush/problem">Algorithmic Crush</a>
 *
 *
 */
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
            int m = queries.size();
            System.out.println(queries.size());

            long[] arr = new long[n + 1];

            while (m-- > 0) {
                List<Integer> query = queries.get(m);
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
