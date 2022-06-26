package hackerrank.dynamicprogramming;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * @see <a href="https://www.hackerrank.com/challenges/candies/problem">Candies</a>
 */
public class Candies {

  /*
   * Complete the 'candies' function below.
   *
   * The function is expected to return a LONG_INTEGER.
   * The function accepts following parameters:
   *  1. INTEGER n
   *  2. INTEGER_ARRAY arr
   */
  public static long candies(int n, List<Integer> arr) {

    long result = 0;
    long[] candyArr = new long[arr.size()];
    candyArr[0] = 1;

    int prev = 0;
    int curr = 0;

    // Go left to right to increment increasing values
    for (int i = 1; i < arr.size(); i++) {
      prev = arr.get(i - 1);
      curr = arr.get(i);

      if (curr > prev) {
        candyArr[i] = candyArr[i - 1] + 1;
      } else {
        candyArr[i] = 1;
      }
    }

    // Go right to left to increment decreasing values
    for (int j = arr.size() - 2; j > -1; j--) {
      prev = arr.get(j + 1);
      curr = arr.get(j);

      if (curr > prev && candyArr[j] <= candyArr[j + 1]) {
        candyArr[j] = candyArr[j + 1] + 1;
      }
    }

    return LongStream.of(candyArr).sum();
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    int n = Integer.parseInt(bufferedReader.readLine().trim());

    List<Integer> arr = IntStream.range(0, n).mapToObj(i -> {
          try {
            return bufferedReader.readLine().replaceAll("\\s+$", "");
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        })
        .map(String::trim)
        .map(Integer::parseInt)
        .collect(toList());

    long result = Candies.candies(n, arr);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
