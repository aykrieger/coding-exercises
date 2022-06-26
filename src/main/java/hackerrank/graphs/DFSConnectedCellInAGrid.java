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
 * @see <a href="https://www.hackerrank.com/challenges/ctci-connected-cell-in-a-grid/problem">DFS:
 * Connected Cell in a Grid</a>
 */
public class DFSConnectedCellInAGrid {

  private static class Graph {

    private List<List<Integer>> grid;
    private boolean[][] visited;
    int rowSize;
    int colSize;

    public Graph(List<List<Integer>> grid) {
      this.grid = grid;
      this.rowSize = grid.size();
      this.colSize = grid.get(0).size();
      this.visited = new boolean[rowSize][colSize];
    }

    public int maxRegion() {
      int result = 0;
      for (int row = 0; row < rowSize; row++) {
        for (int col = 0; col < colSize; col++) {
          if (!visited[row][col] && grid.get(row).get(col) == 1) {
            result = Math.max(result, cellsInRegion(row, col));
          }
        }
      }
      return result;
    }

    private int cellsInRegion(int initRow, int initCol) {
      int count = 0;

      // Explore the 8 adjacent cells
      for (int row = initRow - 1; row < initRow + 2; row++) {
        for (int col = initCol - 1; col < initCol + 2; col++) {
          boolean invalidRow = row < 0 || row > (rowSize - 1);
          boolean invalidCol = col < 0 || col > (colSize - 1);
          if (invalidRow || invalidCol) {
            continue;
          }

          if (!visited[row][col] && grid.get(row).get(col) == 1) {
            count++;
            visited[row][col] = true;
            count += cellsInRegion(row, col);
          }
        }
      }

      return count;
    }
  }

  /*
   * Complete the 'maxRegion' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts 2D_INTEGER_ARRAY grid as parameter.
   */

  public static int maxRegion(List<List<Integer>> grid) {
    Graph graph = new Graph(grid);
    return graph.maxRegion();
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    int n = Integer.parseInt(bufferedReader.readLine().trim());

    int m = Integer.parseInt(bufferedReader.readLine().trim());

    List<List<Integer>> grid = new ArrayList<>();

    IntStream.range(0, n).forEach(i -> {
      try {
        grid.add(
            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList())
        );
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    int res = DFSConnectedCellInAGrid.maxRegion(grid);

    bufferedWriter.write(String.valueOf(res));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
