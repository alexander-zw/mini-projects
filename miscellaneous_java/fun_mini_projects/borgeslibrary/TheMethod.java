package borgeslibrary;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Uses The Method (artificial evolution) to find a sensible sentence in Borges's Library.
 * 
 * @author AlexanderWu
 */
public class TheMethod {
	
	private static Scanner scan = new Scanner(System.in);

	private static String parent = StringMethods.randLine(20);
	
	private static String[] offspring;

	public static void main(String[] args) {
		try {
			while (true) {
				breedGeneration();
			}
		} catch (InputMismatchException e) {
			scan.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void breedGeneration() {
		offspring = StringMethods.offspringOf(parent);
		displayOffspring();
		parent = getChoice();
	}
	
	private static String getChoice() {
		return offspring[scan.nextInt()];
	}

	private static void displayOffspring() {
		for (int i = 0; i < offspring.length; i++) {
			System.out.print(formatLabel(i) + offspring[i] + "       ");
			i++;
			System.out.println(formatLabel(i) + offspring[i]);
		}
	}
	
	private static String formatLabel(int label) {
		return (label > 9? "" : "0") + label + ": ";
	}
	
}
