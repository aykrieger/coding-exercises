package stacksandqueues;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * This problem is quite difficult. I was able to write an inefficient solution and arrived at the
 * correct answer, but I wasn't able to edit it to pass all the test cases. This solution below
 * passes all the test cases, but it's quite opaque.
 *
 * @see <a href="https://www.hackerrank.com/challenges/min-max-riddle/problem">Min Max Riddle</a>
 */
public class MinMaxRiddle {

  // Complete the riddle function below.
  static long[] riddle(long[] arr) {
    int length = arr.length;
    long[] result = new long[length];
    long[] span = new long[length];

    ArrayDeque<Long> valDeq = new ArrayDeque<>();
    ArrayDeque<Long> idxDeq = new ArrayDeque<>();
    idxDeq.addFirst(-1L);

    // Record the number of greater values to the left
    for (int i = 0; i < length; i++) {
      while (!valDeq.isEmpty() && valDeq.peekFirst() >= arr[i]) {
        valDeq.pollFirst();
        idxDeq.pollFirst();
      }
      span[i] = i - idxDeq.peekFirst() - 1;
      valDeq.addFirst(arr[i]);
      idxDeq.addFirst((long) i);
    }

    // Record the number of greater values to the right
    valDeq.clear();
    idxDeq.clear();
    idxDeq.addFirst((long) length);
    for (int i = length - 1; i >= 0; i--) {
      while (!valDeq.isEmpty() && valDeq.peekFirst() >= arr[i]) {
        valDeq.pollFirst();
        idxDeq.pollFirst();
      }
      span[i] += idxDeq.peekFirst() - i - 1;
      valDeq.addFirst(arr[i]);
      idxDeq.addFirst((long) i);
    }

    // Fill the result array with the initial values
    for (int i = 0; i < length; i++) {
      result[(int) span[i]] = Math.max(result[(int) span[i]], arr[i]);
    }

    // Fill the empty spaces in the result array
    for (int i = length - 2; i >= 0; i--) {
      result[i] = Math.max(result[i], result[i + 1]);
    }

    return result;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    int n = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    long[] arr = new long[n];

    String[] arrItems = scanner.nextLine().split(" ");
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int i = 0; i < n; i++) {
      long arrItem = Long.parseLong(arrItems[i]);
      arr[i] = arrItem;
    }

    long[] res = riddle(arr);

    for (int i = 0; i < res.length; i++) {
      bufferedWriter.write(String.valueOf(res[i]));

      if (i != res.length - 1) {
        bufferedWriter.write(" ");
      }
    }

    bufferedWriter.newLine();

    bufferedWriter.close();

    scanner.close();
  }
}
