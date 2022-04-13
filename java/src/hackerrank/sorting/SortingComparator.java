package hackerrank.sorting;

import java.util.*;

/**
 * @see <a href="https://www.hackerrank.com/challenges/ctci-comparator-sorting/problem">Sorting:
 * Comparator</a>
 */
public class SortingComparator {

  static class Player {

    String name;
    int score;

    Player(String name, int score) {
      this.name = name;
      this.score = score;
    }
  }

  static class Checker implements Comparator<Player> {

    // complete this method
    public int compare(Player a, Player b) {
      if (a.score < b.score) {
        return 1;
      } else if (a.score > b.score) {
        return -1;
      } else {
        if (a.name.compareTo(b.name) < 0) {
          return -1;
        } else if (a.name.compareTo(b.name) > 0) {
          return 1;
        } else {
          return 0;
        }
      }
    }
  }

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int n = scan.nextInt();

    Player[] player = new Player[n];
    Checker checker = new Checker();

    for (int i = 0; i < n; i++) {
      player[i] = new Player(scan.next(), scan.nextInt());
    }
    scan.close();

    Arrays.sort(player, checker);
    for (int i = 0; i < player.length; i++) {
      System.out.printf("%s %s\n", player[i].name, player[i].score);
    }
  }
}