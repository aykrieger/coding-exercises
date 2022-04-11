package hackerrank.dictionariesandhashmaps;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

/**
 * @see <a href="https://www.hackerrank.com/challenges/two-string/problem">Two Strings</a>
 */
public class TwoStrings {

  /*
   * Complete the 'twoStrings' function below.
   *
   * The function is expected to return a STRING.
   * The function accepts following parameters:
   *  1. STRING s1
   *  2. STRING s2
   */

  public static String twoStrings(String s1, String s2) {
    for (char c : "abcdefghijklmnopqrstuvwxyz".toCharArray()) {
      if (s1.indexOf(c) > -1 && s2.indexOf(c) > -1) {
        return "YES";
      }
    }
    return "NO";
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    int q = Integer.parseInt(bufferedReader.readLine().trim());

    IntStream.range(0, q).forEach(qItr -> {
      try {
        String s1 = bufferedReader.readLine();

        String s2 = bufferedReader.readLine();

        String result = twoStrings(s1, s2);

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
