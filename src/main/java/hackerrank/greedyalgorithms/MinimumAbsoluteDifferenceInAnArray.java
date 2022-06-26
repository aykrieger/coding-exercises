package hackerrank.greedyalgorithms;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @see <a href="https://www.hackerrank.com/challenges/minimum-absolute-difference-in-an-array/problem">Minimum
 * Absolute Difference in an Array</a>
 */
public class MinimumAbsoluteDifferenceInAnArray {

  /*
   * Complete the 'minimumAbsoluteDifference' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts INTEGER_ARRAY arr as parameter.
   */
  public static int minimumAbsoluteDifference(List<Integer> arr) {
    Collections.sort(arr);
    int minDifference = Math.abs(arr.get(1) - arr.get(0));
    for (int i = 2; i < arr.size(); i++) {
      int prev = arr.get(i - 1);
      int curr = arr.get(i);
      int diff = Math.abs(curr - prev);
      if (diff == 0) {
        minDifference = 0;
        break;
      } else if (diff < minDifference) {
        minDifference = diff;
      }
    }
    return minDifference;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    int n = Integer.parseInt(bufferedReader.readLine().trim());

    List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
        .map(Integer::parseInt)
        .collect(toList());

    int result = MinimumAbsoluteDifferenceInAnArray.minimumAbsoluteDifference(arr);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}