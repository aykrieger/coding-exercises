package hackerrank.warmupchallenges;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Stream;

/**
 * @see <a href="https://www.hackerrank.com/challenges/jumping-on-the-clouds/problem">Jumping on the
 * Clouds</a>
 */
public class JumpingOnTheClouds {


  /*
   * Complete the 'jumpingOnClouds' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts INTEGER_ARRAY c as parameter.
   */

  public static int jumpingOnClouds(List<Integer> c) {
    int size = c.size();
    int numberOfJumps = 0;
    for (int i = 0; i < size - 1; ) {
      if ((i + 2) < size && c.get(i + 2) != 1) {
        i = i + 2;
        numberOfJumps++;
        continue;
      }
      i++;
      numberOfJumps++;
    }
    return numberOfJumps;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    int n = Integer.parseInt(bufferedReader.readLine().trim());

    List<Integer> c = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
        .map(Integer::parseInt)
        .collect(toList());

    int result = jumpingOnClouds(c);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
