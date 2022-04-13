package hackerrank.stringmanipulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

/**
 * @see <a href="https://www.hackerrank.com/challenges/alternating-characters/problem">Alternating
 * Characters</a>
 */
public class AlternatingCharacters {

  /*
   * Complete the 'alternatingCharacters' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts STRING s as parameter.
   */

  public static int alternatingCharacters(String s) {
    if (s.length() == 1) {
      return 0;
    }
    char[] arr = s.toCharArray();
    int result = 0;
    int i = 1;
    int current = 0;
    while (i < arr.length) {
      if (arr[current] == arr[i]) {
        result++;
      } else {
        current = i;
      }
      i++;
    }
    return result;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    int q = Integer.parseInt(bufferedReader.readLine().trim());

    IntStream.range(0, q).forEach(qItr -> {
      try {
        String s = bufferedReader.readLine();

        int result = alternatingCharacters(s);

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
