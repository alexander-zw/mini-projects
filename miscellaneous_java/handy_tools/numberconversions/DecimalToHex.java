package numberconversions;

import java.util.Scanner;

import tools.ManipulateInt;

public class DecimalToHex extends ManipulateInt {
	
	protected static String inputMessage = "Please enter the positive decimal integer: ";
		
	protected static String manipulate(int x){
		String result = "";	//the final hexadecimal number
		int[] hexDigit = new int[100];	//array for digits of hexadecimal number
		int length = 0;	//length of hexadecimal number
		int remainder = 0;	//to subtract from the decimal number
		int hexDigitWithZeros = 0;	//intermediate value of digit
		
		for(int i = 0; Math.pow(16, i) <= x; i++){	//loop to find digits of hex number
			//finding the remainder
			hexDigitWithZeros = (x - remainder) % (int)Math.pow(16, i + 1);
			//digit value; can be over 10
			hexDigit[i] =  hexDigitWithZeros / (int)Math.pow(16, i);
			length = i + 1;	//to find length of hex number
			remainder = hexDigit[i] + remainder;	//refresh value of the subtracted number
		}
		
		for(int i = 1; i < length + 1; i++){	//to reverse order of digits
			result += getHexDigit(hexDigit[length - i]);	//add char digits one by one
		}
		
		return result;
	}
	
	//convert to a hexadecimal digit
	protected static char getHexDigit(int digit){
		
		switch(digit){	//convert of letter if the digit is greater than 9
		case 10:
			return 'A';
		case 11:
			return 'B';
		case 12:
			return 'C';
		case 13:
			return 'D';
		case 14:
			return 'E';
		case 15:
			return 'F';
		default:
			//convert int into string, then convert string into char
			return (digit + "").charAt(0);
		}
		
	}
	
	protected static void output(int x, String result){
		System.out.print(x + " in hexadecimal is " + result);
	}
	
	protected static void processError(Exception e){
		System.out.println("Error: input an integer smaller than " + Integer.MAX_VALUE);
	}

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print(inputMessage);	//tells the user to input an integer
		try {
			int x = scan.nextInt();
			output(x, manipulate(x));	//output the result
			
		} catch (Exception e) {
			processError(e);	//does something about the exception
		} finally{
			scan.close();
		}
		
	}

}
