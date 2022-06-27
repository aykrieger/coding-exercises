package aykrieger;

import java.util.LinkedHashSet;

public class FirstNonDuplicate {

  private LinkedHashSet<Integer> linkedSet = new LinkedHashSet<>();

  public void add(int number) {
    if (!linkedSet.contains(number)) {
      linkedSet.add(number);
    } else {
      linkedSet.remove(number);
    }
  }

  public int firstNonDuplicate() {
    return linkedSet.isEmpty() ? -1 : linkedSet.iterator().next();
  }
}
