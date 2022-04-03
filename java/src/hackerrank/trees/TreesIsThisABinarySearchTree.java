package hackerrank.trees;

/**
 * This problem is currently broken on the HackerRank site.
 *
 * @see <a href="https://www.hackerrank.com/challenges/ctci-is-binary-search-tree/problem">Trees: Is
 * This a Binary Search Tree?</a>
 */
public class TreesIsThisABinarySearchTree {

  /* Hidden stub code will pass a root argument to the function below.
    Complete the function to solve the challenge.
    Hint: you may want to write one or more helper functions.

    The Node class is defined as follows:
    class Node {
      int data;
      Node left;
      Node right;
    }
  */

  boolean checkBST(Node root) {
    return checkBST(root, 1002, -1);
  }

  boolean checkBST(Node node, int minVal, int maxVal) {
    if (node == null) {
      return true;
    }
    return minVal < node.data && node.data < maxVal &&
        checkBST(node.left, minVal, node.data) &&
        checkBST(node.right, node.data, maxVal);
  }

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
}