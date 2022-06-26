package hackerrank.stringmanipulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @see <a href="https://www.hackerrank.com/challenges/ctci-making-anagrams/problem">Strings: Making
 * Anagrams</a>
 */
public class StringsMakingAnagrams {

  /*
   * Complete the 'makeAnagram' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts following parameters:
   *  1. STRING a
   *  2. STRING b
   */

  public static int makeAnagram(String a, String b) {
    int result = 0;
    char[] aArr = a.toCharArray();
    char[] bArr = b.toCharArray();
    int[] freq = new int[(int) 'z' + 1];
    for (int i = 0; i < aArr.length; i++) {
      freq[(int) aArr[i]]++;
    }
    for (int j = 0; j < bArr.length; j++) {
      freq[(int) bArr[j]]--;
    }
    for (int k = 0; k < freq.length; k++) {
      result += Math.abs(freq[k]);
    }
    return result;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    String a = bufferedReader.readLine();

    String b = bufferedReader.readLine();

    int res = makeAnagram(a, b);

    bufferedWriter.write(String.valueOf(res));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
