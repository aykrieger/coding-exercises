package hackerrank.linkedlists;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/insert-a-node-at-a-specific-position-in-a-linked-list/problem">Insert
 * a node at a specific position in a linked list</a>
 */
public class InsertANodeAtASpecificPositionInALinkedList {

  static class SinglyLinkedListNode {

    public int data;
    public SinglyLinkedListNode next;

    public SinglyLinkedListNode(int nodeData) {
      this.data = nodeData;
      this.next = null;
    }
  }

  static class SinglyLinkedList {

    public SinglyLinkedListNode head;
    public SinglyLinkedListNode tail;

    public SinglyLinkedList() {
      this.head = null;
      this.tail = null;
    }

    public void insertNode(int nodeData) {
      SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

      if (this.head == null) {
        this.head = node;
      } else {
        this.tail.next = node;
      }

      this.tail = node;
    }
  }

  public static void printSinglyLinkedList(SinglyLinkedListNode node, String sep,
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
   * Complete the 'insertNodeAtPosition' function below.
   *
   * The function is expected to return an INTEGER_SINGLY_LINKED_LIST.
   * The function accepts following parameters:
   *  1. INTEGER_SINGLY_LINKED_LIST llist
   *  2. INTEGER data
   *  3. INTEGER position
   */

  /*
   * For your reference:
   *
   * SinglyLinkedListNode {
   *     int data;
   *     SinglyLinkedListNode next;
   * }
   *
   */

  public static SinglyLinkedListNode insertNodeAtPosition(SinglyLinkedListNode llist, int data,
      int position) {

    SinglyLinkedListNode curr = llist;

    for (int i = 0; i < position - 1; i++) {
      curr = curr.next;
    }

    SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);

    newNode.next = curr.next;

    SinglyLinkedListNode head = null;
    if (position != 0) {
      curr.next = newNode;
      head = llist;
    } else {
      newNode.next = llist;
      head = newNode;
    }

    return head;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    SinglyLinkedList llist = new SinglyLinkedList();

    int llistCount = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int i = 0; i < llistCount; i++) {
      int llistItem = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      llist.insertNode(llistItem);
    }

    int data = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    int position = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    SinglyLinkedListNode llist_head = insertNodeAtPosition(llist.head, data, position);

    printSinglyLinkedList(llist_head, " ", bufferedWriter);
    bufferedWriter.newLine();

    bufferedWriter.close();

    scanner.close();
  }
}