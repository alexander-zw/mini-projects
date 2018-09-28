package borgeslibrary;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class StringMethods {

	private static Random rand = new Random();

	// cannot be initialized
	private StringMethods() {
	}

	/**
	 * @return A random character, 'a'-'z' or ' ' or ',' or '.' with equal probability
	 */
	public static char randLetter() {
		int randInt = (rand.nextInt(29) + 97);
		switch (randInt) {
		case 123:
			return ' ';
		case 124:
			return ',';
		case 125:
			return '.';
		default:
			return (char) randInt;
		}
	}
	
	/**
	 * @param length the length of the string produced
	 * @return a random string of letters 'a'-'z' or ' ' or ',' or '.' with equal probability
	 */
	public static String randLine(int length) {
		String sum = "";
		for (int i = 0; i < length; i++) {
			sum += randLetter();
		}
		return sum;
	}
	
	public static String[] offspringOf(String parent) {
		Set<String> offspring = new HashSet<String>();
		while (offspring.size() < 20) {
			offspring.add(variationOf(parent));
		}
		Object[] objArr = offspring.toArray();
		String[] strArr = new String[objArr.length];
		for (int i = 0; i < objArr.length; i++)
			strArr[i] = (String) objArr[i];
		return strArr;
	}
	
	public static String variationOf(String original) {
		String newString = "";
		for (int i = 0; i < original.length(); i++) {
			newString += variationOf(original.charAt(i));
		}
		return newString;
	}
	
	private static char variationOf(char original) {
		if (rand.nextDouble() < .1) { // about 5% probability the character will change
			return randLetter();
		}
		return original;
	}

}
