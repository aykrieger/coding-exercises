package dynamicprogramming;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/max-array-sum/problem">Max Array Sum</a>
 * <p>
 * See <a href="https://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/">GeeksforGeeks
 * Max Array Sum</a> for the explanation.
 */
public class MaxArraySum {

  // Complete the maxSubsetSum function below.
  static int maxSubsetSum(int[] arr) {
    int incl = 0;
    int excl = 0;
    int temp = 0;

    for (int i = 0; i < arr.length; i++) {
      temp = incl;
      incl = Math.max(arr[i] + excl, temp);
      excl = temp;
    }
    return Math.max(incl, excl);
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    int n = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    int[] arr = new int[n];

    String[] arrItems = scanner.nextLine().split(" ");
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int i = 0; i < n; i++) {
      int arrItem = Integer.parseInt(arrItems[i]);
      arr[i] = arrItem;
    }

    int res = maxSubsetSum(arr);

    bufferedWriter.write(String.valueOf(res));
    bufferedWriter.newLine();

    bufferedWriter.close();

    scanner.close();
  }
}
