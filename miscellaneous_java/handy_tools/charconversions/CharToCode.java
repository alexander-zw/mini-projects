package charconversions;

import java.util.Scanner;

public class CharToCode {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Please input the character: ");
		try{
			String character = scan.nextLine();	//scan user input
			char firstChar = character.charAt(0);	//only use first character
			System.out.println("The unicode for " + firstChar + " is " + (int)firstChar);
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			scan.close();
		}

	}

}
