package hackerrank.dictionariesandhashmaps;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @see <a href="https://www.hackerrank.com/challenges/count-triplets-1/problem">Count Triplets</a>
 */
public class CountTriplets {

  // Complete the countTriplets function below.
  private static long countTriplets(List<Long> arr, long r) {
    Map<Long, Long> potential = new HashMap<>();
    Map<Long, Long> counter = new HashMap<>();
    long count = 0;
    for (int i = 0; i < arr.size(); i++) {
      long a = arr.get(i);
      long key = a / r;

      if (counter.containsKey(key) && a % r == 0) {
        count += counter.get(key);
      }

      if (potential.containsKey(key) && a % r == 0) {
        long c = potential.get(key);
        counter.put(a, counter.getOrDefault(a, 0L) + c);
      }

      potential.put(a, potential.getOrDefault(a, 0L) + 1);
    }
    return count;
  }


  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

    int n = Integer.parseInt(nr[0]);

    long r = Long.parseLong(nr[1]);

    List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
        .map(Long::parseLong)
        .collect(toList());

    long ans = countTriplets(arr, r);

    bufferedWriter.write(String.valueOf(ans));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
