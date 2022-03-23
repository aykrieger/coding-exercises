package search;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @see <a href="https://www.hackerrank.com/challenges/ctci-ice-cream-parlor/problem">Ice Cream
 * Parlor</a>
 */
public class IceCreamParlor {

  /*
   * Complete the 'whatFlavors' function below.
   *
   * The function accepts following parameters:
   *  1. INTEGER_ARRAY cost
   *  2. INTEGER money
   */
  public static void whatFlavors(List<Integer> cost, int money) {
    HashMap<Integer, Integer> otherIdx = new HashMap<Integer, Integer>();

    for (int i = 0; i < cost.size(); i++) {
      int currCost = cost.get(i);
      int currIdx = i + 1;

      if (currCost >= money) {
        continue;
      }

      if (otherIdx.get(currCost) != null) {
        System.out.println(otherIdx.get(currCost) + " " + currIdx);
        break;
      } else {
        otherIdx.put(money - currCost, currIdx);
      }
    }

  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    int t = Integer.parseInt(bufferedReader.readLine().trim());

    IntStream.range(0, t).forEach(tItr -> {
      try {
        int money = Integer.parseInt(bufferedReader.readLine().trim());

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> cost = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        IceCreamParlor.whatFlavors(cost, money);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    bufferedReader.close();
  }
}
