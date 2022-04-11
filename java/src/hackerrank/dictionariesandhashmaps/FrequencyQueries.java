package hackerrank.dictionariesandhashmaps;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @see <a href="https://www.hackerrank.com/challenges/frequency-queries/problem">Frequency
 * Queries</a>
 */
public class FrequencyQueries {

  private static final Map<Integer, Integer> map = new HashMap<>();
  private static final Map<Integer, Integer> freq = new HashMap<>();
  private static final StringBuilder sb = new StringBuilder();

  private static void freqQuery(int option, int value) {

    switch (option) {
      case 1: {
        Integer currFreq = map.get(value);
        if (currFreq == null) {
          currFreq = 0;
        }

        map.put(value, currFreq + 1);

        if (currFreq + 1 > 1) {
          freq.put(currFreq, freq.get(currFreq) - 1);
        }

        Integer newFreqCount = freq.get(currFreq + 1);
        freq.put(currFreq + 1, newFreqCount == null ? 1 : newFreqCount + 1);

        break;
      }
      case 2: {
        Integer currFreq = map.get(value);

        if (currFreq != null && currFreq > 0) {
          map.put(value, currFreq - 1);

          if (currFreq - 1 > 0) {
            freq.put(currFreq - 1, freq.get(currFreq - 1) + 1);
          }

          freq.put(currFreq, freq.get(currFreq) - 1);
        }

        break;
      }
      case 3: {
        Integer count = freq.get(value);
        sb.append((count != null && count > 0 ? 1 : 0) + "\n");
        break;
      }
      default:
        break;
    }
  }

  public static void main(String[] args) throws IOException {
    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

      int q = Integer.parseInt(bufferedReader.readLine().trim());

      for (int i = 0; i < q; i++) {
        String[] query = bufferedReader.readLine().split(" ");
        freqQuery(Integer.parseInt(query[0]), Integer.parseInt(query[1]));
      }

      try (BufferedWriter bufferedWriter = new BufferedWriter(
          new FileWriter(System.getenv("OUTPUT_PATH")))) {
        bufferedWriter.write(sb.toString());
      }

    }
  }
}
