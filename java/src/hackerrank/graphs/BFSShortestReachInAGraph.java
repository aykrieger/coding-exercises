package hackerrank.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/ctci-bfs-shortest-reach/problem">BFS:
 * Shortest Reach in a Graph</a>
 */
public class BFSShortestReachInAGraph {

  public static class Graph {

    private HashMap<Integer, ArrayList<Integer>> adjMap;
    private int[] distances;
    private boolean[] visited;

    public Graph(int size) {
      visited = new boolean[size];
      adjMap = new HashMap<Integer, ArrayList<Integer>>();
      distances = new int[size];

      int i = size - 1;
      while (i > -1) {
        adjMap.put(i, new ArrayList<Integer>());
        distances[i] = -1;
        i--;
      }
    }

    public void addEdge(int first, int second) {
      adjMap.get(first).add(second);
      adjMap.get(second).add(first);
    }

    public int[] shortestReach(int startId) {
      ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
      deque.offerFirst(startId);
      visited[startId] = true;
      distances[startId] = 0;

      while (!deque.isEmpty()) {
        int curr = deque.pollFirst();
        ArrayList<Integer> neighbors = adjMap.get(curr);
        // Add neighbors to deque
        for (int i = 0; i < neighbors.size(); i++) {
          int currNeighbor = neighbors.get(i);
          if (!visited[currNeighbor]) {
            visited[currNeighbor] = true;
            distances[currNeighbor] = distances[curr] + 6;
            deque.offerLast(currNeighbor);
          }
        }
      }
      return distances;
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int queries = scanner.nextInt();

    for (int t = 0; t < queries; t++) {

      // Create a graph of size n where each edge weight is 6:
      Graph graph = new Graph(scanner.nextInt());
      int m = scanner.nextInt();

      // read and set edges
      for (int i = 0; i < m; i++) {
        int u = scanner.nextInt() - 1;
        int v = scanner.nextInt() - 1;

        // add each edge to the graph
        graph.addEdge(u, v);
      }

      // Find shortest reach from node s
      int startId = scanner.nextInt() - 1;
      int[] distances = graph.shortestReach(startId);

      for (int i = 0; i < distances.length; i++) {
        if (i != startId) {
          System.out.print(distances[i]);
          System.out.print(" ");
        }
      }
      System.out.println();
    }

    scanner.close();
  }
}