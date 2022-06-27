package hackerrank.warmupchallenges;

import java.util.List;

/**
 * @see <a href="https://www.hackerrank.com/challenges/a-very-big-sum/problem">A Very Big Sum</a>
 */
public class AVeryBigSum {

  public static long aVeryBigSum(List<Long> ar) {
    return ar.stream().mapToLong(Long::longValue).sum();
  }

}
