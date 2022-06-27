package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @see <a
 * href="https://leetcode.com/problems/longest-substring-without-repeating-characters/">Longest
 * Substring Without Repeating Characters</a>
 */
public class LongestSubstringWithoutRepeatingCharacters {

  public static int lengthOfLongestSubstring(String s) {
    int length = s.length();
    int leftIdx = 0;
    int rightIdx = 0;
    int result = 0;

    Set<Character> charSet = new HashSet<>();
    while (rightIdx < length) {
      System.out.println("charSet: " + charSet);
      if (charSet.contains(s.charAt(rightIdx))) {
        charSet.remove(s.charAt(leftIdx));
        leftIdx++;
      } else {
        charSet.add(s.charAt(rightIdx));
        rightIdx++;
        result = Math.max(result, charSet.size());
      }
    }
    return result;
  }
}
