package hackerrank.trees;

import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/tree-height-of-a-binary-tree/problem">Matrix</a>
 */
public class TreeHeightOfABinaryTree {

  public static class Node {

    Node left;
    Node right;
    int data;

    Node(int data) {
      this.data = data;
      left = null;
      right = null;
    }
  }

  public static int height(Node root) {
    int leftHeight = 0;
    int rightHeight = 0;
    if (root.left != null) {
      leftHeight = 1 + height(root.left);
    }
    if (root.right != null) {
      rightHeight = 1 + height(root.right);
    }
    return Math.max(leftHeight, rightHeight);
  }

  public static Node insert(Node root, int data) {
    if (root == null) {
      return new Node(data);
    } else {
      Node cur;
      if (data <= root.data) {
        cur = insert(root.left, data);
        root.left = cur;
      } else {
        cur = insert(root.right, data);
        root.right = cur;
      }
      return root;
    }
  }

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int t = scan.nextInt();
    Node root = null;
    while (t-- > 0) {
      int data = scan.nextInt();
      root = insert(root, data);
    }
    scan.close();
    int height = height(root);
    System.out.println(height);
  }
}