package printers;

import java.util.Scanner;

/**
 * Can be used to print a long sequence of repeating characters, such as
 * {@code ",000,000,000,000,000,000"}. Can be used as a program, or a static
 * method.
 * 
 * @author Alexander Z. Wu
 *
 */
public class PrintLongSequence {

	// Cannot initiate this class
	private PrintLongSequence() {
	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.print("Please enter sequence to repeat: ");
		String base = scan.nextLine();
		System.out.print("How many times? ");
		int repeat = scan.nextInt();
		System.out.println(repeatingSequence(base, repeat));
		scan.close();

	}

	public static String repeatingSequence(String base, int repeat) {

		String result = "";
		for (int i = 0; i < repeat; i++) {
			result += base;
		}
		return result;

	}

}
