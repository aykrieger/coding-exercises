package hackerrank.greedyalgorithms;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @see <a href="https://www.hackerrank.com/challenges/luck-balance/problem">Luck Balance</a>
 */
public class LuckBalance {

  /*
   * Complete the 'luckBalance' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts following parameters:
   *  1. INTEGER k
   *  2. 2D_INTEGER_ARRAY contests
   */
  public static int luckBalance(int k, List<List<Integer>> contests) {
    int result = 0;

    ArrayList<Integer> sort = new ArrayList<>();
    for (List<Integer> list : contests) {
      result += list.get(0);
      if (list.get(1) == 1) {
        sort.add(list.get(0));
      }
    }
    Collections.sort(sort);
    int numLostImportant = sort.size() - k - 1;
    while (numLostImportant > -1) {
      result -= 2 * sort.get(numLostImportant);
      numLostImportant--;
    }

    return result;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

    int n = Integer.parseInt(firstMultipleInput[0]);

    int k = Integer.parseInt(firstMultipleInput[1]);

    List<List<Integer>> contests = new ArrayList<>();

    IntStream.range(0, n).forEach(i -> {
      try {
        contests.add(
            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList())
        );
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    int result = LuckBalance.luckBalance(k, contests);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
