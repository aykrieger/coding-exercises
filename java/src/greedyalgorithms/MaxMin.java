package greedyalgorithms;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * @see <a href="https://www.hackerrank.com/challenges/angry-children/problem">Max Min</a>
 */
public class MaxMin {

  /*
   * Complete the 'maxMin' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts following parameters:
   *  1. INTEGER k
   *  2. INTEGER_ARRAY arr
   */
  public static int maxMin(int k, List<Integer> arr) {
    Collections.sort(arr);

    int result = arr.get(k - 1) - arr.get(0);
    for (int i = k; i < arr.size(); i++) {
      result = Math.min(arr.get(i) - arr.get(i - k + 1), result);
    }
    return result;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int n = Integer.parseInt(bufferedReader.readLine().trim());

    int k = Integer.parseInt(bufferedReader.readLine().trim());

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

    int result = MaxMin.maxMin(k, arr);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
