package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @see <a href="https://leetcode.com/problems/restore-ip-addresses/">Restore IP Addresses</a>
 */
public class RestoreIPAddresses {

  public static List<String> restoreIpAddresses(String input) {

    if (input.length() < 4 || input.length() > 12) {
      return new ArrayList<>();
    }
    List<String> results = new ArrayList<>();
    List<String> octets = new ArrayList<>();
    validIPsHelper(results, octets, input, 0, 4);
    return results;
  }

  private static void validIPsHelper(List<String> results, List<String> octets, String input,
      int index,
      int octetsToAdd) {
    if (octetsToAdd < 1) {
      if (index == input.length()) {
        results.add(String.join(".", octets));
      }
      return;
    }

    int curr = index;
    int maxIndex = curr + 3;
    while (curr < input.length() && curr < maxIndex) {
      String subString = input.substring(index, curr + 1);
      int num = Integer.parseInt(subString);
      if (subString.equals(Integer.toString(num)) && isValidOctet(num)) {
        octets.add(subString);
        validIPsHelper(results, octets, input, curr + 1, octetsToAdd - 1);
        octets.remove(octets.size() - 1);
      } else {
        break;
      }
      curr++;
    }
  }

  private static boolean isValidOctet(int num) {
    return num >= 0 && num <= 255;
  }
}
