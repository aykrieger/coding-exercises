package hackerrank.graphs;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @see <a href="https://www.hackerrank.com/challenges/torque-and-development/problem">Roads and
 * Libraries</a>
 * <p>
 * <p>
 * Additional References:
 * @see <a href="https://algs4.cs.princeton.edu/15uf/">Union Find Algorithm</a>
 * @see <a href="https://www.coursera.org/lecture/algorithms-part1/quick-union-improvements-RZW72">Union
 * Find Improvements</a>
 */
public class RoadsAndLibraries {

  /*
   * Complete the 'roadsAndLibraries' function below.
   *
   * The function is expected to return a LONG_INTEGER.
   * The function accepts following parameters:
   *  1. INTEGER n
   *  2. INTEGER c_lib
   *  3. INTEGER c_road
   *  4. 2D_INTEGER_ARRAY cities
   */
  public static long roadsAndLibraries(int n, int c_lib, int c_road, List<List<Integer>> cities) {

    // For arrays parent, rank, and cityCount, ignore index 0
    final int[] parent = new int[n + 1];
    final int[] rank = new int[n + 1]; // Union-find subtree rank to merge shorter to larger
    final int[] cityCount = new int[n + 1];

    for (int i = 1; i < n + 1; i++) {
      parent[i] = i;
      cityCount[i] = 1;
    }

    for (final List<Integer> conn : cities) {
      final int v = conn.get(0);
      final int q = conn.get(1);
      final int rootV = find(v, parent);
      final int rootQ = find(q, parent);
      // If v and q are not unioned, union them
      if (rootV != rootQ) {
        if (rank[rootV] > rank[rootQ]) {
          parent[rootQ] = rootV;
          cityCount[rootV] += cityCount[rootQ];
        } else {
          parent[rootV] = rootQ;
          cityCount[rootQ] += cityCount[rootV];

          if (rank[rootV] == rank[rootQ]) {
            rank[rootQ]++;
          }
        }
      }
    }

    // find all islands (connected sub-graphs) and calculate minimal cost for each city's island
    // consider each "island" as a spanning tree, e.g. it has minimal roads/nodes to connect all of them
    long totalCost = 0;
    for (int i = 1; i < n + 1; i++) {
      if (parent[i] == i) {
        // number of edges in spanning sub-tree of a graph with cityCount[i] nodes
        final long roadsCount = cityCount[i] - 1;
        final long buildRoadsAndOneLib = roadsCount * c_road + c_lib;
        final int buildLibsInEachCity = cityCount[i] * c_lib;
        totalCost += Math.min(buildRoadsAndOneLib, buildLibsInEachCity);
      }
    }

    return totalCost;
  }

  // Union find with pass compression
  private static int find(int val, final int[] parents) {
    while (parents[val] != val) {
      parents[val] = parents[parents[val]];
      val = parents[val];
    }
    return val;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    int q = Integer.parseInt(bufferedReader.readLine().trim());

    IntStream.range(0, q).forEach(qItr -> {
      try {
        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        int c_lib = Integer.parseInt(firstMultipleInput[2]);

        int c_road = Integer.parseInt(firstMultipleInput[3]);

        List<List<Integer>> cities = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
          try {
            cities.add(
                Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList())
            );
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        });

        long result = RoadsAndLibraries.roadsAndLibraries(n, c_lib, c_road, cities);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    bufferedReader.close();
    bufferedWriter.close();
  }
}
