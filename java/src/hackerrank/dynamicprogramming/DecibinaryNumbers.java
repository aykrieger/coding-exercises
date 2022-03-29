package hackerrank.dynamicprogramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * This solution is credited to the users <a href="https://www.hackerrank.com/mail4deepak">mail4deepak</a>,
 * and
 * <a href="https://www.hackerrank.com/craig53">craig53</a> on HackerRank and the Math Stack
 * Exchange user
 * <a href="https://math.stackexchange.com/questions/3540243/whats-the-number-of-decibinary-numbers-that-evaluate-to-given-decimal-number/3545775#3545775">Hristo
 * lliev</a> for their work. This is quite a difficult math and computer science problem.
 *
 * @see <a href="https://www.hackerrank.com/challenges/decibinary-numbers/problem">Decibinary
 * Numbers</a>
 */
public class DecibinaryNumbers {

  /*
   * Complete the 'decibinaryNumbers' function below.
   *
   * The function is expected to return a LONG_INTEGER.
   * The function accepts LONG_INTEGER x as parameter.
   */

  private static int dmax = 300000;
  private static int digits = 10;
  private static int powers = 20;
  private static long[][] duplicates = new long[dmax][powers];
  private static long[] offsets = new long[dmax];

  static void preCompute() {
    // Compute the number of duplicates for each value, number of digits.
    for (int i = 0; i < dmax; i++) {
      duplicates[i][0] = i < digits ? 1 : 0;

      for (int j = 1; j < powers; j++) {
        // Duplicates is the sum of all shorter numbers duplicates for each digit.
        for (int k = 0; k < digits; k++) {
          int value = i - k * (1 << j);

          // Break if using digit creates number larger than target value.
          if (value < 0) {
            break;
          }
          duplicates[i][j] += duplicates[value][j - 1];
        }
      }
    }
    // Calculate the absolute offset for the first duplicate of each number.
    for (int i = 1; i < dmax; i++) {
      offsets[i] = duplicates[i - 1][powers - 1] + offsets[i - 1];
    }
  }

  static long decibinaryNumbers(long x) {
    long result = 0;
    int l = Arrays.binarySearch(offsets, x - 1);
    if (l < 0) {
      l = Math.abs(l) - 2;
    }

    int value = (int) (l - offsets[0]);
    long offset = (x - 1) - offsets[l];

    // Find each digit.
    for (int i = powers - 1; i >= 1; i--) {
      int power = 1 << i;

      // Find the digit which takes us closest to offset.
      for (int digit = 0; digit < digits; digit++) {
        // Calculate value of remaining digits.
        int v1 = value - digit * power;

        // If index is less than duplicates for remainder we have our digit.
        if (offset < duplicates[v1][i - 1]) {
          result += digit;
          value -= power * digit;
          break;
        }

        // Subtract number of duplicates for this digit from offset.
        offset -= duplicates[v1][i - 1];
      }

      result *= 10;
    }

    // Whatever is left must be the last digit.
    result += value;

    return result;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    int q = Integer.parseInt(bufferedReader.readLine().trim());
    DecibinaryNumbers.preCompute();

    IntStream.range(0, q).forEach(qItr -> {
      try {
        long x = Long.parseLong(bufferedReader.readLine().trim());

        long result = DecibinaryNumbers.decibinaryNumbers(x);

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
