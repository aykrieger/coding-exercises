package hackerrank.linkedlists;

/**
 * @see <a href="https://www.hackerrank.com/challenges/ctci-linked-list-cycle/problem">Linked Lists:
 * Detect a Cycle</a>
 */
public class LinkedListsDetectACycle {

  static class Node {

    public Node next;
  }

  boolean hasCycle(Node head) {
    int i = 101;
    while (i > 0) {
      if (head == null) {
        return false;
      }
      head = head.next;
      i--;
    }
    return true;
  }
}
