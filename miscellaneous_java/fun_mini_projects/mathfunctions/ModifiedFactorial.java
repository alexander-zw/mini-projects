package mathfunctions;

import java.util.Scanner;

public class ModifiedFactorial {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter number: ");
		int num = scan.nextInt();
		System.out.println("Result: " + factUsingTwo(num));
		scan.close();

	}

	public static double factUsingTwo(int num){
		
		double product = 1;
		while(num > 1){
			product *= num;
			num -= 2;
		}
		
		return product;
	}
	
}
