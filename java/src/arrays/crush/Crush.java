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
                /*
        For this problem we are not combining each given array in the queries list and
        combining it into a single array that stores every value at every index. That
        would take O(n + m) time and space complexity. Instead, we are storing the
        beginning
        and end index of each given array in our "diff-array" (I just created that term)
        and the value to increment or decrement our rolling sum. After we store all of
        the given arrays in the diff-array, we then start at index 0 in the diff-array
        and increment our rolling sum with the positive values and decrement our rolling
        sum with the negative values. When we reach the end of the diff-array, our
        rolling sum will be the max value we are looking for.

        This solution only takes O(n + m) time and space complexity.

        I like to think of this as storing the "diffs" for each array we are given

        We will use indicies 0 to "n + 1".
        */
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
