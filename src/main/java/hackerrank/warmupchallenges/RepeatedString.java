package hackerrank.warmupchallenges;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.commons.lang3.StringUtils;

/**
 * @see <a href="https://www.hackerrank.com/challenges/repeated-string/problem">Repeated String</a>
 */
public class RepeatedString {

  /*
   * Complete the 'repeatedString' function below.
   *
   * The function is expected to return a LONG_INTEGER.
   * The function accepts following parameters:
   *  1. STRING s
   *  2. LONG_INTEGER n
   */

  public static long repeatedString(String s, long n) {
    long numInOrig = (long) StringUtils.countMatches(s, "a");
    long origSize = (long) s.length();

    long quotient = n / origSize;
    long remainder = n % origSize;

    return (quotient * numInOrig) + (StringUtils.countMatches(s.substring(0, (int) remainder),
        "a"));
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    String s = bufferedReader.readLine();

    long n = Long.parseLong(bufferedReader.readLine().trim());

    long result = repeatedString(s, n);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
