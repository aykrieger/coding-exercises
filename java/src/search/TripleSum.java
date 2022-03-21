package search;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/triple-sum/problem">Triple Sum</a>
 */
public class TripleSum {

  // Complete the triplets function below.
  static long triplets(int[] a, int[] b, int[] c) {
    long result = 0;

    Arrays.sort(a);
    Arrays.sort(b);
    Arrays.sort(c);
    a = Arrays.stream(a).distinct().toArray();
    b = Arrays.stream(b).distinct().toArray();
    c = Arrays.stream(c).distinct().toArray();

    int idxA = 0;
    int idxB = 0;
    int idxC = 0;
    for (idxB = 0; idxB < b.length; idxB++) {
      while (idxA < a.length && a[idxA] <= b[idxB]) {
        idxA++;
      }

      while (idxC < c.length && c[idxC] <= b[idxB]) {
        idxC++;
      }

      result += (long) idxA * (long) idxC;
    }

    return result;

  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    String[] lenaLenbLenc = scanner.nextLine().split(" ");

    int lena = Integer.parseInt(lenaLenbLenc[0]);

    int lenb = Integer.parseInt(lenaLenbLenc[1]);

    int lenc = Integer.parseInt(lenaLenbLenc[2]);

    int[] arra = new int[lena];

    String[] arraItems = scanner.nextLine().split(" ");
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int i = 0; i < lena; i++) {
      int arraItem = Integer.parseInt(arraItems[i]);
      arra[i] = arraItem;
    }

    int[] arrb = new int[lenb];

    String[] arrbItems = scanner.nextLine().split(" ");
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int i = 0; i < lenb; i++) {
      int arrbItem = Integer.parseInt(arrbItems[i]);
      arrb[i] = arrbItem;
    }

    int[] arrc = new int[lenc];

    String[] arrcItems = scanner.nextLine().split(" ");
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int i = 0; i < lenc; i++) {
      int arrcItem = Integer.parseInt(arrcItems[i]);
      arrc[i] = arrcItem;
    }

    long ans = triplets(arra, arrb, arrc);

    bufferedWriter.write(String.valueOf(ans));
    bufferedWriter.newLine();

    bufferedWriter.close();

    scanner.close();
  }
}
