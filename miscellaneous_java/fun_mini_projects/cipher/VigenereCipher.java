package cipher;

import java.util.Scanner;

public class VigenereCipher {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter message:");
		String str = scan.nextLine();
		System.out.println("Enter keyword:");
		String keyword = scan.nextLine();
		System.out.println(decrypt(str, keyword));
//		char x = scan.next().charAt(0);
//		System.out.println(toInt(x));
		scan.close();
	}

	public static String decrypt(String str, String keyword) {
		StringBuilder decrypted = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			// int c = the position of i in the string str
			int c = str.charAt(i);
			// encryption logic for lowercase letters
			if (Character.isLowerCase(c) || Character.isUpperCase(c)) {
				int X = Character.toLowerCase(c);
				int Mod = X - 97 + 26 - toInt(keyword.charAt(i % keyword.length()));
				c = Mod % 26;
			}

			// encrypted=encrypted+(char)c;
			char Z = (char) (c + 97);
			decrypted.append(Z);
		}
		return decrypted.toString();
	}

	private static int toInt(char c) {
		return (int) c - 97;
	}

}

/*
 * Example on Khan Academy:
 * Klkbnqlcytfysryucocphgbdizzfcmjwkuchzyeswfogmmetwwossdchrzyldsbwnydednzwnefydthtddbojice
 * mlucdygicczhoadrzcylwadsxpilpiecskomoltejtkmqqymehpmmjxyolwpeewjckznpccpsvsxauyodhalmrioc
 * wpelwbcniyfxmwjcemcyrazdqlsomdbfljwnbijxpddsyoehxpceswtoxwbleecsaxcnuetzywfn
 * Key "sskkuullll" becomes:
 * start warning i heard report of our break in on the
 * news still waiting on alarm test schedules i will report back tomorrow with
 * final plan for extra security i suggest we burn our letters after reading and
 * switch our letters to numbers using polybius square drop message under the
 * bench at train station end
 */
