package hackerrank.miscellaneous;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/friend-circle-queries/problem">Friend Circle
 * Queries</a>
 */
public class FriendCircleQueries {

  static class Graph {

    Map<Integer, Integer> parents = new HashMap<Integer, Integer>();
    Map<Integer, Integer> sizes = new HashMap<Integer, Integer>();
    int max = 0;

    public void union(int firstPerson, int secondPerson) {
      if (!parents.containsKey(firstPerson)) {
        parents.put(firstPerson, firstPerson);
        sizes.put(firstPerson, 1);
      }

      if (!parents.containsKey(secondPerson)) {
        parents.put(secondPerson, secondPerson);
        sizes.put(secondPerson, 1);
      }

      int firstParent = find(firstPerson);
      int secondParent = find(secondPerson);

      // If these are the same, they are already unioned
      if (firstParent == secondParent) {
        return;
      }

      int firstSize = sizes.get(firstParent);
      int secondSize = sizes.get(secondParent);

      if (firstSize < secondSize) {
        parents.put(firstParent, secondParent);
        sizes.put(secondParent, firstSize + secondSize);
        if (firstSize + secondSize > max) {
          max = firstSize + secondSize;
        }
      } else {
        parents.put(secondParent, firstParent);
        sizes.put(firstParent, firstSize + secondSize);
        if (firstSize + secondSize > max) {
          max = firstSize + secondSize;
        }
      }
    }

    private int find(int person) {
      while (parents.get(person) != person) {
        parents.put(person, parents.get(parents.get(person)));
        person = parents.get(person);
      }
      return person;
    }
  }

  // Complete the maxCircle function below.
  static int[] maxCircle(int[][] queries) {
    Graph graph = new Graph();
    int[] result = new int[queries.length];

    for (int i = 0; i < queries.length; i++) {
      graph.union(queries[i][0], queries[i][1]);
      result[i] = graph.max;
    }
    return result;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    int q = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    int[][] queries = new int[q][2];

    for (int i = 0; i < q; i++) {
      String[] queriesRowItems = scanner.nextLine().split(" ");
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int j = 0; j < 2; j++) {
        int queriesItem = Integer.parseInt(queriesRowItems[j]);
        queries[i][j] = queriesItem;
      }
    }

    int[] ans = maxCircle(queries);

    for (int i = 0; i < ans.length; i++) {
      bufferedWriter.write(String.valueOf(ans[i]));

      if (i != ans.length - 1) {
        bufferedWriter.write("\n");
      }
    }

    bufferedWriter.newLine();

    bufferedWriter.close();

    scanner.close();
  }
}
