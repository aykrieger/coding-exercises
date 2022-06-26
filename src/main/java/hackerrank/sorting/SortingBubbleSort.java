package hackerrank.sorting;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Stream;

/**
 * @see <a href="https://www.hackerrank.com/challenges/ctci-bubble-sort/problem">Sorting: Bubble
 * Sort</a>
 */
public class SortingBubbleSort {

  /*
   * Complete the 'countSwaps' function below.
   *
   * The function accepts INTEGER_ARRAY a as parameter.
   */

  public static void countSwaps(List<Integer> a) {
    int n = a.size();
    int swaps = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n - 1; j++) {
        if (a.get(j) > a.get(j + 1)) {
          Integer temp = a.get(j);
          a.set(j, a.get(j + 1));
          a.set(j + 1, temp);
          swaps++;
        }
      }
    }
    System.out.println("Array is sorted in " + swaps + " swaps.");
    System.out.println("First Element: " + a.get(0));
    System.out.println("Last Element: " + a.get(n - 1));
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(bufferedReader.readLine().trim());

    List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
        .map(Integer::parseInt)
        .collect(toList());

    countSwaps(a);

    bufferedReader.close();
  }
}
