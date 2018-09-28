package tools;

import java.util.Scanner;

/**
 * Can be inherited if some program asks the user for an integer, calculates
 * some result with it, and displays the result. To use, override the method
 * {@code String manipulate(int x)} so that it returns the result of the
 * calculation from the input, {@code x}
 * <p>
 * Also checks for input and other exceptions.
 * <p>
 * Displays messages before input. User can choose methods of output and
 * exception handling by overriding the corresponding methods.
 * 
 * @author Alexander Wu
 *
 */
public abstract class ManipulateInt {

	/**
	 * the message displayed before input
	 */
	protected static String inputMessage = "Please input an integer: ";

	/**
	 * The processing of the integer; to be overridden.
	 * <p>
	 * The return value must be converted to a string.
	 * <p>
	 * Default: return {@code x} as a string
	 * 
	 * @param x
	 *            the input integer
	 * 
	 * @return the result of the computation as a string
	 */
	protected static String manipulate(int x) {
		return x + "";
	}

	/**
	 * Outputs result; can override.
	 * <p>
	 * Default: print {@code "The integer is " + result}
	 * 
	 * @param x
	 *            the original input integer
	 * 
	 * @param result
	 *            the computed result
	 */
	protected static void output(String result) {
		System.out.println("The integer is " + result);
	}

	/**
	 * What to do when an exception occurs. Can override.
	 * <p>
	 * Default: print {@code "Error:" + e.getMessage()}
	 * 
	 * @param e
	 *            the exception that occurred, to be sent by the main method
	 */
	protected static void processError(Exception e) {
		System.out.println("Error: " + e.getMessage());
	}

	/**
	 * Asks for an integer, manipulates it according to
	 * {@code int manipulate(int x)} and prints the result.
	 * <p>
	 * Should place into main method for effect.
	 * 
	 * @param args
	 */
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

}
