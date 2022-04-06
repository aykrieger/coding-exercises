package hackerrank.recursionandbacktracking;


import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

/**
 * This problem was not a good practice problem for interview preparation (too tedious to implement,
 * no transferable algorithms/skills), so I provided a solution from the problem's discussion
 * section.
 *
 * @see <a href="https://www.hackerrank.com/challenges/crossword-puzzle/problem">Crossword
 * Puzzle</a>
 */
public class CrosswordPuzzle {

  public static void main(String[] args) {
    try (final Scanner in = new Scanner(System.in)) {
      final char[][] crossword = Stream.generate(in::nextLine).limit(10).map(String::toCharArray)
          .toArray(char[][]::new);
      final char[][] words = Arrays.stream(in.nextLine().split(";")).map(String::toCharArray)
          .toArray(char[][]::new);
      final char[][] result = solve(crossword, words, 0, new AtomicBoolean());
      Arrays.stream(result).map(String::new).forEach(System.out::println);
    }
  }

  static char[][] solve(final char[][] cross, final char[][] words, final int w,
      final AtomicBoolean found) {
    if (w == words.length) {
      found.set(true);
      return cross;
    }

    final int wordLen = words[w].length;
    for (int y = 0; y < 10; y++) {
      for (int x = 0; x < 10; x++) {
        int rowC = 0, colC = 0;
        for (int i = x;
            i < 10 && rowC < wordLen && (words[w][rowC] == cross[y][i] || cross[y][i] == '-');
            i++, rowC++) {
        }
        for (int i = y;
            i < 10 && colC < wordLen && (words[w][colC] == cross[i][x] || cross[i][x] == '-');
            i++, colC++) {
        }

        if (rowC == wordLen) {
          final char[][] cw = Arrays.stream(cross).map(char[]::clone).toArray(char[][]::new);
          System.arraycopy(words[w], 0, cw[y], x, wordLen);
          final char cn[][] = CrosswordPuzzle.solve(cw, words, w + 1, found);
          if (found.get()) {
            return cn;
          }
        }

        if (colC == wordLen) {
          final char[][] cw = Arrays.stream(cross).map(char[]::clone).toArray(char[][]::new);
          for (int i = 0; i < wordLen; cw[y + i][x] = words[w][i], i++) {
          }
          final char cn[][] = CrosswordPuzzle.solve(cw, words, w + 1, found);
          if (found.get()) {
            return cn;
          }
        }
      }
    }
    return cross;
  }
}
