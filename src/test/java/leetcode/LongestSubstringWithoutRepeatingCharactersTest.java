package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for {@link LongestSubstringWithoutRepeatingCharacters}
 */
public class LongestSubstringWithoutRepeatingCharactersTest {

  @Test
  public void testLengthOfLongestSubstringTest1() {
    assertEquals(3,
        LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring("abcabcbb"));
  }

  @Test
  public void testLengthOfLongestSubstringTest2() {
    assertEquals(1,
        LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring("bbbbb"));
  }

  @Test
  public void testLengthOfLongestSubstringTest3() {
    assertEquals(3,
        LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring("pwwkew"));
  }

}
