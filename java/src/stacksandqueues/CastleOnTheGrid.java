package stacksandqueues;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.IntStream;

public class CastleOnTheGrid {

  /*
   * Complete the 'minimumMoves' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts following parameters:
   *  1. STRING_ARRAY grid
   *  2. INTEGER startX
   *  3. INTEGER startY
   *  4. INTEGER goalX
   *  5. INTEGER goalY
   */
  public static int minimumMoves(List<String> grid, int startX, int startY, int goalX, int goalY) {

    /*
    Step 1: Travel through every open spot on the map, mark every location that can be reached in
    1 move with the number 1.
    Step 2: Travel through every open spot on the map, mark every location that can be reached in
    2 moves with the number 2.
    Step 3: Repeat the previous step until you find the goal. When you do, return the mark at that
    location. That will be how many moves it took to get to the goal.
    */
    int[][] map = new int[grid.size()][grid.get(0).length()];
    // Build the map
    for (int i = 0; i < grid.size(); i++) {
      for (int j = 0; j < grid.get(0).length(); j++) {
        // -1 is a valid space, -2 is an obstacle
        map[i][j] = grid.get(i).charAt(j) == '.' ? -1 : -2;
      }
    }

    map[startX][startY] = 0;
    int moves = 0;
    while (true) {
      moves++;
      for (int i = 0; i < map.length; i++) {
        for (int j = 0; j < map[0].length; j++) {
          if (map[i][j] == -1) {
            exploreMap(map, i, j, moves);
            if (i == goalX && j == goalY && map[i][j] != -1) {
              return moves;
            }
          }
        }
      }
    }
  }

  private static void exploreMap(int[][] map, int x, int y, int moves) {
    // Explore left
    for (int j = y - 1; j > -1 && map[x][j] != -2; j--) {
      if (map[x][j] == moves - 1) {
        map[x][y] = moves;
      }
    }

    // Explore right
    for (int j = y + 1; j < map[0].length && map[x][j] != -2; j++) {
      if (map[x][j] == moves - 1) {
        map[x][y] = moves;
      }
    }

    // Explore up
    for (int i = x - 1; i > -1 && map[i][y] != -2; i--) {
      if (map[i][y] == moves - 1) {
        map[x][y] = moves;
      }
    }

    // Explore down
    for (int i = x + 1; i < map.length && map[i][y] != -2; i++) {
      if (map[i][y] == moves - 1) {
        map[x][y] = moves;
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    int n = Integer.parseInt(bufferedReader.readLine().trim());

    List<String> grid = IntStream.range(0, n).mapToObj(i -> {
          try {
            return bufferedReader.readLine();
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        })
        .collect(toList());

    String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

    int startX = Integer.parseInt(firstMultipleInput[0]);

    int startY = Integer.parseInt(firstMultipleInput[1]);

    int goalX = Integer.parseInt(firstMultipleInput[2]);

    int goalY = Integer.parseInt(firstMultipleInput[3]);

    int result = CastleOnTheGrid.minimumMoves(grid, startX, startY, goalX, goalY);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
