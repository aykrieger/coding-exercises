package hackerrank.graphs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/find-the-nearest-clone/problem">Find the
 * Nearest Clone</a>
 */
public class FindTheNearestClone {

  private static HashMap<Integer, ArrayList<Integer>> adjMap = new HashMap<Integer, ArrayList<Integer>>();
  private static boolean[] visitedNodes;

  // Complete the findShortest function below.

  /*
   * For the unweighted graph, <name>:
   *
   * 1. The number of nodes is <name>Nodes.
   * 2. The number of edges is <name>Edges.
   * 3. An edge exists between <name>From[i] to <name>To[i].
   *
   */
  static int findShortest(int graphNodes, int[] graphFrom, int[] graphTo, int[] colors,
      int targetColor) {

    visitedNodes = new boolean[graphNodes + 1];

    int targetColors = 0;
    for (int i = 1; i <= graphNodes; i++) {
      adjMap.put(i, new ArrayList<Integer>());
      if (colors[i - 1] == targetColor) {
        targetColors++;
      }
    }

    // If there are less than two nodes that are the target color, there is no shortest path
    if (targetColors < 2) {
      return -1;
    }

    // Build the graph
    for (int i = 0; i < graphFrom.length; i++) {
      int node1 = graphFrom[i];
      int node2 = graphTo[i];
      adjMap.get(node1).add(node2);
      adjMap.get(node2).add(node1);
    }

    // Initialize as the longest path, which is the number of nodes
    int shortestPath = graphNodes;

    // For each node of the target color, calculate the shortest path from that node
    // to another node of the target color
    for (Map.Entry<Integer, ArrayList<Integer>> entry : adjMap.entrySet()) {
      int nodeIdx = entry.getKey();

      // nodeIdx 1's color is at ids[0] because the nodes indices start at 1
      int currColor = colors[nodeIdx - 1];

      if (currColor != targetColor) {
        continue;
      }

      int currPath = 0;
      currPath += distanceToTargetColor(nodeIdx, targetColor, colors);
      shortestPath = Math.min(shortestPath, currPath);
    }

    return shortestPath;
  }

  static int distanceToTargetColor(int nodeIdx, int targetColor, int[] colors) {
    visitedNodes[nodeIdx] = true;
    ArrayList<Integer> currAdj = adjMap.get(nodeIdx);
    int shortestPath = visitedNodes.length;

    for (int i = 0; i < currAdj.size(); i++) {
      int currNodeIdx = currAdj.get(i);

      System.out.println("origNode " + nodeIdx + " currNode " + currNodeIdx);

      // We can skip nodes that have already been visited because
      // visiting those nodes again will not give us a shorter distance to the
      // target color
      if (visitedNodes[currNodeIdx] == true) {
        System.out.println("Already visited " + currNodeIdx);
        continue;
      }

      // Base case, if this node is the target color, then the path from the original
      // node to this node is 1
      if (colors[currNodeIdx - 1] == targetColor) {
        return 1;
      }

      int currPath = 1;
      // This node is not the target color, find the distance to the target color for
      // this node's neighbors
      currPath += distanceToTargetColor(currNodeIdx, targetColor, colors);

      shortestPath = Math.min(shortestPath, currPath);
    }

    return shortestPath;
  }


  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    String[] graphNodesEdges = br.readLine().split(" ");
    int graphNodes = Integer.parseInt(graphNodesEdges[0].trim());
    int graphEdges = Integer.parseInt(graphNodesEdges[1].trim());

    int[] graphFrom = new int[graphEdges];
    int[] graphTo = new int[graphEdges];

    for (int i = 0; i < graphEdges; i++) {
      String[] graphFromTo = br.readLine().split(" ");
      graphFrom[i] = Integer.parseInt(graphFromTo[0].trim());
      graphTo[i] = Integer.parseInt(graphFromTo[1].trim());
    }

    int[] ids = new int[graphNodes];

    String[] idsItems = br.readLine().split(" ");

    for (int i = 0; i < graphNodes; i++) {
      int idsItem = Integer.parseInt(idsItems[i]);
      ids[i] = idsItem;
    }

    int val = Integer.parseInt(br.readLine().split(" ")[0]);
    br.close();

    int ans = findShortest(graphNodes, graphFrom, graphTo, ids, val);

    bufferedWriter.write(String.valueOf(ans));
    bufferedWriter.newLine();

    bufferedWriter.close();
  }
}
