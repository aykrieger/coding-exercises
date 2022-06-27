package aykrieger;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for {@link FirstNonDuplicate}
 */
public class FirstNonDuplicateTest {

  @Test
  public void testFirstNonDuplicate() {
    FirstNonDuplicate firstNonDuplicate = new FirstNonDuplicate();
    firstNonDuplicate.add(10);
    firstNonDuplicate.add(11);
    firstNonDuplicate.add(12);
    assertEquals(10, firstNonDuplicate.firstNonDuplicate());
    firstNonDuplicate.add(10);
    assertEquals(11, firstNonDuplicate.firstNonDuplicate());
    firstNonDuplicate.add(11);
    firstNonDuplicate.add(12);
    assertEquals(-1, firstNonDuplicate.firstNonDuplicate());
  }


}
