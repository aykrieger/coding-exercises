package leetcode;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @see <a href="https://leetcode.com/problems/k-diff-pairs-in-an-array">K-diff Pairs in an
 * Array</a>
 */
public class KDiffPairsInAnArray {

  public static int countPairs(List<Integer> numbers, int k) {
    HashSet<Integer> numSet = new HashSet<Integer>();
    numSet.addAll(numbers);
    HashSet<Integer> sumSet = new HashSet<Integer>();

    int count = 0;

    for (Integer num : numSet) {
      sumSet.add(num + k);
    }

    for (Integer sum : sumSet) {
      if (numSet.contains(sum)) {
        count++;
      }
    }

    return count;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    int numbersCount = Integer.parseInt(bufferedReader.readLine().trim());

    List<Integer> numbers = IntStream.range(0, numbersCount).mapToObj(i -> {
          try {
            return bufferedReader.readLine().replaceAll("\\s+$", "");
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        })
        .map(String::trim)
        .map(Integer::parseInt)
        .collect(toList());

    int k = Integer.parseInt(bufferedReader.readLine().trim());

    int result = countPairs(numbers, k);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
