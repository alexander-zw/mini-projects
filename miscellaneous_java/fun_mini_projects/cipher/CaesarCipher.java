package cipher;

/* This code is open source and can be used and distributed by anyone. -Creator of program, RF
 * Apex Programming
 * Started: 10/30/16
 * Last update: 11/1/16
 * ENCRYPT/DECRYPT of shift/Caesar ciphers
 */
import java.util.Scanner;

public class CaesarCipher {
	// Open up with my main method
	public static void main(String[] args) {
		// Lets get a new scanner here
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String str;
		int key;

		System.out.println("Enter message:");
		// Next line input (For string input)
		str = sc.nextLine();
		System.out.println("Enter encryption key:");
		// The key is what value you will shift the cipher over
		key = sc.nextInt();

		// This for loop is repeated use of 'Encrypt' and 'Decrypt' options
		for (;;) {
			System.out.println("1.Encrypt\n2.Decrypt\n3.Exit...");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Encrypted message..." + encrypt(str, key));
				System.out.println("NOTE, THAT WIERD CHARACTER BETWEEN WORDS IS A SPACE");
				break;
			case 2:
				System.out.println("Decrypted message..." + decrypt(str, key));
				System.out.println("NOTE, THAT WIERD CHARACTER BETWEEN WORDS IS A SPACE");
				break;
			case 3:
				// exit from the program
				System.exit(0);
				break;
			default:
				System.out.println("Invalid option..");
			}
		}
	}

	// lets create a new method called encrypt
	// lets make it use String str and int keylength
	public static String encrypt(String str, int key) {// OPEN OF ENCRYPT
		StringBuilder encrypted = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {// OPEN OF FOR LOOP
												// int c= the position of i in
												// the string str
			int c = str.charAt(i);
			// encryption logic for lowercase letters
			if (Character.isLowerCase(c) || Character.isUpperCase(c)) {
				int X = Character.toLowerCase(c);
				int Mod = X - 97 + key;
				c = Mod % 26;
			}
			// encrypted=encrypted+(char)c;
			char m = (char) (c + 97);
			encrypted.append(m);
		} // CLOSE OF FOR LOOP
		return encrypted.toString();
	}// CLOSE OF ENCRYPT

	public static String decrypt(String str, int key) {
		StringBuilder decrypted = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			// int c = the position of i in the string str
			int c = str.charAt(i);
			// encryption logic for lowercase letters
			if (Character.isLowerCase(c) || Character.isUpperCase(c)) {
				int X = Character.toLowerCase(c);
				int Mod = X - 97 + 26 - key;
				c = Mod % 26;
			}

			// encrypted=encrypted+(char)c;
			char Z = (char) (c + 97);
			decrypted.append(Z);
		}
		return decrypted.toString();
	}// END DECRYPT
}// END CLASS
