package hackerrank.warmupchallenges;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link AVeryBigSum}
 */
public class AVeryBigSumTest {

  @Test
  public void testAVeryBigSum() {
    List<Long> data = Arrays.asList(500L, 501L, 502L, 503L, 504L);
    assertEquals(2510L, AVeryBigSum.aVeryBigSum(data));
  }
}
