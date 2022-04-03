package hackerrank.graphs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @see <a href="https://www.hackerrank.com/challenges/matrix/problem">Matrix</a>
 */
public class Matrix {

  /*
   * Complete the 'minTime' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts following parameters:
   *  1. 2D_INTEGER_ARRAY roads
   *  2. INTEGER_ARRAY machines
   */

  static List<Node> nodes;
  static PriorityQueue<Edge> edges;

  // Complete the minTime function below.
  static int minTime(int[][] roads, int[] machines) {
    int n = roads.length + 1;
    buildEdgeQueue(roads);
    buildNodes(n, machines);

    int time = 0;

    while (!edges.isEmpty()) {
      Edge currentEdge = edges.poll();
      int firstRoot = pathCompressionFind(currentEdge.firstNode);
      int secondRoot = pathCompressionFind(currentEdge.secondNode);
      if (firstRoot != secondRoot) {
        // If these roots can not union, that means each has a
        // machine and we can increase the total time by this edge's time
        boolean canUnion = union(firstRoot, secondRoot);
        if (!canUnion) {
          time += currentEdge.time;
        }
      }
    }

    return time;
  }

  static void buildEdgeQueue(int[][] roads) {
    edges = new PriorityQueue<>();
    for (int[] road : roads) {
      edges.add(new Edge(road[0], road[1], road[2]));
    }
  }

  static void buildNodes(int numNodes, int[] machines) {
    nodes = new ArrayList<>();

    for (int i = 0; i < numNodes; i++) {
      nodes.add(new Node(i));
    }
    for (int machineIdx : machines) {
      nodes.get(machineIdx).hasMachine = true;
    }
  }

  static int find(int nodeIdx) {
    int parentIdx = nodes.get(nodeIdx).parentIdx;
    if (parentIdx == nodeIdx) {
      return nodeIdx;
    } else {
      return find(parentIdx);
    }
  }

  static int pathCompressionFind(int nodeIdx) {
    Node curr = nodes.get(nodeIdx);
    int parent = curr.parentIdx;
    if (parent == nodeIdx) {
      return nodeIdx;
    } else {
      int parentIdx = find(parent);
      curr.parentIdx = parentIdx;
      return parentIdx;
    }
  }

  static boolean union(int firstIdx, int secondIdx) {
    Node firstNode = nodes.get(firstIdx);
    Node secondNode = nodes.get(secondIdx);
    if (firstNode.hasMachine && secondNode.hasMachine) {
      return false;
    }

    firstNode.parentIdx = secondIdx;
    secondNode.hasMachine = firstNode.hasMachine || secondNode.hasMachine;
    return true;
  }

  static class Edge implements Comparable<Edge> {

    int firstNode;
    int secondNode;
    int time;

    Edge(int firstNode, int secondNode, int time) {
      this.firstNode = firstNode;
      this.secondNode = secondNode;
      this.time = time;
    }

    @Override
    public int compareTo(Edge e) {
      return (e).time - this.time;
    }
  }

  static class Node {

    int parentIdx;
    boolean hasMachine = false;

    Node(int parentIdx) {
      this.parentIdx = parentIdx;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

    int n = Integer.parseInt(firstMultipleInput[0]);

    int k = Integer.parseInt(firstMultipleInput[1]);

    int[][] roads = new int[n][3];

    for (int i = 0; i < (n - 1); i++) {
      String[] cities = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
      roads[i][0] = Integer.parseInt(cities[0]);
      roads[i][1] = Integer.parseInt(cities[1]);
      roads[i][2] = Integer.parseInt(cities[2]);
    }

    int[] machines = new int[k];

    for (int i = 0; i < k; i++) {
      String[] machine = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
      machines[i] = Integer.parseInt(machine[0]);
    }

    int result = Matrix.minTime(roads, machines);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}