package hackerrank.arrays;

import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/ctci-array-left-rotation/problem">Arrays:
 * Left Rotation</a>
 */
public class ArraysLeftRotation {

  public static int[] arrayLeftRotation(int[] a, int n, int k) {
    int sizeA = a.length;
    int result[] = new int[sizeA];
    int numMoves = k % sizeA;
    // Negative because we are moving left
    numMoves = -numMoves;

    for (int i = 0; i < sizeA; i++) {
      // Index of element in result array
      int newInd = Math.floorMod(numMoves + i, sizeA);
      result[newInd] = a[i];
    }
    return result;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int k = in.nextInt();
    int a[] = new int[n];
    for (int a_i = 0; a_i < n; a_i++) {
      a[a_i] = in.nextInt();
    }

    int[] output = new int[n];
    output = arrayLeftRotation(a, n, k);
    for (int i = 0; i < n; i++) {
      System.out.print(output[i] + " ");
    }

    System.out.println();

  }
}
