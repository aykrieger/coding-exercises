package hackerrank.recursionandbracktracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.stream.IntStream;

/**
 * @see <a href="https://www.hackerrank.com/challenges/ctci-recursive-staircase/problem">Recursion:
 * Davis' Staircase</a>
 */
public class RecursionDavisStaircase {

  /*
   * Complete the 'stepPerms' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts INTEGER n as parameter.
   */

  static HashMap<Integer, Integer> cache = new HashMap<Integer, Integer>();

  public static int stepPerms(int n) {
    return numWaysToClimb(n);


  }

  private static int numWaysToClimb(int n) {
    if (n == 0) {
      return 0;
    }

    if (cache.get(n) != null) {
      return cache.get(n);
    }

    int result = 0;

    if (n == 1) {
      result = 1;
    } else if (n == 2) {
      result = 2;
    } else if (n == 3) {
      result = 4;
    } else {
      result = numWaysToClimb(n - 1) + numWaysToClimb(n - 2) + numWaysToClimb(n - 3);
    }

    cache.put(n, result);
    return result;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    int s = Integer.parseInt(bufferedReader.readLine().trim());

    IntStream.range(0, s).forEach(sItr -> {
      try {
        int n = Integer.parseInt(bufferedReader.readLine().trim());

        int res = RecursionDavisStaircase.stepPerms(n);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    bufferedReader.close();
    bufferedWriter.close();
  }
}
