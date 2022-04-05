package hackerrank.linkedlists;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/reverse-a-doubly-linked-list/problem">Reverse
 * a doubly linked list</a>
 */
public class ReverseADoublyLinkedList {

  static class DoublyLinkedListNode {

    public int data;
    public DoublyLinkedListNode next;
    public DoublyLinkedListNode prev;

    public DoublyLinkedListNode(int nodeData) {
      this.data = nodeData;
      this.next = null;
      this.prev = null;
    }
  }

  static class DoublyLinkedList {

    public DoublyLinkedListNode head;
    public DoublyLinkedListNode tail;

    public DoublyLinkedList() {
      this.head = null;
      this.tail = null;
    }

    public void insertNode(int nodeData) {
      DoublyLinkedListNode node = new DoublyLinkedListNode(nodeData);

      if (this.head == null) {
        this.head = node;
      } else {
        this.tail.next = node;
        node.prev = this.tail;
      }

      this.tail = node;
    }
  }

  public static void printDoublyLinkedList(DoublyLinkedListNode node, String sep,
      BufferedWriter bufferedWriter) throws IOException {
    while (node != null) {
      bufferedWriter.write(String.valueOf(node.data));

      node = node.next;

      if (node != null) {
        bufferedWriter.write(sep);
      }
    }
  }

  /*
   * Complete the 'reverse' function below.
   *
   * The function is expected to return an INTEGER_DOUBLY_LINKED_LIST.
   * The function accepts INTEGER_DOUBLY_LINKED_LIST llist as parameter.
   */

  /*
   * For your reference:
   *
   * DoublyLinkedListNode {
   *     int data;
   *     DoublyLinkedListNode next;
   *     DoublyLinkedListNode prev;
   * }
   *
   */

  public static DoublyLinkedListNode reverse(DoublyLinkedListNode llist) {
    DoublyLinkedListNode curr = llist;

    if (llist == null || llist.next == null) {
      return llist;
    }

    while (curr.next != null) {
      DoublyLinkedListNode temp = curr.next;

      curr.next = curr.prev;
      curr.prev = temp;

      curr = temp;
    }

    curr.next = curr.prev;
    curr.prev = null;

    return curr;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    int t = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int tItr = 0; tItr < t; tItr++) {
      DoublyLinkedList llist = new DoublyLinkedList();

      int llistCount = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int i = 0; i < llistCount; i++) {
        int llistItem = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        llist.insertNode(llistItem);
      }

      DoublyLinkedListNode llist1 = reverse(llist.head);

      printDoublyLinkedList(llist1, " ", bufferedWriter);
      bufferedWriter.newLine();
    }

    bufferedWriter.close();

    scanner.close();
  }
}
