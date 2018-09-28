package charconversions;

import java.util.Scanner;

public class CodeToChar {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Please input the first unicode (0 to 65535): ");
		try{
			int code = scan.nextInt();	//read user input
			for(int i = 0; i < 10; i++){	//output ten consecutive characters starting at the input
			System.out.println("The character for " + (code + i) + " is " + (char)(code + i));
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			scan.close();
		}

	}

}
