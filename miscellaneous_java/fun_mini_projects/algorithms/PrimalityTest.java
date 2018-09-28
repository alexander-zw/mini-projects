package algorithms;

import java.util.Date;
import java.util.Scanner;

import tools.ManipulateInt;

/*
 * Note: with the number 1111111111111111111, it was determined that to search
 * all values from 2 to √n takes 10,050-10,150ms; to search 2 and then all odd
 * values takes 5025-5130ms; to search 2, 3 and then skip all multiples of 2 and
 * 3 (5, 7, 11, 13, 17, 19, 23, 25, 29, 31...) takes 3525-3625ms (requires the
 * use of byte variable j)
 */
public class PrimalityTest extends ManipulateInt {

	// @Override
	protected static String manipulate(long x) {

		if (x <= 1) {
			return "one or zero or negative";
		}
		
		if (x == 2 || x == 3) {
			return "prime";
		}

		if (x % 2 == 0) { // first check 2
			return "composite with a factor of 2";
		}

		if (x % 3 == 0) { // check 3
			return "composite with a factor of 3";
		}

		long wall = (long) Math.sqrt(x);
		byte j = 4; // used to skip multiples of 3

		for (long i = 5; i <= wall; i += j) {
			
			if (x % i == 0) {
				return "composite with a factor of " + i;
			}
			j = (byte) (j ^ 0b110); // change from 2 to 4 and vice versa
			
		}

		return "prime";
	}

	// @Override
	protected static void processError(Exception e) {
		System.out.println("No, input an integer between " + Long.MIN_VALUE + " and " + Long.MAX_VALUE);
	}

	// this is not supposed to be here, but there's something wrong so I added
	// it
	public static void main() {

		Scanner scan = new Scanner(System.in);

		System.out.print(inputMessage); // tells the user to input an integer

		try {
			long x = scan.nextLong();
			Date time1 = new Date();
			output(manipulate(x)); // output the result
			Date time2 = new Date();
			System.out.println("Time taken: " + (time2.getTime() - time1.getTime()));

		} catch (Exception e) {
			processError(e); // does something about the exception
		} finally {
			scan.close();
		}

	}

	public static void main(String[] args) {
		
		main();
		
//		for(int i = 0; i < 10; i++){
//			Date time1 = new Date();
//			output(manipulate(1111111111111111111l)); // output the result
//			Date time2 = new Date();
//			System.out.println("Time taken: " + (time2.getTime() - time1.getTime()));
//		}
		
	}

}
