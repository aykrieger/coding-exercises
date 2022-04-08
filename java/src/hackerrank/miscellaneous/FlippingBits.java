package hackerrank.miscellaneous;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

/**
 * @see <a href="https://www.hackerrank.com/challenges/flipping-bits/problem">Flipping Bits</a>
 */
public class FlippingBits {

  /*
   * Complete the 'flippingBits' function below.
   *
   * The function is expected to return a LONG_INTEGER.
   * The function accepts LONG_INTEGER n as parameter.
   */
  public static long flippingBits(long n) {
    long maxValue = (long) Math.pow(2, 32) - 1;
    return n ^ maxValue;
  }


  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    int q = Integer.parseInt(bufferedReader.readLine().trim());

    IntStream.range(0, q).forEach(qItr -> {
      try {
        long n = Long.parseLong(bufferedReader.readLine().trim());

        long result = flippingBits(n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    bufferedReader.close();
    bufferedWriter.close();
  }
}
