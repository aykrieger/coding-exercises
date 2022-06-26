package hackerrank.search;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/triple-sum/problem">Minimum Time Required</a>
 */
public class MinimumTimeRequired {

  // Complete the minTime function below.
  static long minTime(long[] machines, long goal) {
    Arrays.sort(machines);
    long result = 0;
    long max = machines[machines.length - 1];
    long minDays = 0;
    long maxDays = max * goal;

    while (minDays < maxDays) {
      long currDays = (minDays + maxDays) / 2;
      long unit = 0;

      for (long machine : machines) {
        unit += currDays / machine;
      }

      if (unit < goal) {
        minDays = currDays + 1;
      } else {
        result = currDays;
        maxDays = currDays;
      }
    }

    return result;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    String[] nGoal = scanner.nextLine().split(" ");

    int n = Integer.parseInt(nGoal[0]);

    long goal = Long.parseLong(nGoal[1]);

    long[] machines = new long[n];

    String[] machinesItems = scanner.nextLine().split(" ");
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int i = 0; i < n; i++) {
      long machinesItem = Long.parseLong(machinesItems[i]);
      machines[i] = machinesItem;
    }

    long ans = minTime(machines, goal);

    bufferedWriter.write(String.valueOf(ans));
    bufferedWriter.newLine();

    bufferedWriter.close();

    scanner.close();
  }
}
