package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class RestoreIPAddressesTest {

  @Test
  public void testRestoreIpAddresses1() {
    List<String> expected = Arrays.asList("255.255.11.135", "255.255.111.35");
    List<String> result = RestoreIPAddresses.restoreIpAddresses("25525511135");
    Collections.sort(expected);
    Collections.sort(result);
    assertEquals(expected, result);
  }

  @Test
  public void testRestoreIpAddresses2() {
    List<String> expected = Arrays.asList("0.0.0.0");
    List<String> result = RestoreIPAddresses.restoreIpAddresses("0000");
    assertEquals(expected, result);
  }

  @Test
  public void testRestoreIpAddresses3() {
    List<String> expected = Arrays.asList("1.0.10.23", "1.0.102.3", "10.1.0.23", "10.10.2.3",
        "101.0.2.3");
    List<String> result = RestoreIPAddresses.restoreIpAddresses("101023");
    Collections.sort(expected);
    Collections.sort(result);
    assertEquals(expected, result);
  }

  @Test
  public void testRestoreIpAddresses4() {
    List<String> expected = Arrays.asList("1.92.168.11", "19.2.168.11", "19.21.68.11",
        "19.216.8.11", "19.216.81.1", "192.1.68.11", "192.16.8.11", "192.16.81.1", "192.168.1.1");

    List<String> result = RestoreIPAddresses.restoreIpAddresses("19216811");
    Collections.sort(expected);
    Collections.sort(result);
    assertEquals(expected, result);
  }
}
