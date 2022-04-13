package hackerrank.sorting;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Stream;

/**
 * @see <a
 * href="https://www.hackerrank.com/challenges/fraudulent-activity-notifications/problem">Fraudulent
 * Activity Notifications</a>
 */
public class FraudulentActivityNotifications {

  static int activityNotifications(List<Integer> expenditure, int d) {
    int[] arr = new int[expenditure.size()];
    for (int i = 0; i < expenditure.size(); i++) {
      arr[i] = expenditure.get(i);
    }
    int notificationCount = 0;

    int[] data = new int[201];
    for (int i = 0; i < d; i++) {
      data[arr[i]]++;
    }

    for (int i = d; i < arr.length; i++) {

      double median = getMedian(d, data);

      if (arr[i] >= 2 * median) {
        notificationCount++;

      }

      data[arr[i]]++;
      data[arr[i - d]]--;

    }

    return notificationCount;

  }

  private static double getMedian(int d, int[] data) {
    double median = 0;
    if (d % 2 == 0) {
      Integer m1 = null;
      Integer m2 = null;
      int count = 0;
      for (int j = 0; j < data.length; j++) {
        count += data[j];
        if (m1 == null && count >= d / 2) {
          m1 = j;
        }
        if (m2 == null && count >= d / 2 + 1) {
          m2 = j;
          break;
        }
      }
      median = (m1 + m2) / 2.0;
    } else {
      int count = 0;
      for (int j = 0; j < data.length; j++) {
        count += data[j];
        if (count > d / 2) {
          median = j;
          break;
        }
      }
    }
    return median;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

    int n = Integer.parseInt(firstMultipleInput[0]);

    int d = Integer.parseInt(firstMultipleInput[1]);

    List<Integer> expenditure = Stream.of(
            bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
        .map(Integer::parseInt)
        .collect(toList());

    int result = activityNotifications(expenditure, d);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
