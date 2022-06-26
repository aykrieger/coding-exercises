package hackerrank.dictionariesandhashmaps;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Stream;

/**
 * @see <a href="https://www.hackerrank.com/challenges/ctci-ransom-note/problem">Hash Tables: Ransom
 * Note</a>
 */
public class HashTablesRansomNote {

  /*
   * Complete the 'checkMagazine' function below.
   *
   * The function accepts following parameters:
   *  1. STRING_ARRAY magazine
   *  2. STRING_ARRAY note
   */

  public static void checkMagazine(List<String> magazine, List<String> note) {
    boolean pass = true;
    for (String word : note) {
      if (!magazine.remove(word)) {
        pass = false;
        break;
      }
    }
    if (pass) {
      System.out.println("Yes");
    } else {
      System.out.println("No");
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

    int m = Integer.parseInt(firstMultipleInput[0]);

    int n = Integer.parseInt(firstMultipleInput[1]);

    List<String> magazine = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
        .collect(toList());

    List<String> note = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
        .collect(toList());

    checkMagazine(magazine, note);

    bufferedReader.close();
  }
}
