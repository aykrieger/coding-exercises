import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This problem is from HackerRank:
 * <a href="https://www.hackerrank.com/challenges/minimum-swaps-2/problem">Minimum Swaps 2</a>
 *
 * The solution to this involves looping over every index of the array and swapping the current
 * element with the element at the current element's correct position. The given unordered array
 * consists of consecutive integers, so we know the correct position for each element.
 * *
 * For example, if we are given:
 * [2, 1, 4, 3]
 *  ^
 *
 * We start at index 0 and see our element is 2. We know the value of the first element (index 0)
 * should be index + 1 = 1. 2 does not match 1, so we need to swap. We know element 2 should go at
 * index 2 - 1 = 1, so we swap element 2 with the element at index 1 (which is 1 in this example).
 * After 1 swap our array looks like:
 *
 * [1, 2, 4, 3]
 *  ^
 * <i>swaps = 1</i>
 *
 * We stay at the current index (index 0) because we made a swap and need to check the current
 * element. Does the current element, 1, match (index + 1 = 1) 1? Yes. We know the element at index
 * 0 is correct, so we can move to index 1.
 *
 * [1, 2, 4, 3]
 *     ^
 * <i>swaps = 1</i>
 *
 * Does the current element, 2, match (index + 1 = 2) 2? Yes. We can move to the next index.
 *
 * [1, 2, 4, 3]
 *        ^
 * <i>swaps = 1</i>
 *
 * Does the current element, 4, match (index + 1 = 3) 3? No. We know element 4 belongs at index
 * (4 - 1) 3, so we swap element 4 with the element at index 3, which is 3.
 *
 * [1, 2, 3, 4]
 *        ^
 * <i>swaps = 2</i>
 *
 * Does the current element, 3, match (index + 1 = 3)? Yes. We can move to the next index.
 *
 * [1, 2, 3, 4]
 *           ^
 * <i>swaps = 2</i>
 *
 * Does the current element, 4, match (index + 1 = 4) 4? Yes. We see we're at the end of the array
 * and the array is sorted. The minimum number of swaps to sort this array is 2.
 */
public class MinimumSwaps2 {

  // Complete the minimumSwaps function below.
  static int minimumSwaps(int[] arr) {
    int swaps = 0;

    for (int i = 0; i < arr.length; i++) {
      if (arr[i] != i + 1) {
        swaps++;
        int temp = arr[i];
        arr[i] = arr[temp - 1];
        arr[temp - 1] = temp;
        i--;
      }
    }
    return swaps;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int n = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    int[] arr = new int[n];

    String[] arrItems = scanner.nextLine().split(" ");
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int i = 0; i < n; i++) {
      int arrItem = Integer.parseInt(arrItems[i]);
      arr[i] = arrItem;
    }

    int res = minimumSwaps(arr);

    bufferedWriter.write(String.valueOf(res));
    bufferedWriter.newLine();

    bufferedWriter.close();

    scanner.close();
  }
}
