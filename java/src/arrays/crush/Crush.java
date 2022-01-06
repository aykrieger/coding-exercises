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
        /*
        The simple solution to this problem is to create an array of size n and add the value k to
        each array element between the given indices, inclusive. After we go through each query we
        can calculate the max value by iterating over every element of the array and checking if
        that element is larger than the current max value.

        This solution works, but it takes O(n * m) time.

        In order to reduce our runtime, we will have to get creative.

        Instead of adding the given value to each element in our array, we will add the value to the
        given beginning index (a) and subtract the value from the given end index (b). After we go
        through each of the queries, we can calculate the max value. To do this we iterate over
        every index in the array and keep a rolling sum.

        create an array of size n a



         each given array in the queries list and combining it
        into a single array that stores every value at every index. That would take O(n + m) time
        and space complexity.

        In our solution we will create an array of size n to store the k values at the beginning and
        We first create an array . I'll call
        this our "diff-array". Whenever we encounter a new set of a, b, and k, we will add the
        positive value of k at the beginning (a) index of the diff-array and set the negative value
        of k at the end index (b) of the diff-array. After we go through all the given sets of a, b,
        and k, we sum all the values in the array to get our max value.

        The main idea in this is to

        We will treat the first index of the diff-array as 1. When we encounter a "1" as our a
        value, we will increment the value at index "a - 1" (since our array is 0 indexed).

        We are storing the beginning and end index of each given array in our "diff-array" (I just
        created that term) and the value to increment or decrement our rolling sum. After we
        store all the given arrays in the diff-array, we then calculate the max value. We start at
        index 0 in the diff-array and
        increment our rolling sum with the positive values and decrement our rolling sum

with the negative values. When we reach the end of the diff-array, our
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
