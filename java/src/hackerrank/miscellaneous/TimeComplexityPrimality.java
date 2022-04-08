package hackerrank.miscellaneous;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

/**
 * @see <a href="https://www.hackerrank.com/challenges/ctci-big-o/problem">Time Complexity:
 * Primality</a>
 */
public class TimeComplexityPrimality {

  /*
   * Complete the 'primality' function below.
   *
   * The function is expected to return a STRING.
   * The function accepts INTEGER n as parameter.
   */
  public static String primality(int n) {
    if (n < 2) {
      return "Not prime";
    } else if (n == 2) {
      return "Prime";
    } else if (n % 2 == 0) {
      return "Not prime";
    } else {
      for (int i = 3; i < Math.sqrt(n) + 1; i = i + 2) {
        if (n % i == 0) {
          return "Not prime";
        }
      }
    }
    return "Prime";
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    int p = Integer.parseInt(bufferedReader.readLine().trim());

    IntStream.range(0, p).forEach(pItr -> {
      try {
        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String result = primality(n);

        bufferedWriter.write(result);
        bufferedWriter.newLine();
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    bufferedReader.close();
    bufferedWriter.close();
  }
}
