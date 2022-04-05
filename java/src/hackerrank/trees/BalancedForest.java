package hackerrank.trees;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * @see <a href="https://www.hackerrank.com/challenges/balanced-forest/problem">Balanced Forest</a>
 */
public class BalancedForest {

  /*
   * Complete the 'balancedForest' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts following parameters:
   *  1. INTEGER_ARRAY c
   *  2. 2D_INTEGER_ARRAY edges
   */

  static class Node {

    int value;
    int id;
    boolean dfsVisited;
    boolean addedToRecursiveStack;
    long sum;

    Set<Node> neighbors = new HashSet<Node>();

    @Override
    public int hashCode() {
      return id;
    }
  }

  static long total;
  static long min;

  // The set of sums of nodes in the recursive "solve" stack
  static Set<Long> sumSetInRecursiveStack;
  // Set of sums that were popped from the recursive "solve" stack
  static Set<Long> sumSetPoppedFromStack;

  public static long balancedForest(List<Integer> c, List<List<Integer>> edges) {
    sumSetInRecursiveStack = new HashSet<>();
    sumSetPoppedFromStack = new HashSet<>();

    // Build nodes
    HashMap<Integer, Node> nodeMap = new HashMap<Integer, Node>();
    for (int i = 0; i < c.size(); i++) {
      Node curr = new Node();
      curr.value = c.get(i);
      curr.id = i + 1;
      nodeMap.put(i, curr);
    }

    // Build graph
    for (int i = 0; i < edges.size(); i++) {
      Node first = nodeMap.get(edges.get(i).get(0) - 1);
      Node second = nodeMap.get(edges.get(i).get(1) - 1);
      first.neighbors.add(second);
      second.neighbors.add(first);
    }

    // Set the first node as the root, any node would work
    Node root = nodeMap.get(0);

    // Total of all nodes' values
    total = dfs(root);
    min = total;

    solve(root);
    return (min == total ? -1 : min);
  }

  static void solve(Node curr) {
    if (curr.addedToRecursiveStack) {
      return;
    }

    curr.addedToRecursiveStack = true;
    long leftForTwoNodes = total - curr.sum;
    long possibleMin = (leftForTwoNodes / 2) - curr.sum;

    // Case 1: The current node is the smallest subtree in the solution
    // Check if there are two other subtrees of equal sum
    if (leftForTwoNodes % 2 == 0
        && (sumSetPoppedFromStack.contains(leftForTwoNodes / 2)
        || sumSetInRecursiveStack.contains((leftForTwoNodes / 2) + curr.sum))
        && possibleMin >= 0) {
      min = Math.min(min, possibleMin);
    }

    // Case 2: The current node is one of the two equal sum subtrees in the solution
    // Check if this subtree fits that solution
    possibleMin = curr.sum - (total - curr.sum * 2);
    if ((sumSetPoppedFromStack.contains(curr.sum)
        || sumSetPoppedFromStack.contains(total - curr.sum * 2)
        || sumSetInRecursiveStack.contains(curr.sum * 2)
        || sumSetInRecursiveStack.contains(total - curr.sum))
        && possibleMin >= 0) {
      min = Math.min(min, possibleMin);
    }

    // Add the current node sum to the recursive set for other subtrees to use
    sumSetInRecursiveStack.add(curr.sum);
    for (Node neighbor : curr.neighbors) {
      solve(neighbor);
    }
    ;

    // The current node sum is no longer available in the recursive set
    sumSetInRecursiveStack.remove(curr.sum);

    // Add the current node sum to the popped set for other subtrees to use
    sumSetPoppedFromStack.add(curr.sum);
  }

  static long dfs(Node curr) {
    if (curr.dfsVisited) {
      return 0;
    }

    curr.dfsVisited = true;
    for (Node neighbor : curr.neighbors) {
      if (!neighbor.dfsVisited) {
        curr.sum += dfs(neighbor);
      }
    }
    curr.sum += curr.value;
    return curr.sum;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    int q = Integer.parseInt(bufferedReader.readLine().trim());

    IntStream.range(0, q).forEach(qItr -> {
      try {
        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> c = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<List<Integer>> edges = new ArrayList<>();

        IntStream.range(0, n - 1).forEach(i -> {
          try {
            edges.add(
                Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList())
            );
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        });

        long result = BalancedForest.balancedForest(c, edges);

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
