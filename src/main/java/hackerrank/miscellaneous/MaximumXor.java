package hackerrank.miscellaneous;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/maximum-xor/problem">Maximum XOR</a>
 */
public class MaximumXor {

  static class BitNode {

    BitNode[] bits = new BitNode[2];
  }

  // Complete the maxXor function below.
  static int[] maxXor(int[] arr, int[] queries) {
    int[] max = new int[queries.length];
    BitNode root = new BitNode();

    // For each array element, encode the element into our Trie
    for (int element : arr) {
      BitNode node = root;
      // Max int size is 2^(32) - 1 so the max shift size is 31
      // Calculate the bit at each position in the element and
      // add that bit to our Trie
      for (int shift = 31; shift >= 0; shift--) {
        // 1 if the current bit is 1, 0 if the current bit is 0
        int bitShifted = (element >> shift) & 1;
        if (node.bits[bitShifted] == null) {
          node.bits[bitShifted] = new BitNode();
        }
        node = node.bits[bitShifted];
      }
    }

    // For each query element, encode the elment into our Trie
    for (int i = 0; i < queries.length; i++) {
      int a = 0;
      BitNode node = root;

      for (int shift = 31; shift >= 0; shift--) {
        int bitShifted = (queries[i] >> shift) & 1;
        if (node.bits[1 - bitShifted] != null) {
          bitShifted = 1 - bitShifted;
        }
        a = (a << 1) | bitShifted;
        node = node.bits[bitShifted];
      }
      max[i] = a ^ queries[i];
    }
    return max;
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

    int m = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    int[] queries = new int[m];

    for (int i = 0; i < m; i++) {
      int queriesItem = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
      queries[i] = queriesItem;
    }

    int[] result = maxXor(arr, queries);

    for (int i = 0; i < result.length; i++) {
      bufferedWriter.write(String.valueOf(result[i]));

      if (i != result.length - 1) {
        bufferedWriter.write("\n");
      }
    }

    bufferedWriter.newLine();

    bufferedWriter.close();

    scanner.close();
  }
}
