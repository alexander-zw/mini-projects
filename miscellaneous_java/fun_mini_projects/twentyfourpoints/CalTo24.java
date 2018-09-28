package twentyfourpoints;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Sees if four numbers can be combined with +,-,*,/ to become NUMBER and lists the ways
 * 
 * @author AlexanderWu
 */
public class CalTo24 {
	
	// the number we need to calculate to
	private static int NUMBER = 24;

	private static String calculation;
	private static Set<String> calculations = new HashSet<String>();

	// performs an operation (+, -, *, /) on the two numbers according to int i while updating
	// calculation
	private static int randOp(int first, int second, int i) throws Exception {
		switch (i) {
		case 0:
			calculation += " + " + second;
			return first + second;
		case 1:
			calculation += " - " + second;
			return first - second;
		case 2:
			calculation += " * " + second;
			return first * second;
		default:
			if (first % second == 0) {
				calculation += " / " + second;
				return first / second;
			}
			throw new Exception("Cannot divide evenly");
		}
	}

	// cut out the last operation on String calculation
	private static void cut(int num) {
		calculation = calculation.substring(0, calculation.length() - 3 - (num + "").length());

	}

	// whether op1 and op2 are both +,- or both *,/
	private static boolean sameOp(int op1, int op2) {
		// two numbers aren't too far apart and they are not 1 (-) and 2 (*)
		return Math.abs(op1 - op2) < 2 && op1 + op2 != 3;
	}

	// makes the set calculations contain the string calculations that would get NUMBER
	// the calculations are somewhat repetitive
	// grouped calculations are not formatted properly
	public static void find(int first, int second, int third, int fourth) {
		// answers to operations
		int[] ans = new int[3];

		// try performing operations in order in first iteration
		// try performing two groups of operations then combining the groups in second iteration
		for (int mode = 0; mode < 2; mode++) {

			for (int a = 0; a < 4; a++) { // switch the order of the four numbers
				for (int b = 0; b < 3; b++) {
					for (int c = 0; c < 2; c++) {

						for (int i = 0; i < 4; i++) { // four different operations for each of 3 groups
							calculation = first + ""; // reset calculation string
							try {
								ans[0] = randOp(first, second, i);
								for (int j = 0; j < 4; j++) {
									try {
										ans[1] = mode == 0 ? randOp(ans[0], third, j)
												: randOp(third, fourth, j);
										for (int k = 0; k < 4; k++) {
											try {
												ans[2] = mode == 0 ? randOp(ans[1], fourth, k)
														: randOp(ans[0], ans[1], k);

												if (ans[2] == NUMBER) {
													// in second iteration, discard repeating
													// calculations, only allow e.g. a + b * c - d
													// instead of e.g. a + b - c - d or a + b * c * d
													if (mode == 0)
														calculations.add(calculation);
													else if (sameOp(i, j) && !sameOp(i, k))
														calculations.add("g " + calculation);
												}

												cut(fourth);
											} catch (Exception e) {
											}
										}
										cut(third);
									} catch (Exception e) {
									}
								}
								cut(second);
							} catch (Exception e) {
							}
						}

						// c: swap third and fourth
						third ^= fourth;
						fourth ^= third;
						third ^= fourth;
					}
					// b: change second
					switch (b) {
					case 0: // 1, 2, 3, 4
						// swap middle two
						second ^= third;
						third ^= second;
						second ^= third;
						break;
					case 1: // 1, 3, 2, 4
						// swap last two then middle two
						third ^= fourth;
						fourth ^= third;
						third ^= fourth;
						second ^= third;
						third ^= second;
						second ^= third;
						break;
					case 2: // 1, 4, 3, 2
						// swap second and last so that it goes back to the original
						second ^= fourth;
						fourth ^= second;
						second ^= fourth;
					}
				}
				// a
				switch (a) {
				case 0: // 1, 2, 3, 4
					// swap first two
					first ^= second;
					second ^= first;
					first ^= second;
					break;
				case 1: // 2, 1, 3, 4
					// swap middle two then first two
					second ^= third;
					third ^= second;
					second ^= third;
					first ^= second;
					second ^= first;
					first ^= second;
					break;
				case 2: // 3, 2, 1, 4
					// swap first and last then last two
					first ^= fourth;
					fourth ^= first;
					first ^= fourth;
					third ^= fourth;
					fourth ^= third;
					third ^= fourth;
					// 4, 2, 3, 1
				}
			}

		}

	}

	// asks for input (four numbers), finds calculation methods, then outputs them
	public static void main(String[] args) {
		try {

			// get input
			System.out.println("Please enter the four numbers:");
			Scanner scan = new Scanner(System.in);
			int first = scan.nextInt();
			int second = scan.nextInt();
			int third = scan.nextInt();
			int fourth = scan.nextInt();
			scan.close();

			// find and sort calculation methods
			find(first, second, third, fourth);
			// copy to array to sort
			String[] calArr = new String[calculations.size()];
			int i = 0;
			for (String cal : calculations) {
				calArr[i] = cal;
				i++;
			}
			Arrays.sort(calArr);

			// print
			for (String cal : calArr) {
				System.out.println(cal);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
