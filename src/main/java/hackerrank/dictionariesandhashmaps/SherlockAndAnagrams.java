package hackerrank.dictionariesandhashmaps;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

/**
 * @see <a href="https://www.hackerrank.com/challenges/sherlock-and-anagrams/problem">Sherlock and
 * Anagrams</a>
 */
public class SherlockAndAnagrams {

  private static boolean areAnagrams(int start1, int end1, int start2, int end2, int[][] count) {
    for (int i = 'a'; i <= 'z'; i++) {
      if (count[i][end1 + 1] - count[i][start1] != count[i][end2 + 1] - count[i][start2]) {
        return false;
      }
    }
    return true;
  }

  /*
   * Complete the 'sherlockAndAnagrams' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts STRING s as parameter.
   */

  public static int sherlockAndAnagrams(String str) {
    int count[][] = new int[128][110];

    final char s[] = str.toCharArray();
    count[s[0]][1] = 1;
    for (int strInd = 1; strInd < s.length; strInd++) {
      for (int j = 'a'; j <= 'z'; j++) {
        count[j][strInd + 1] = count[j][strInd];
      }
      count[s[strInd]][strInd + 1]++;
    }

    int result = 0;
    for (int len = 1; len <= s.length - 1; len++) {
      for (int from = 0; from <= s.length - len; from++) {
        for (int to = from + 1; to <= s.length - len; to++) {
          if (areAnagrams(from, from + len - 1, to, to + len - 1, count)) {
            result++;
          }
        }
      }
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

        int result = sherlockAndAnagrams(s);

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
