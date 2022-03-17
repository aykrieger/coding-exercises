package greedyalgorithms;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @see <a href="https://www.hackerrank.com/challenges/special-palindrome-again">Reverse Shuffle Merge</a>
 */
class ReverseShuffleMerge {

  /*
   * Complete the 'reverseShuffleMerge' function below.
   *
   * The function is expected to return a STRING.
   * The function accepts STRING s as parameter.
   */
  static String reverseShuffleMerge(String s) {
    char[] arr = s.toCharArray();
    Map<Character, Integer> freqMap = new HashMap<>();

    for (int i = 0; i < arr.length; i++) {
      char curr = arr[i];
      freqMap.put(curr, freqMap.getOrDefault(curr, 0) + 1);
    }

    Map<Character, Integer> usedCount = new HashMap<>();
    Map<Character, Integer> aheadCount = new HashMap<>(freqMap);
    StringBuilder result = new StringBuilder();

    for (int i = arr.length - 1; i >= 0; i--) {
      char curr = arr[i];
      aheadCount.put(curr, aheadCount.get(curr) - 1);
      // If we have already used enough occurrences of this character
      // in our result, then we can skip it
      if (usedCount.getOrDefault(curr, 0) == freqMap.get(curr) / 2) {
        continue;
      }

      while (result.length() > 0) {
        char last = result.charAt(result.length() - 1);
        // If the current character is less than the last character lexicographically
        if ((curr < last) &&
            // and there are enough of the last character ahead so that we can
            // safely delete it
            (usedCount.getOrDefault(last, 0) + aheadCount.get(last) - 1 >=
                freqMap.get(last) / 2)) {
          // Then we delete the last character and replace it with the current
          // character after the while loop
          result.deleteCharAt(result.length() - 1);
          usedCount.put(last, usedCount.get(last) - 1);
        } else {
          break;
        }
      }

      result.append(curr);
      usedCount.put(curr, usedCount.getOrDefault(curr, 0) + 1);
    }

    return result.toString();
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    String s = bufferedReader.readLine();

    String result = ReverseShuffleMerge.reverseShuffleMerge(s);

    bufferedWriter.write(result);
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
