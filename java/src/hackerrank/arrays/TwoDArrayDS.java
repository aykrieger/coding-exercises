package hackerrank.arrays;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @see <a href="https://www.hackerrank.com/challenges/2d-array/problem">2D Array - DS</a>
 */
public class TwoDArrayDS {

  /*
   * Complete the 'hourglassSum' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts 2D_INTEGER_ARRAY arr as parameter.
   */

  public static int hourglassSum(List<List<Integer>> arr) {
    Integer largestSum = null;

    for (int x = 0; x < 4; x++) {
      for (int y = 0; y < 4; y++) {
        // Here we begin calculating the sum of a single hourglass
        int currentSum = 0;
        for (int i = 0; i < 3; i++) {
          for (int j = 0; j < 3; j++) {
            if (i == 1 && (j == 0 || j == 2)) {
              continue;
            }

            currentSum = currentSum + arr.get(x + i).get(y + j);
          }
        }
        if (largestSum == null) {
          largestSum = currentSum;
        }
        if (currentSum > largestSum) {
          largestSum = currentSum;
        }
      }
    }
    return largestSum;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    List<List<Integer>> arr = new ArrayList<>();

    IntStream.range(0, 6).forEach(i -> {
      try {
        arr.add(
            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList())
        );
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    int result = hourglassSum(arr);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
