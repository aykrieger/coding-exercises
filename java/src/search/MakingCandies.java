package search;

import java.io.*;

/**
 * @see <a href="https://www.hackerrank.com/challenges/making-candies/problem">Making Candies</a>
 */
public class MakingCandies {

  /*
   * Complete the 'minimumPasses' function below.
   *
   * The function is expected to return a LONG_INTEGER.
   * The function accepts following parameters:
   *  1. LONG_INTEGER m
   *  2. LONG_INTEGER w
   *  3. LONG_INTEGER p
   *  4. LONG_INTEGER n
   */
  static long minimumPasses(long m, long w, long p, long n) {
    long min = 1;
    long max = 10000000000000L;

    while (min < max) {
      long mid = (min + max)/2;

      if (check(m , w , p , n , mid)) {
        max = mid;
      } else {
        min = mid + 1;
      }
    }
    return min;
  }

  static boolean  check(long m , long w , long p , long target , long rounds) {
    if(m >= (target + w - 1) / w) {
      return true;
    }

    long curr = m * w;
    rounds--;

    if (rounds == 0) {
      return false;
    }

    while(true) {

      long rem = target - curr;
      long required = (rem + (m * w) -1) / (m * w);

      if (rounds >= required) {
        return true;
      }

      if (curr < p) {
        rem = p - curr;
        required = (rem  + (m * w) -1 ) / (m * w);
        rounds = rounds - required;
        if (rounds < 1) {
          return false;
        }

        curr += required*m*w;
      }

      curr = curr - p;
      if( m > w) {
        w++;
      } else {
        m++;
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

    long m = Long.parseLong(firstMultipleInput[0]);

    long w = Long.parseLong(firstMultipleInput[1]);

    long p = Long.parseLong(firstMultipleInput[2]);

    long n = Long.parseLong(firstMultipleInput[3]);

    long result = MakingCandies.minimumPasses(m, w, p, n);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
