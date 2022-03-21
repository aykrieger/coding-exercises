package search;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * @see <a href="https://www.hackerrank.com/challenges/swap-nodes-algo/problem">Swap Nodes</a>
 */
public class SwapNodes {

  static class Node {
    int value;
    int depth;

    Node left = null;
    Node right = null;

    Node (int value, int depth) {
      this.value = value;
      this.depth = depth;
    }
  }

  static List<Integer> queryResult = null;

  /*
   * Complete the 'swapNodes' function below.
   *
   * The function is expected to return a 2D_INTEGER_ARRAY.
   * The function accepts following parameters:
   *  1. 2D_INTEGER_ARRAY indexes
   *  2. INTEGER_ARRAY queries
   */
  public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {
    List<List<Integer>> result = new ArrayList<>();

    Node root = buildTree(indexes);

    int idx = 0;
    while(idx < queries.size()) {
      queryResult = new ArrayList<>();
      switchNodes(root, queries.get(idx));
      addToQueryResult(root);
      System.out.println("queryResult = " + queryResult.toString());
      result.add(new ArrayList<>(queryResult));
      idx++;
    }

    return result;

  }

  public static void addToQueryResult(Node root) {
    if (root != null) {
      addToQueryResult(root.left);
      System.out.print(" V: " + root.value + " D: " + root.depth);
      queryResult.add(root.value);
      addToQueryResult(root.right);
    }
  }

  public static void switchNodes(Node root, int k) {
    if (root != null) {
      if (root.depth % k == 0) {
        Node temp = root.left;
        root.left = root.right;
        root.right = temp;
      }
      switchNodes(root.left, k);
      switchNodes(root.right, k);
    }
  }

  public static Node buildTree(List<List<Integer>> indexes) {
    Node root = new Node(1, 1);
    Queue<Node> nodes = new LinkedList<>();
    Node curr = root;
    nodes.offer(curr);

    for (int i = 0; i < indexes.size(); i++) {
      curr = nodes.poll();
      int leftVal = indexes.get(i).get(0);
      int rightVal = indexes.get(i).get(1);

      curr.left = (leftVal == -1) ? null : new Node(leftVal, curr.depth + 1);
      curr.right = (rightVal == -1) ? null : new Node(rightVal, curr.depth + 1);

      if (curr.left != null && curr.left.value != -1) {
        nodes.offer(curr.left);
      }
      if (curr.right != null && curr.right.value != -1) {
        nodes.offer(curr.right);
      }
    }

    return root;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int n = Integer.parseInt(bufferedReader.readLine().trim());

    List<List<Integer>> indexes = new ArrayList<>();

    IntStream.range(0, n).forEach(i -> {
      try {
        indexes.add(
            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList())
        );
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());

    List<Integer> queries = IntStream.range(0, queriesCount).mapToObj(i -> {
          try {
            return bufferedReader.readLine().replaceAll("\\s+$", "");
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        })
        .map(String::trim)
        .map(Integer::parseInt)
        .collect(toList());

    List<List<Integer>> result = swapNodes(indexes, queries);


    result.stream()
        .map(
            r -> r.stream()
                .map(Object::toString)
                .collect(joining(" "))
        )
        .map(r -> r + "\n")
        .collect(toList())
        .forEach(e -> {
          try {
            bufferedWriter.write(e);
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        });

    bufferedReader.close();
    bufferedWriter.close();
  }
}
