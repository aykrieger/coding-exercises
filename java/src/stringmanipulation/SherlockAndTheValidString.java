package stringmanipulation;

import java.io.*;
import java.util.Arrays;

/**
 * @see <a href="https://www.hackerrank.com/challenges/sherlock-and-valid-string/problem">Sherlock and the Valid String</a>
 */
public class SherlockAndTheValidString {

  static String isValid(String s) {
    if (s.isEmpty()) {
      return "NO";
    }

    if (s.length() <= 3) {
      return "YES";
    }

    int[] letters = new int[26];
    for(int i = 0; i < s.length(); i++){
      letters[s.charAt(i) - 'a']++;
    }

    Arrays.sort(letters);

    int i = 0;
    while (letters[i] == 0){
      i++;
    }

    int minFreq = letters[i];
    int maxFreq = letters[25];
    boolean result = false;
    if (minFreq == maxFreq) {
      result = true;
    } else {
      // Remove a letter at either the higher frequency or the lower frequency
      if (((maxFreq - minFreq == 1) && (maxFreq > letters[24])) ||
          (minFreq == 1) && (letters[i+1] == maxFreq)) {
        result = true;
      }
    }
    return result ? "YES" : "NO";
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    String s = bufferedReader.readLine();

    String result = SherlockAndTheValidString.isValid(s);

    bufferedWriter.write(result);
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
