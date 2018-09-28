package cipher;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OtherCipher {

	public static void main(String args[]) {

		try {
			Scanner scan = new Scanner(System.in);
			String str = scan.nextLine();
			List<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < str.length(); i++) {
				list.add(Integer.parseInt("" + str.charAt(i)));
			}
			boolean[] pad = new boolean[200];
			System.out.println(decrypt(list, pad));
			scan.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String decrypt(List<Integer> list, boolean[] pad) {

		int[] intPad = intPad(pad);
		String decrypted = "";

		for (int i = 0; i < list.size(); i++) {
			list.set(i, list.get(i).intValue() ^ intPad[i]);
			decrypted += list.get(i);
		}

		return decrypted;
	}

	private static int[] intPad(boolean[] pad) {

		int[] intPad = new int[pad.length / 2];

		for (int i = 0; i < pad.length; i += 2) {
			intPad[i / 2] = (pad[i] ? 1 : 0) * 2 + (pad[i + 1] ? 1 : 0);
		}

		return intPad;
	}

}
