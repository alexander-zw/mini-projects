package ordinary;

import java.util.Scanner;

import tools.ManipulateInt;

public class EvenOrOdd extends ManipulateInt {

	protected static String inputMessage = "Please input an integer to find whether it's even or odd: ";

	// @Override
	protected static String manipulate(int x) {
		return (x % 2 == 0) ? "even" : "odd";
	}

	// @Override
	protected static void processError(Exception e) {
		System.out.println("No, input an integer between " + Integer.MIN_VALUE + " and " + Integer.MAX_VALUE);
	}

	// this is not supposed to be here, but there's something wrong so I added it
	public static void main() {

		Scanner scan = new Scanner(System.in);

		System.out.print(inputMessage); // tells the user to input an integer

		try {
			int x = scan.nextInt();
			output(manipulate(x)); // output the result

		} catch (Exception e) {
			processError(e); // does something about the exception
		} finally {
			scan.close();
		}

	}

	public static void main(String[] args) {
		main();
	}

}
