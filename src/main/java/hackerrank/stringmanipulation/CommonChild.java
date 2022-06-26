package hackerrank.stringmanipulation;

import java.io.*;

/**
 * @see <a href="https://www.hackerrank.com/challenges/common-child/problem">Common Child</a>
 */
public class CommonChild {

  /*
   * Complete the 'commonChild' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts following parameters:
   *  1. STRING s1
   *  2. STRING s2
   */
  public static int commonChild(String s1, String s2) {
    char[] arr1 = s1.toCharArray();
    char[] arr2 = s2.toCharArray();
    int mat[][] = new int[s1.length() + 1][s2.length() + 1];

    int i = 1;
    int j = 1;

    for (i = 1; i <= arr1.length; i++) {
      for (j = 1; j <= arr2.length; j++) {
        if (arr1[i - 1] == arr2[j - 1]) {
          mat[i][j] = mat[i - 1][j - 1] + 1;
        } else {
          mat[i][j] = (Math.max(mat[i - 1][j], mat[i][j - 1]));
        }
      }
    }

    return (mat[i - 1][j - 1]);
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    String s1 = bufferedReader.readLine();

    String s2 = bufferedReader.readLine();

    int result = CommonChild.commonChild(s1, s2);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
