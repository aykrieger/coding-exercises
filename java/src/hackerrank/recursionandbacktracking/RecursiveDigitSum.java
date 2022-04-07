package hackerrank.recursionandbacktracking;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @see <a href="https://www.hackerrank.com/challenges/recursive-digit-sum/problem">Recursive Digit
 * Sum</a>
 */
public class RecursiveDigitSum {

  /*
   * Complete the 'superDigit' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts following parameters:
   *  1. STRING n
   *  2. INTEGER k
   */

  public static int superDigit(String n, int k) {

    if (n.length() == 1) {
      return Integer.parseInt(n);
    } else {
      long sum = 0;

      for (int i = 0; i < n.length(); i++) {
        sum += Long.parseLong(n.charAt(i) + "");
      }

      return superDigit(String.valueOf(sum * k), 1);
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

    String n = firstMultipleInput[0];

    int k = Integer.parseInt(firstMultipleInput[1]);

    int result = superDigit(n, k);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
