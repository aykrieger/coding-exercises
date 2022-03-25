package stacksandqueues;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.IntStream;

/**
 * @see <a href="https://www.hackerrank.com/challenges/balanced-brackets/problem">Balanced
 * Brackets</a>
 */
public class BalancedBrackets {

  /*
   * Complete the 'isBalanced' function below.
   *
   * The function is expected to return a STRING.
   * The function accepts STRING s as parameter.
   */
  public static String isBalanced(String s) {
    char[] arr = s.toCharArray();
    HashMap<Character, Character> bracketMap = new HashMap<>();
    bracketMap.put('(', ')');
    bracketMap.put('[', ']');
    bracketMap.put('{', '}');
    Deque<Character> stack = new LinkedList<>();

    for (int i = 0; i < arr.length; i++) {
      Character curr = arr[i];
      if (bracketMap.containsKey(curr)) {
        stack.push(curr);
      } else {
        Character popped = stack.poll();
        if (curr != bracketMap.get(popped)) {
          return "NO";
        }
      }
    }
    return stack.isEmpty() ? "YES" : "NO";
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    int t = Integer.parseInt(bufferedReader.readLine().trim());

    IntStream.range(0, t).forEach(tItr -> {
      try {
        String s = bufferedReader.readLine();

        String result = BalancedBrackets.isBalanced(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    bufferedReader.close();
    bufferedWriter.close();
  }
}
