package stacksandqueues;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Stream;

/**
 * @see <a href="https://www.hackerrank.com/challenges/largest-rectangle/problem">Largest
 * Rectangle</a>
 */
public class LargestRectangle {

  /*
   * Complete the 'largestRectangle' function below.
   *
   * The function is expected to return a LONG_INTEGER.
   * The function accepts INTEGER_ARRAY h as parameter.
   */
  public static long largestRectangle(List<Integer> h) {
    long result = 0;
    long minHeight = 0;
    long width = 0;
    int leftIdx = 0;
    int rightIdx = 0;
    for (int i = 0; i < h.size(); i++) {
      minHeight = h.get(i);
      width = 0;
      leftIdx = i;
      rightIdx = i;

      // Calculate width left of i
      while (leftIdx >= 0 && (h.get(leftIdx) >= minHeight)) {
        width++;
        leftIdx--;
      }

      // Calculate width right of i
      while (rightIdx < h.size() && (h.get(rightIdx) >= minHeight)) {
        width++;
        rightIdx++;
      }
      width -= 1;
      result = Math.max(result, (minHeight * width));
    }
    return result;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    int n = Integer.parseInt(bufferedReader.readLine().trim());

    List<Integer> h = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
        .map(Integer::parseInt)
        .collect(toList());

    long result = LargestRectangle.largestRectangle(h);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
