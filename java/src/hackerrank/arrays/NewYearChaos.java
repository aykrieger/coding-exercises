package hackerrank.arrays;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @see <a href="https://www.hackerrank.com/challenges/new-year-chaos/problem">New Year Chaos</a>
 */
public class NewYearChaos {

  /*
   * Complete the 'minimumBribes' function below.
   *
   * The function accepts INTEGER_ARRAY q as parameter.
   */

  // Complete the minimumBribes function below.
  static void minimumBribes(List<Integer> q) {

    int moves = 0;

    int maxNum = 100000;
    int midNum = 100000;
    int minNum = 100000;

    for (int i = q.size() - 1; i >= 0; i--) {
      // If the person is more than two swaps away from their final position
      if ((q.get(i) - i) > 3) {
        System.out.println("Too chaotic");
        return;
      }

      if (q.get(i) > midNum) {
        // The person bribed two people
        moves = moves + 2;
      } else if (q.get(i) > minNum) {
        // The person bribed one person
        moves = moves + 1;
      }

      // Update our three values
      if (q.get(i) < minNum) {
        maxNum = midNum;
        midNum = minNum;
        minNum = q.get(i);
      } else if (q.get(i) < midNum) {
        maxNum = midNum;
        midNum = q.get(i);
      } else if (q.get(i) < maxNum) {
        maxNum = q.get(i);
      }
    }
    System.out.println(moves);
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    int t = Integer.parseInt(bufferedReader.readLine().trim());

    IntStream.range(0, t).forEach(tItr -> {
      try {
        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> q = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        minimumBribes(q);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    bufferedReader.close();
  }
}
