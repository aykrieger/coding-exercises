package stringmanipulation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/special-palindrome-again">Special String Again</a>
 */
public class SpecialStringAgain {

	// Complete the substrCount function below.
	static long substrCount(int n, String s) {
		char[] arr = s.toCharArray();
		long result = arr.length;

		for (int i = 0; i < arr.length; i++) {
			char startChar = arr[i];
			int diffCharIndex = -1;

			for (int j = i + 1; j < arr.length; j++) {
				char currChar = arr[j];

				if (startChar == currChar) {
					if ((diffCharIndex == -1) ||
							(j - diffCharIndex) == (diffCharIndex - i)) {
						result++;
					}
				} else {
					if (diffCharIndex == -1) {
						diffCharIndex = j;
					} else {
						break;
					}
				}
			}
		}

		return result;
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int n = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		String s = scanner.nextLine();

		long result = substrCount(n, s);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedWriter.close();

		scanner.close();
	}
}
