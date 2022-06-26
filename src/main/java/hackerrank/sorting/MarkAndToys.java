package hackerrank.sorting;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * @see <a href="https://www.hackerrank.com/challenges/mark-and-toys/problem">Mark and Toys</a>
 */
public class MarkAndToys {

  /*
   * Complete the 'maximumToys' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts following parameters:
   *  1. INTEGER_ARRAY prices
   *  2. INTEGER k
   */

  public static int maximumToys(List<Integer> prices, int k) {
    Collections.sort(prices);
    int numberOfToys = 0;
    for (int i = 0; i < prices.size(); i++) {
      k = k - prices.get(i);
      if (k < 0) {
        break;
      }
      numberOfToys++;
    }
    return numberOfToys;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

    int n = Integer.parseInt(firstMultipleInput[0]);

    int k = Integer.parseInt(firstMultipleInput[1]);

    List<Integer> prices = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
        .map(Integer::parseInt)
        .collect(toList());

    int result = maximumToys(prices, k);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
