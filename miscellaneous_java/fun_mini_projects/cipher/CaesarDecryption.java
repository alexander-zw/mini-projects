package cipher;

import java.util.Scanner;

/**
 * This program uses brute force decryption to decrypt Caesar ciphers. It tries
 * every possible key from 1 to 25 and displays the results. The user then
 * decides which key is correct.
 * 
 * @author AlexanderWu
 *
 */
public class CaesarDecryption {

	public static void main(String[] args) {
		System.out.println("Enter message:");
		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		bruteForceDecrypt(str);
		scan.close();
	}

	public static void bruteForceDecrypt(String str) {
		for (int i = 1; i < 26; i++) {
			System.out.println("Key: " + i);
			System.out.println(CaesarCipher.decrypt(str, i));
		}
	}

}

/*
 * Example on Khan Academy:
 * Gluhtlishjrvbadvyyplkaohavbyjpwolypzavvdlhrvuuleatlzzhnlzdpajoavcpnlulyljpwolyrlfdvykpzaolopkk
 * luzftivsvmklhaoputfmhcvypalovsilpuluk
 * Key 7 becomes:
 * ze name blackout worried that our cipher is too weak on next message switch to vigenere cipher
 * keyword is the hidden symbol of death in my favorite holbein end
 * 
 * Vwduwljudeehghyhubwklqjlfrxogilqgsohdvhuhwxuqdqbeoxhsulqwviruydxowdqgdodupghvljqedvhgrqzklfked
 * qnbrxghflghrqldpvhwwlqjxsvdihkrxvhfr
 * Key 3 becomes:
 * start i grabbed everything i could find please return any blueprints for vault and alarm
 * design based on which bank you decide on i am setting up safe house co
 */
