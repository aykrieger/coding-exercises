package dynamicprogramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

/**
 * @see <a href="https://www.hackerrank.com/challenges/abbr/problem">Abbreviation</a>
 */
public class Abbreviation {

  /*
   * Complete the 'abbreviation' function below.
   *
   * The function is expected to return a STRING.
   * The function accepts following parameters:
   *  1. STRING a
   *  2. STRING b
   */
  public static String abbreviation(String a, String b) {
    boolean[][] dp = new boolean[b.length() + 1][a.length() + 1];
    dp[0][0] = true;
    for (int j = 1; j < dp[0].length; j++) {
      if (Character.isLowerCase(a.charAt(j - 1))) {
        dp[0][j] = dp[0][j - 1];
      }
    }

    for (int i = 1; i < dp.length; i++) {
      for (int j = 1; j < dp[0].length; j++) {
        char ca = a.charAt(j - 1);
        char cb = b.charAt(i - 1);
        if (Character.isUpperCase(ca)) {
          if (ca == cb) {
            dp[i][j] = dp[i - 1][j - 1];
          }
        } else {
          if (Character.toUpperCase(ca) == cb) {
            dp[i][j] = dp[i - 1][j - 1] || dp[i][j - 1];
          } else {
            dp[i][j] = dp[i][j - 1];
          }
        }
      }
    }

    return dp[b.length()][a.length()] ? "YES" : "NO";
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    int q = Integer.parseInt(bufferedReader.readLine().trim());

    IntStream.range(0, q).forEach(qItr -> {
      try {
        String a = bufferedReader.readLine();

        String b = bufferedReader.readLine();

        String result = Abbreviation.abbreviation(a, b);

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
