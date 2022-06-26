package hackerrank.stacksandqueues;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

/**
 * @see <a href="https://www.hackerrank.com/challenges/poisonous-plants/problem">Poisonous
 * Plants</a>
 */
public class PoisonousPlants {

  /*
   * Complete the 'poisonousPlants' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts INTEGER_ARRAY p as parameter.
   */
  public static int poisonousPlants(List<Integer> p) {
    Stack<Pair> stack = new Stack<>();
    int days = 0;
    for (int i = p.size() - 1; i >= 0; i--) {
      int temp = 0;
      while (!stack.empty() && p.get(i) < stack.peek().val) {
        temp++;
        Pair pair = stack.pop();
        temp = Math.max(temp, pair.count);
      }
      days = Math.max(days, temp);
      stack.push(new Pair(p.get(i), temp));
    }

    return days;
  }

  public static class Pair {

    int val = 0;
    int count = 0;

    public Pair(int val, int count) {
      this.val = val;
      this.count = count;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    int n = Integer.parseInt(bufferedReader.readLine().trim());

    List<Integer> p = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
        .map(Integer::parseInt)
        .collect(toList());

    int result = PoisonousPlants.poisonousPlants(p);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
