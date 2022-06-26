package hackerrank.warmupchallenges;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @see <a href="https://www.hackerrank.com/challenges/counting-valleys/problem">Counting
 * Valleys</a>
 */
public class CountingValleys {

  /*
   * Complete the 'countingValleys' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts following parameters:
   *  1. INTEGER steps
   *  2. STRING path
   */

  public static int countingValleys(int steps, String path) {
    int height = 0;
    int valleys = 0;

    for (char c : path.toCharArray()) {
      if (height == -1 && c == 'U') {
        valleys++;
      }
      switch (c) {
        case 'U':
          height++;
          break;
        case 'D':
          height--;
          break;
        default:
          break;
      }

    }
    return valleys;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    int steps = Integer.parseInt(bufferedReader.readLine().trim());

    String path = bufferedReader.readLine();

    int result = countingValleys(steps, path);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
