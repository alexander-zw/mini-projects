package numberconversions;

import java.util.Scanner;

public class TestNumberConversions {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.print("Please enter the number of zeros: ");
		int numOfZeros = scan.nextInt();
		String word = NumberConversions.thousandsExpressionOf(numOfZeros);
		if (word.equals("")) {
			word = "too many zeros";
		}
		System.out.println(word);

		scan.close();

	}

}
