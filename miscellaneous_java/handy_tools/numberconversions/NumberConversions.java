package numberconversions;

import java.util.Scanner;

/**
 * The {@code NumberConversions} class contains several static methods that
 * convert Arabic numerals into different types of number representations,
 * like English word form, Chinese, and Roman numeral.
 * <p>
 * It can also be run as a program, which converts Arabic numeral into English.
 * <p>
 * Here are some examples:
 * <blockquote>
 * 
 * <pre>
 * NumberConversions.englishOf("-050836011.40")
 *     returns "negative fifty million eight hundred thirty-six thousand eleven point four zero"
 * NumberConversions.romanOf("2974") returns "MMCMLXXIV"
 * </pre>
 * 
 * </blockquote>
 * 
 * @author Alexander Z. Wu
 */
public class NumberConversions {

	// cannot initiate this class
	private NumberConversions() {

	}

	/**
	 * Returns the character digit {@code digit} converted into
	 * an English word; includes {@code "zero"} if {@code allowZero} = true.
	 * <p>
	 * If
	 * {@code digit} is not a character digit, returns {@code ""}.
	 * <p>
	 * Examples:
	 * <blockquote>
	 * 
	 * <pre>
	 * expressionOf('3', true) returns "three"
	 * expressionOf('0', true) returns "zero"
	 * expressionOf('0', false) returns ""
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param digit
	 *            the digit character to convert
	 * 
	 * @param allowZero
	 *            whether {@code "zero"} is allowed
	 * 
	 * @return the converted English word
	 */
	public static String expressionOf(char digit, boolean allowZero) {
		switch (digit) {
		case '1':
			return "one";
		case '2':
			return "two";
		case '3':
			return "three";
		case '4':
			return "four";
		case '5':
			return "five";
		case '6':
			return "six";
		case '7':
			return "seven";
		case '8':
			return "eight";
		case '9':
			return "nine";
		case '0':
			if (allowZero) {
				return "zero";
			}
		default:
			return ""; // if none apply
		}

	}

	/**
	 * Returns character digit {@code digit} as an English tens-word with
	 * hyphen; except for {@code '1'}: returns {@code "ten"}.
	 * <p>
	 * If input is not a digit character or is {@code '0'}, returns {@code ""}.
	 * <p>
	 * Examples:
	 * <blockquote>
	 * 
	 * <pre>
	 * tensExpressionOf('3') returns "thirty-"
	 * tensExpressionOf('1') returns "ten"
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param digit
	 *            the digit character to convert
	 * 
	 * @return the converted English word
	 */
	public static String tensExpressionOf(char digit) {
		switch (digit) {
		case '1':
			return "ten";
		case '2':
			return "twenty-";
		case '3':
			return "thity-";
		case '4':
			return "fourty-";
		case '5':
			return "fifty-";
		case '6':
			return "sixty-";
		case '7':
			return "seventy-";
		case '8':
			return "eighty-";
		case '9':
			return "ninety-";
		default:
			return ""; // if none apply
		}

	}

	/**
	 * Returns the English thousand-word suffix that refers to "1" with
	 * {@code numberOfZeros} zeros after it.
	 * <p>
	 * If {@code numberOfZeros} does not satisfy 3 <= {@code numberOfZeros}
	 * <= 305, returns {@code ""}.
	 * <p>
	 * Examples:
	 * <blockquote>
	 * 
	 * <pre>
	 * thousandsExpressionOf(6) returns "million"
	 * thousandsExpressionOf(7) returns "million"
	 * thousandsExpressionOf(100) returns "duotrigintillion"
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param numberOfZeros
	 *            the number of zeros after the "1" in a number
	 * 
	 * @return the thousands-word suffix
	 */
	public static String thousandsExpressionOf(int numberOfZeros) {
		// this number is useful in determining the thousand-word
		int numberOfThousands = numberOfZeros / 3 - 1;

		switch (numberOfThousands) {
		case 0:
			return "thousand";
		case 1:
			return "million";
		case 2:
			return "billion";
		case 3:
			return "trillion";
		case 4:
			return "quadrillion";
		case 5:
			return "quintillion";
		case 6:
			return "sextillion";
		case 7:
			return "septillion";
		case 8:
			return "octillion";
		case 9:
			return "nonillion";
			
		case 10:
			return "decillion";
		case 20:
			return "vigintillion";
		case 30:
			return "trigintillion";
		case 40:
			return "qradragintillion";
		case 50:
			return "quinquagintillion";
		case 60:
			return "sexagintillion";
		case 70:
			return "septagintillion";
		case 80:
			return "octogintillion";
		case 90:
			return "nonagintillion";
		case 100:
			return "centillion";

		default: // if the number of zeros is not a tens or less than ten
			if (numberOfThousands > 10) { // in case numberOfThousands is -1
				// the first part is the ones place, the second the tens place
				// the second part needs to be converted back to numberOfZeros instead of
				// numberOfThousands
				return latinRootOf(numberOfThousands % 10)
						+ thousandsExpressionOf(numberOfThousands / 10 * 30 + 3);
			}

			return ""; // if none apply; the ones digit (numberOfThousands = -1) is here
		}

	}

	// gets Latin root from number; used in thousandsExpressionOf method
	private static String latinRootOf(int number) {
		switch (number) {
		case 1:
			return "un";
		case 2:
			return "duo";
		case 3:
			return "tre";
		case 4:
			return "quattuor";
		case 5:
			return "quin";
		case 6:
			return "sex";
		case 7:
			return "septen";
		case 8:
			return "octo";
		case 9:
			return "novem";
		default:
			return ""; // if none apply
		}

	}

	// converts "ten x" into "xteen" (or "eleven" or "twelve")
	private static String correctTeens(String expression) {

		expression = expression.replace("ten one", "eleven");
		expression = expression.replace("ten two", "twelve");
		expression = expression.replace("ten three", "thirteen");
		expression = expression.replace("ten four", "fourteen");
		expression = expression.replace("ten five", "fifteen");
		expression = expression.replace("ten six", "sixteen");
		expression = expression.replace("ten seven", "seventeen");
		expression = expression.replace("ten eight", "eighteen");
		expression = expression.replace("ten nine", "nineteen");

		return expression;
	}

	/**
	 * Returns the integer Arabic numeral {@code numeral} in English word form.
	 * <p>
	 * {@code numeral} can be positive, negative, zero, or negative zero. The
	 * absolute value of {@code numeral} must be less than one thousand
	 * centillion. Ignores any leading zeros unless it is the last digit. If
	 * {@code numeral} is {@code ""}, returns {@code ""}. If {@code numeral} is
	 * {@code "-"}, returns {@code "negative"}.
	 * <p>
	 * Examples: <blockquote>
	 * 
	 * <pre>
	 * integerEnglishOf("40562011") returns "forty million five hundred two thousand eleven"
	 * integerEnglishOf("-000") returns "negative zero"
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param numeral
	 *            the Arabic numeral to convert
	 * 
	 * @return the converted English-form number
	 * 
	 * @throws IllegalArgumentException("Input not an Arabic numeral")
	 * 
	 * @throws IllegalArgumentException("That is probably a googolplex")
	 */
	public static String integerEnglishOf(String numeral) throws IllegalArgumentException {

		/*
		 * 1. initialize variables
		 * 2. check whether number is negative (remember it)
		 * 3. remove heading zeros
		 * 4. check whether input is empty (return "")
		 */

		char[] digit; // array for digits of number
		String[] expression; // array for words-form of digits of number
		String finalExpression = ""; // final answer

		int length; // number of digits of integer part of Arabic numeral
		boolean isNegative = false; // whether the number is negative

		if (numeral.startsWith("-")) { // if the number is negative
			// for use in finalExpression; cannot add to string since other checks are still necessary
			isNegative = true;
			numeral = numeral.substring(1); // remove negative sign
		}

		// remove heading zeros, except for last one
		while (numeral.startsWith("0") && numeral.length() > 1) {
			numeral = numeral.substring(1);
		}

		length = numeral.length(); // length is after "-" is removed
		if (length == 0) { // if there is no input (other than "-")
			if (isNegative) // if the number is negative (input was "-")
				return "negative";
			return ""; // otherwise (input was empty)
		}

		/*
		 * 5. put digits in string (String numeral) into character array (char[] digit)
		 * 6. check whether the input was an integer Arabic numeral (throw exception)
		 * 7. check whether the number is greater than or equal to one thousand centillion
		 * (throw exception)
		 */

		digit = new char[length]; // length of character digit array
		// puts digits in string (String numeral) into character array (char[] digit)
		// and searches for non-digits
		for (int i = 0; i < length; i++) {
			digit[i] = numeral.charAt(i); // store separate digits as character array

			if ((int) digit[i] < 48 || (int) digit[i] > 57) { // if this is not a digit
				throw new IllegalArgumentException("Input not an Arabic numeral"); // error
			}

		}

		if (length > 306) { // if the number is too long (one thousand centillion)
			// note: postponed to after non-digit check in case the input was not an Arabic numeral
			if (isNegative) {
				throw new IllegalArgumentException("That is probably negative googolplex");
			}
			throw new IllegalArgumentException("That is probably a googolplex");
		}

		/*
		 * 8. change character digits (char[] digit) into words, sorted into ones, tens,
		 * and hundreds; store into string array (String[] expression)
		 * 9. add thousands-word suffix (thousand, million) to string (expression[i])
		 */

		expression = new String[length]; // length of words string array
		// changes digits into strings (words)
		for (int i = length - 1; i > -1; i--) {

			switch ((length - i - 1) % 3) { // to sort between ones, tens, and hundreds
			case 0: // ones: change into words (one, two, three)
				expression[i] = expressionOf(digit[i], false);
				break;
			case 1: // tens: change into words (ten, twenty-, thirty-)
				expression[i] = tensExpressionOf(digit[i]);
				break;
			case 2: // hundreds: change into words and add suffix (one hundred, two hundred)
				// if the hundreds place is not empty
				if (!(digit[i] == (char) 0) && !(digit[i] == '0')) {
					expression[i] = expressionOf(digit[i], false) + " hundred";
				}
			}

			if ((length - i - 1) % 3 == 0 && ( // add suffix: thousand, million, etc
					// if this is the first digit and is not zero
					(i == 0 && digit[i] != '0') ||
					// or if this is the second digit and neither of the next two digits is zero
					(i == 1 && (digit[i] != '0' || digit[i - 1] != '0')) ||
					// or if this is over the second digit and none of the next three digits is zero
					(i > 1 && (digit[i] != '0' || digit[i - 1] != '0' || digit[i - 2] != '0')))) {

				expression[i] += " " + thousandsExpressionOf(length - i - 1);

			}

		}

		/*
		 * 10. add "negative" word (String finalExpression)
		 * 11. put strings (String[] expression) together (String finalExpression)
		 * 12. remove unnecessary "-"s (String finalExpression) (as in "twenty-")
		 * 13. trim leading, trailing, double, and triple spaces (String finalExpression)
		 * 14. check whether the number is zero (return "zero" or "negative zero")
		 * 15. change "ten x" into "xteen" (String finalExpression)
		 * 16. return value (String finalExpression)
		 */

		if (isNegative) { // if the number is negative
			finalExpression = "negative ";
		}

		// puts strings together
		if (length != 1 || digit[0] != '0') { // if the number is not zero
			for (String singleExpression : expression) {

				if (singleExpression == null) {
					continue; // skip null
				}
				if (!singleExpression.endsWith("-")) { // skip if there is a hyphen
					singleExpression += " "; // add space between words otherwise
				}

				// the final digits in word form
				finalExpression += singleExpression;
			}
		}

		// remove unnecessary hyphens
		finalExpression = finalExpression.replace("- ", " ");

		finalExpression = finalExpression.trim(); // trim leading and trailing spaces
		// eliminate double and triple spaces
		finalExpression = finalExpression.replace("  ", " ");
		finalExpression = finalExpression.replace("  ", " ");

		// if the number is zero
		if (finalExpression.isEmpty()) {
			return "zero";
		}

		// if the number is negative zero
		if (finalExpression.equals("negative")) {
			return "negative zero";
		}

		// change "ten x" into "xteen"
		finalExpression = correctTeens(finalExpression);

		return finalExpression;
	}

	/**
	 * Returns the an Arabic numeral {@code numeral} supposedly behind the
	 * decimal point in English word form.
	 * <p>
	 * If {@code numeral} is {@code ""}, returns {@code ""}.
	 * <p>
	 * Example:
	 * <blockquote>
	 * 
	 * <pre>
	 * fractionalEnglishOf("0528") returns "zero five two eight"
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param numeral
	 *            the Arabic numeral to convert
	 * 
	 * @return the converted English word form
	 * 
	 * @throws IllegalArgumentException("Input not an Arabic numeral")
	 */
	public static String fractionalEnglishOf(String numeral) throws IllegalArgumentException {

		/*
		 * 1. initialize variables
		 * 2. check whether input is empty (return "")
		 */

		char[] digit; // array for fractional digits of number
		String[] expression; // array for words-form of fractional digits
		String finalExpression = ""; // fractional part of final answer
		int length = numeral.length(); // number of digits of Arabic numeral

		if (length == 0) { // if there is no input
			return "";
		}

		/*
		 * 3. put digits in string (String numeral) into character array (char[] digit)
		 * 4. check whether the input was an integer Arabic numeral (throw exception)
		 * 5. change digits in character array (char[] digit) into words, stored in string array
		 * (String[] expression)
		 */

		digit = new char[length]; // length of character digit array
		// puts digits in string (String numeral) into character array (char[] digit)
		// and searches for non-digits
		for (int i = 0; i < length; i++) {
			digit[i] = numeral.charAt(i); // store separate digits as character array

			if ((int) digit[i] < 48 || (int) digit[i] > 57) { // if this is not a digit
				throw new IllegalArgumentException("Input not an Arabic numeral"); // error
			}

		}

		expression = new String[length]; // length of words string array
		for (int i = 0; i < length; i++) { // changes digits into strings (words)
			expression[i] = expressionOf(digit[i], true);
		}

		/*
		 * 6. put strings (String[] expression) together (String finalExpression)
		 * 7. return value (String finalExpression)
		 */

		for (String singleExpression : expression) {
			finalExpression += singleExpression + " "; // the final digits in word form
		}

		// remove last space
		finalExpression = finalExpression.substring(0, finalExpression.length() - 1);

		return finalExpression;
	}

	/**
	 * Returns the integer Arabic numeral {@code numeral} in English
	 * word form.
	 * <p>
	 * {@code numeral} can be positive, negative, zero, or negative zero.
	 * The absolute value of {@code numeral} must be less than one thousand
	 * centillion. Ignores any leading zeros unless it is the last digit.
	 * <p>
	 * If input ends with {@code "."}, outputs {@code "... point"}. If
	 * input begins with {@code "."}, outputs {@code "point ..."}. If input
	 * begins with {@code "-."}, outputs {@code "negative point ..."}.
	 * Doesn't accept {@code ""}, {@code "."}, {@code "-"}, or {@code "-."}
	 * alone. Doesn't accept any non-digits other than a leading {@code "-"}
	 * and one {@code "."} somewhere behind the {@code "-"}.
	 * <p>
	 * Examples:
	 * <blockquote>
	 * 
	 * <pre>
	 * englishOf("40562011") returns "forty million five hundred two thousand eleven"
	 * englishOf("-000.30") returns "negative zero point three zero"
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param numeral
	 *            the Arabic numeral to convert
	 * 
	 * @return the converted English-form number
	 * 
	 * @throws IllegalArgumentException("Input not an Arabic numeral")
	 * 
	 * @throws IllegalArgumentException("That is probably a googolplex")
	 * 
	 * @throws IllegalArgumentException("Input is empty")
	 */
	public static String englishOf(String numeral) throws IllegalArgumentException {

		/*
		 * 1. check whether input (String numeral) is empty (throw exception)
		 * 2. check whether input (String numeral) is "." (throw exception)
		 * 3. return converted number for "point ..."
		 * 4. check whether input (String numeral) is "-" or "-." (throw exception)
		 * 5. split input (String numeral) into integer and fractional parts
		 * (String[] separatedNumeral)
		 * 6. convert integer part
		 * 7. return converted number for "... point ..." and "... point"
		 */
		if (numeral.isEmpty()) { // if there is no input
			throw new IllegalArgumentException("Input is empty"); // error
		}

		if (numeral.startsWith(".")) { // if there is no integer part
			numeral = numeral.substring(1); // remove decimal point
			if (numeral.isEmpty()) // if the input is "."
				throw new IllegalArgumentException("Input not an Arabic numeral"); // error
			return "point " + fractionalEnglishOf(numeral); // return converted number
		}

		// in case the input equals one of these
		if (numeral.equals("-") || numeral.equals("-.")) {
			throw new IllegalArgumentException("Input not an Arabic numeral"); // error
		}

		// split integer part and fractional part
		String[] separatedNumeral = numeral.split("[.]", 2);

		// convert integer part
		separatedNumeral[0] = integerEnglishOf(separatedNumeral[0]);

		// if there is both an integer part and fractional part
		if (separatedNumeral.length == 2) {
			return separatedNumeral[0] + " point " + fractionalEnglishOf(separatedNumeral[1]);
		} // if there is no fractional part
		return separatedNumeral[0];

	}

	/**
	 * Returns the character digit {@code digit} converted into a Chinese
	 * word. Includes {@code "零"} (zero) if {@code allowZero} = {@code true}.
	 * Uses {@code "两"} instead of {@code "二"} if {@code allowLiang} = {@code true}.
	 * <p>
	 * If
	 * {@code digit} is not a character digit, returns {@code ""}.
	 * <p>
	 * Examples:
	 * <blockquote>
	 * 
	 * <pre>
	 * chineseExpressionOf('3', true, false) returns "三"
	 * chineseExpressionOf('0', true, false) returns "零"
	 * chineseExpressionOf('0', false, false) returns ""
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param digit
	 *            the digit character to convert
	 * 
	 * @param allowZero
	 *            whether {@code "零"} is allowed
	 * 
	 * @param allowLiang
	 *            whether {@code "两"} is allowed instead of {@code "二"}
	 * 
	 * @return the converted Chinese word
	 */

	public static String chineseExpressionOf(char digit, boolean allowZero, boolean allowLiang) {
		switch (digit) {
		case '1':
			return "一";
		case '2':
			if (allowLiang)
				return "两";
			return "二";
		case '3':
			return "三";
		case '4':
			return "四";
		case '5':
			return "五";
		case '6':
			return "六";
		case '7':
			return "七";
		case '8':
			return "八";
		case '9':
			return "九";
		case '0':
			if (allowZero) {
				return "零";
			}
		default:
			return ""; // if none apply
		}

	}

	// corrects some errors that may occur in the integerChineseOf method
	private static String correctChineseExpression(String expression) {

		expression = expression.replace("一十", "十");

		// correct "两"
		expression = expression.replace("十两", "十二");
		expression = expression.replace("百两", "百零二");
		expression = expression.replace("千两", "千零二");
		expression = expression.replace("千零二百", "千两百");

		// correct zeros
		expression = expression.replace("百一", "百零一");
		expression = expression.replace("百三", "百零三");
		expression = expression.replace("百零三十", "百三十");
		expression = expression.replace("百四", "百零四");
		expression = expression.replace("百零四十", "百四十");
		expression = expression.replace("百五", "百零五");
		expression = expression.replace("百零五十", "百五十");
		expression = expression.replace("百六", "百零六");
		expression = expression.replace("百零六十", "百六十");
		expression = expression.replace("百七", "百零七");
		expression = expression.replace("百零七十", "百七十");
		expression = expression.replace("百八", "百零八");
		expression = expression.replace("百零八十", "百八十");
		expression = expression.replace("百九", "百零九");
		expression = expression.replace("百零九十", "百九十");

		expression = expression.replace("千一", "千零一");
		expression = expression.replace("千十", "千零十");
		expression = expression.replace("千零一百", "千一百");
		expression = expression.replace("千三", "千零三");
		expression = expression.replace("千零三百", "千三百");
		expression = expression.replace("千四", "千零四");
		expression = expression.replace("千零四百", "千四百");
		expression = expression.replace("千五", "千零五");
		expression = expression.replace("千零五百", "千五百");
		expression = expression.replace("千六", "千零六");
		expression = expression.replace("千零六百", "千六百");
		expression = expression.replace("千七", "千零七");
		expression = expression.replace("千零七百", "千七百");
		expression = expression.replace("千八", "千零八");
		expression = expression.replace("千零八百", "千八百");
		expression = expression.replace("千九", "千零九");
		expression = expression.replace("千零九百", "千九百");

		return expression;
	}

	/**
	 * Returns the integer Arabic numeral {@code numeral} in Chinese
	 * word form.
	 * <p>
	 * {@code numeral} can be positive, negative, zero, or negative zero.
	 * The absolute value of {@code numeral} must be less than one trillion
	 * (一万亿). Ignores any leading zeros unless it is the last digit.
	 * If {@code numeral} is {@code ""}, returns {@code ""}. If {@code numeral}
	 * is {@code "-"}, returns {@code "负"}.
	 * <p>
	 * The rules of using {@code "两"} instead of {@code "二"} are as follows:
	 * {@code "两"} is used in any hundreds place or thousands place, and is
	 * used in before a {@code "万"} or {@code "亿"} only when it is the only
	 * character in that place. All other instances use {@code "二"}.
	 * <p>
	 * The method never uses {@code "一十"} and uses {@code "十"} instead.
	 * <p>
	 * Examples:
	 * <blockquote>
	 * 
	 * <pre>
	 * integerChineseOf("40562011") returns "四千零五十六万两千零十一"
	 * integerChineseOf("202020000") returns "两亿两百零二万"
	 * integerChineseOf("-000") returns "负零"
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param numeral
	 *            the Arabic numeral to convert
	 * 
	 * @return the converted Chinese-form number
	 * 
	 * @throws IllegalArgumentException("Input not an Arabic numeral")
	 * 
	 * @throws IllegalArgumentException("Input is too large")
	 */
	public static String integerChineseOf(String numeral) throws IllegalArgumentException {

		/*
		 * 1. initialize variables
		 * 2. check whether number is negative (remember it)
		 * 3. check whether input is empty (return "")
		 */

		char[] digit; // array for digits of number
		String[] expression; // array for words-form of digits of number
		String finalExpression = ""; // final answer

		int length; // number of digits of integer part of Arabic numeral
		boolean isNegative = false; // whether the number is negative

		if (numeral.startsWith("-")) { // if the number is negative
			isNegative = true; // for use in finalExpression
			numeral = numeral.substring(1); // remove negative sign
		}

		length = numeral.length(); // length is after "-" is removed
		if (length == 0) { // if there is no input (other than "-")
			if (isNegative) // if the number is negative
				return "负";
			return ""; // otherwise
		}

		/*
		 * 4. put digits in string (String numeral) into character array (char[] digit)
		 * 5. check whether the input was an integer Arabic numeral (throw exception)
		 * 6. check whether the number is greater than or equal to one trillion (throw exception)
		 */

		digit = new char[length]; // length of character digit array
		// puts digits in string (String numeral) into character array (char[] digit)
		// and searches for non-digits
		for (int i = 0; i < length; i++) {
			digit[i] = numeral.charAt(i); // store separate digits as character array

			if ((int) digit[i] < 48 || (int) digit[i] > 57) { // if this is not a digit
				throw new IllegalArgumentException("Input not an Arabic numeral"); // error
			}

		}

		if (length > 12) { // if the number is too long (one thousand centillion)
			// note: postponed to after non-digit check in case the input was not an Arabic numeral
			throw new IllegalArgumentException("Input is too large");
		}

		/*
		 * 7. change character digits (char[] digit) into words, sorted into ones, tens,
		 * hundreds, and thousands; store into string array (String[] expression)
		 * 8. add thousands-word suffix (万, 亿) to string (expression[i])
		 */

		expression = new String[length]; // length of words string array
		// changes digits into strings (words)
		for (int i = length - 1; i > -1; i--) {

			switch ((length - i - 1) % 4) { // to sort between ones, tens, hundreds, and thousands
			case 0: // ones: change into words (一， 二， 三)
				expression[i] = chineseExpressionOf(digit[i], false, i != length - 1);
				break;
			case 1: // tens: change into words and add suffix (十， 二十， 三十)
				// if the tens place is not empty
				if (!(digit[i] == (char) 0) && !(digit[i] == '0'))
					expression[i] = chineseExpressionOf(digit[i], false, false) + "十";
				break;
			case 2: // hundreds: change into words and add suffix (一百， 两百)
				// if the hundreds place is not empty
				if (!(digit[i] == (char) 0) && !(digit[i] == '0'))
					expression[i] = chineseExpressionOf(digit[i], false, true) + "百";
				break;
			case 3: // thousands: change into words and add suffix (一千， 两千)
				// if the thousands place is not empty
				if (!(digit[i] == (char) 0) && !(digit[i] == '0'))
					expression[i] = chineseExpressionOf(digit[i], false, true) + "千";
			}

			// add suffix: 万, 亿
			if (// if this is the first digit and is not zero
					(i == 0 && digit[i] != '0') ||
					// or if this is the second digit and neither of the next two digits is zero
					(i == 1 && (digit[i] != '0' || digit[i - 1] != '0')) ||
					// or if this is over the second digit
					// and none of the next three digits is zero
					(i > 1 && (digit[i] != '0' || digit[i - 1] != '0' || digit[i - 2] != '0'))) {

				if (i == length - 5)
					expression[i] += "万";
				else if (i == length - 9)
					expression[i] += "亿";

			}

		}

		/*
		 * 9. add "负" (negative) (String finalExpression)
		 * 10. put strings (String[] expression) together (String finalExpression)
		 * 11. check whether the number is zero (return "零" or "负零")
		 * 12. correct some errors in the final answer (String finalExpression)
		 * 13. return value (String finalExpression)
		 */

		if (isNegative) { // if the number is negative
			finalExpression = "负";
		}

		// puts strings together
		for (String singleExpression : expression) {

			if (singleExpression == null) {
				continue; // skip null
			}

			// the final digits in word form
			finalExpression += singleExpression;
		}

		// if the number is zero
		if (finalExpression.isEmpty()) {
			return "零";
		}

		// if the number is negative zero
		if (finalExpression.equals("负")) {
			return "负零";
		}

		// correct some errors
		finalExpression = correctChineseExpression(finalExpression);

		return finalExpression;
	}

	/**
	 * Returns the an Arabic numeral {@code numeral} supposedly behind the
	 * decimal point in Chinese word form.
	 * <p>
	 * If {@code numeral} is {@code ""}, returns {@code ""}.
	 * <p>
	 * Example:
	 * <blockquote>
	 * 
	 * <pre>
	 * fractionalChineseOf("0528") returns "零五二八"
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param numeral
	 *            the Arabic numeral to convert
	 * 
	 * @return the converted Chinese word form
	 * 
	 * @throws IllegalArgumentException("Input not an Arabic numeral")
	 */
	public static String fractionalChineseOf(String numeral) throws IllegalArgumentException {

		/*
		 * 1. initialize variables
		 * 2. check whether input is empty (return "")
		 */

		char[] digit; // array for fractional digits of number
		char[] expression; // array for words-form of fractional digits
		String finalExpression = ""; // fractional part of final answer
		int length = numeral.length(); // number of digits of Arabic numeral

		if (length == 0) { // if there is no input
			return "";
		}

		/*
		 * 3. put digits in string (String numeral) into character array (char[] digit)
		 * 4. check whether the input was an integer Arabic numeral (throw exception)
		 * 5. change digits in character array (char[] digit) into words, stored in character
		 * array (char[] expression)
		 */

		digit = new char[length]; // length of character digit array
		// puts digits in string (String numeral) into character array (char[] digit)
		// and searches for non-digits
		for (int i = 0; i < length; i++) {
			digit[i] = numeral.charAt(i); // store separate digits as character array

			if ((int) digit[i] < 48 || (int) digit[i] > 57) { // if this is not a digit
				throw new IllegalArgumentException("Input not an Arabic numeral"); // error
			}

		}

		expression = new char[length]; // length of words string array
		for (int i = 0; i < length; i++) { // changes digits into strings (words)
			expression[i] = chineseExpressionOf(digit[i], true, false).charAt(0);
		}

		/*
		 * 6. put strings (char[] expression) together (String finalExpression)
		 * 7. return value (String finalExpression)
		 */

		for (char singleExpression : expression) {
			finalExpression += singleExpression; // the final digits in word form
		}

		return finalExpression;
	}

	/**
	 * Returns the integer Arabic numeral {@code numeral} in Chinese
	 * word form.
	 * <p>
	 * {@code numeral} can be positive, negative, zero, or negative zero.
	 * The absolute value of {@code numeral} must be less than one trillion
	 * (一万亿). Ignores any leading zeros unless it is the last digit.
	 * <p>
	 * If input ends with {@code "."}, outputs {@code "... 点"}. If
	 * input begins with {@code "."}, outputs {@code "点 ..."}. If input
	 * begins with {@code "-."}, outputs {@code "负点 ..."}.
	 * Doesn't accept {@code ""}, {@code "."}, {@code "-"}, or {@code "-."}.
	 * alone. Doesn't accept any non-digits other than a leading {@code "-"}
	 * and one {@code "."} somewhere behind the {@code "-"}.
	 * <p>
	 * The rules of using {@code "两"} instead of {@code "二"} are as follows:
	 * {@code "两"} is used in any hundreds place or thousands place, and is
	 * used in before a {@code "万"} or {@code "亿"} only when it is the only
	 * character in that place. All other instances use {@code "二"}.
	 * <p>
	 * The method never uses {@code "一十"} and uses {@code "十"} instead.
	 * <p>
	 * Examples:
	 * <blockquote>
	 * 
	 * <pre>
	 * chineseOf("40562011") returns "四千零五十六万两千零十一"
	 * chineseOf("202020000.") returns "两亿两百零二万点"
	 * chineseOf("-000.30") returns "负零点三零"
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param numeral
	 *            the Arabic numeral to convert
	 * 
	 * @return the converted English-form number
	 * 
	 * @throws IllegalArgumentException("Input not an Arabic numeral")
	 * 
	 * @throws IllegalArgumentException("Input is too large")
	 * 
	 * @throws IllegalArgumentException("Input is empty")
	 */
	public static String chineseOf(String numeral) throws IllegalArgumentException {

		/*
		 * 1. check whether input (String numeral) is empty (throw exception)
		 * 2. check whether input (String numeral) is "." (throw exception)
		 * 3. return converted number for "point ..."
		 * 4. check whether input (String numeral) is "-" or "-." (throw exception)
		 * 5. split input (String numeral) into integer and fractional parts
		 * (String[] separatedNumeral)
		 * 6. convert integer part
		 * 7. return converted number for "... point ..." and "... point"
		 */
		if (numeral.isEmpty()) { // if there is no input
			throw new IllegalArgumentException("Input is empty"); // error
		}

		if (numeral.startsWith(".")) { // if there is no integer part
			numeral = numeral.substring(1); // remove decimal point
			if (numeral.isEmpty()) // if the input is "."
				throw new IllegalArgumentException("Input not an Arabic numeral"); // error
			return "点" + fractionalChineseOf(numeral); // return converted number
		}

		// in case the input equals one of these
		if (numeral.equals("-") || numeral.equals("-.")) {
			throw new IllegalArgumentException("Input not an Arabic numeral"); // error
		}

		// split integer part and fractional part
		String[] separatedNumeral = numeral.split("[.]", 2);

		// convert integer part
		separatedNumeral[0] = integerChineseOf(separatedNumeral[0]);

		// if there is both an integer part and fractional part
		if (separatedNumeral.length == 2) {
			return separatedNumeral[0] + "点" + fractionalChineseOf(separatedNumeral[1]);
		} // if there is no fractional part
		return separatedNumeral[0];

	}

	/**
	 * Returns the character digit {@code digit} converted into a traditional
	 * Chinese word (used on checks). Includes {@code "零"} (zero) if
	 * {@code allowZero} = {@code true}.
	 * <p>
	 * If
	 * {@code digit} is not a character digit, returns {@code ""}.
	 * <p>
	 * Examples:
	 * <blockquote>
	 * 
	 * <pre>
	 * traditionalChineseExpressionOf('3', true) returns "叁"
	 * traditionalChineseExpressionOf('0', true) returns "零"
	 * traditionalChineseExpressionOf('0', false) returns ""
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param digit
	 *            the digit character to convert
	 * 
	 * @param allowZero
	 *            whether {@code "零"} is allowed
	 * 
	 * @return the converted traditional Chinese word
	 */

	public static String traditionalChineseExpressionOf(char digit, boolean allowZero) {
		switch (digit) {
		case '1':
			return "壹";
		case '2':
			return "贰";
		case '3':
			return "叁";
		case '4':
			return "肆";
		case '5':
			return "伍";
		case '6':
			return "陆";
		case '7':
			return "柒";
		case '8':
			return "捌";
		case '9':
			return "玖";
		case '0':
			if (allowZero) {
				return "零";
			}
		default:
			return ""; // if none apply
		}

	}

	// corrects some errors that may occur in the integerTraditionalChineseOf method
	private static String correctTraditionalChineseExpression(String expression) {

		// correct zeros
		expression = expression.replace("佰壹", "佰零壹");
		expression = expression.replace("佰零壹拾", "佰壹拾");
		expression = expression.replace("佰贰", "佰零贰");
		expression = expression.replace("佰零贰拾", "佰贰拾");
		expression = expression.replace("佰叁", "佰零叁");
		expression = expression.replace("佰零叁拾", "佰叁拾");
		expression = expression.replace("佰肆", "佰零肆");
		expression = expression.replace("佰零肆拾", "佰肆拾");
		expression = expression.replace("佰伍", "佰零伍");
		expression = expression.replace("佰零伍拾", "佰伍拾");
		expression = expression.replace("佰陆", "佰零陆");
		expression = expression.replace("佰零陆拾", "佰陆拾");
		expression = expression.replace("佰柒", "佰零柒");
		expression = expression.replace("佰零柒拾", "佰柒拾");
		expression = expression.replace("佰捌", "佰零捌");
		expression = expression.replace("佰零捌拾", "佰捌拾");
		expression = expression.replace("佰玖", "佰零玖");
		expression = expression.replace("佰零玖拾", "佰玖拾");

		expression = expression.replace("仟壹", "仟零壹");
		expression = expression.replace("仟零壹佰", "仟壹佰");
		expression = expression.replace("仟贰", "仟零贰");
		expression = expression.replace("仟零贰佰", "仟贰佰");
		expression = expression.replace("仟叁", "仟零叁");
		expression = expression.replace("仟零叁佰", "仟叁佰");
		expression = expression.replace("仟肆", "仟零肆");
		expression = expression.replace("仟零肆佰", "仟肆佰");
		expression = expression.replace("仟伍", "仟零伍");
		expression = expression.replace("仟零伍佰", "仟伍佰");
		expression = expression.replace("仟陆", "仟零陆");
		expression = expression.replace("仟零陆佰", "仟陆佰");
		expression = expression.replace("仟柒", "仟零柒");
		expression = expression.replace("仟零柒佰", "仟柒佰");
		expression = expression.replace("仟捌", "仟零捌");
		expression = expression.replace("仟零捌佰", "仟捌佰");
		expression = expression.replace("仟玖", "仟零玖");
		expression = expression.replace("仟零玖佰", "仟玖佰");

		return expression;
	}

	/**
	 * Returns the integer Arabic numeral {@code numeral} in traditional Chinese
	 * word form (used in checks).
	 * <p>
	 * {@code numeral} can be positive, negative, zero, or negative zero.
	 * The absolute value of {@code numeral} must be less than one trillion
	 * (壹万亿). Ignores any leading zeros unless it is the last digit.
	 * If {@code numeral} is {@code ""}, returns {@code ""}. If {@code numeral}
	 * is {@code "-"}, returns {@code "负"}.
	 * <p>
	 * {@code "两"} is never used. The method uses {@code "壹十"} instead of
	 * {@code "十"}.
	 * <p>
	 * Examples:
	 * <blockquote>
	 * 
	 * <pre>
	 * integerTraditionalChineseOf("40562011") returns "肆仟零伍拾陆万贰仟零壹十壹"
	 * integerTraditionalChineseOf("-000") returns "负零"
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param numeral
	 *            the Arabic numeral to convert
	 * 
	 * @return the converted traditional Chinese-form number
	 * 
	 * @throws IllegalArgumentException("Input not an Arabic numeral")
	 * 
	 * @throws IllegalArgumentException("Input is too large")
	 */
	public static String integerTraditionalChineseOf(String numeral) throws IllegalArgumentException {

		/*
		 * 1. initialize variables
		 * 2. check whether number is negative (remember it)
		 * 3. check whether input is empty (return "")
		 */

		char[] digit; // array for digits of number
		String[] expression; // array for words-form of digits of number
		String finalExpression = ""; // final answer

		int length; // number of digits of integer part of Arabic numeral
		boolean isNegative = false; // whether the number is negative

		if (numeral.startsWith("-")) { // if the number is negative
			isNegative = true; // for use in finalExpression
			numeral = numeral.substring(1); // remove negative sign
		}

		length = numeral.length(); // length is after "-" is removed
		if (length == 0) { // if there is no input (other than "-")
			if (isNegative) // if the number is negative
				return "负";
			return ""; // otherwise
		}

		/*
		 * 4. put digits in string (String numeral) into character array (char[] digit)
		 * 5. check whether the input was an integer Arabic numeral (throw exception)
		 * 6. check whether the number is greater than or equal to one trillion (throw exception)
		 */

		digit = new char[length]; // length of character digit array
		// puts digits in string (String numeral) into character array (char[] digit)
		// and searches for non-digits
		for (int i = 0; i < length; i++) {
			digit[i] = numeral.charAt(i); // store separate digits as character array

			if ((int) digit[i] < 48 || (int) digit[i] > 57) { // if this is not a digit
				throw new IllegalArgumentException("Input not an Arabic numeral"); // error
			}

		}

		if (length > 12) { // if the number is too long (one thousand centillion)
			// note: postponed to after non-digit check in case the input was not an Arabic numeral
			throw new IllegalArgumentException("Input is too large");
		}

		/*
		 * 7. change character digits (char[] digit) into words, sorted into ones, tens,
		 * hundreds, and thousands; store into string array (String[] expression)
		 * 8. add thousands-word suffix (万, 亿) to string (expression[i])
		 */

		expression = new String[length]; // length of words string array
		// changes digits into strings (words)
		for (int i = length - 1; i > -1; i--) {

			switch ((length - i - 1) % 4) { // to sort between ones, tens, hundreds, and thousands
			case 0: // ones: change into words (壹, 贰, 叁)
				expression[i] = traditionalChineseExpressionOf(digit[i], false);
				break;
			case 1: // tens: change into words and add suffix (壹拾, 贰拾, 叁拾)
				// if the tens place is not empty
				if (!(digit[i] == (char) 0) && !(digit[i] == '0'))
					expression[i] = traditionalChineseExpressionOf(digit[i], false) + "拾";
				break;
			case 2: // hundreds: change into words and add suffix (壹佰, 贰佰)
				// if the hundreds place is not empty
				if (!(digit[i] == (char) 0) && !(digit[i] == '0'))
					expression[i] = traditionalChineseExpressionOf(digit[i], false) + "佰";
				break;
			case 3: // thousands: change into words and add suffix (壹仟, 贰仟)
				// if the thousands place is not empty
				if (!(digit[i] == (char) 0) && !(digit[i] == '0'))
					expression[i] = traditionalChineseExpressionOf(digit[i], false) + "仟";
			}

			// add suffix: 万, 亿
			if (// if this is the first digit and is not zero
					(i == 0 && digit[i] != '0') ||
					// or if this is the second digit and neither of the next two digits is zero
					(i == 1 && (digit[i] != '0' || digit[i - 1] != '0')) ||
					// or if this is over the second digit
					// and none of the next three digits is zero
					(i > 1 && (digit[i] != '0' || digit[i - 1] != '0' || digit[i - 2] != '0'))) {

				if (i == length - 5)
					expression[i] += "万";
				else if (i == length - 9)
					expression[i] += "亿";

			}

		}

		/*
		 * 9. add "负" (negative) (String finalExpression)
		 * 10. put strings (String[] expression) together (String finalExpression)
		 * 11. check whether the number is zero (return "零" or "负零")
		 * 12. correct some errors (String finalExpression)
		 * 13. return value (String finalExpression)
		 */

		if (isNegative) { // if the number is negative
			finalExpression = "负";
		}

		// puts strings together
		for (String singleExpression : expression) {

			if (singleExpression == null) {
				continue; // skip null
			}

			// the final digits in word form
			finalExpression += singleExpression;
		}

		// if the number is zero
		if (finalExpression.isEmpty()) {
			return "零";
		}

		// if the number is negative zero
		if (finalExpression.equals("负")) {
			return "负零";
		}

		// correct some errors
		finalExpression = correctTraditionalChineseExpression(finalExpression);

		return finalExpression;
	}

	// creates one digit of a Roman numeral string according to the Arabic numeral
	// (char digit) and the place before the decimal point (int place);
	// the number cannot be greater than three thousand
	private static String unitRomanNumeral(char digit, int place) {

		String onesChar = romanDigit(place, false);

		if (place > 4) { // it there are more than four places
			return "";
		}

		switch (digit) {
		case '1':
			return onesChar + "";
		case '2':
			return onesChar + "" + onesChar;
		case '3':
			return onesChar + "" + onesChar + onesChar;
		case '4':
			if (place < 4) // if the number is not over a thousand
				return onesChar + "" + romanDigit(place, true);
		case '9':
			if (place < 4) // if the number is not over a thousand
				return onesChar + "" + romanDigit(place + 1, false);
		default:
			// if digit is 5 to 8 and the number is not over a thousand
			if ((int) digit > 48 && (int) digit < 57 && place < 4) {
				return romanDigit(place, true) + unitRomanNumeral((char) (digit - 5), place);
			}
		}

		return "";
	}

	// returns the character used in a Roman numeral at the nth place
	// before the decimal point; used in method unitRomanNumeral
	private static String romanDigit(int place, boolean isFive) {
		switch (place) {
		case 1:
			if (isFive)
				return "V";
			return "I";
		case 2:
			if (isFive)
				return "L";
			return "X";
		case 3:
			if (isFive)
				return "D";
			return "C";
		case 4:
			if (!isFive)
				return "M";
		}

		return "";
	}

	/**
	 * Returns the input Arabic numeral {@code arabicNum} in Roman numeral form.
	 * <p>
	 * Only accepts 1 to 3999, integer. Ignores leading zeros. If input is
	 * {@code ""}, returns {@code ""}.
	 * <p>
	 * Example:
	 * <blockquote>
	 * 
	 * <pre>
	 * romanOf("0529") returns "DXXIX"
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param arabicNum
	 *            the Arabic numeral to convert
	 * 
	 * @return the converted Roman numeral
	 * 
	 * @throws IllegalArgumentException("Input not an Arabic numeral")
	 * 
	 * @throws IllegalArgumentException("Input out of domain")
	 */
	public static String romanOf(String arabicNum) throws IllegalArgumentException {

		/*
		 * 1. initialize variables
		 * 2. check whether input is empty (return "")
		 */

		char[] arabicDigit; // array for digits of Arabic numeral
		String[] RomanNum; // array for units of Roman numeral
		String finalRomanNum = ""; // final Roman numeral

		int length; // number of digits of integer part of Arabic numeral
		boolean isZero = true; // whether the number is zero

		length = arabicNum.length(); // length is after "-" is removed
		if (length == 0) { // if there is no input
			return "";
		}

		/*
		 * 3. put digits in string (String arabicNum) into character array (char[] arabicDigit)
		 * 4. check whether the input was an integer Arabic numeral (throw exception)
		 * 5. check whether the number is greater than or equal to five thousand
		 * (throw exception)
		 */

		arabicDigit = new char[length]; // length of character digit array
		// puts digits in string (String arabicNum) into character array (char[] arabicDigit)
		// and searches for non-digits
		for (int i = 0; i < length; i++) {
			arabicDigit[i] = arabicNum.charAt(i); // store separate digits as character array

			if ((int) arabicDigit[i] < 48 || (int) arabicDigit[i] > 57) { // if this is not a digit
				throw new IllegalArgumentException("Input not an Arabic numeral"); // error
			}

			if (arabicDigit[i] != '0') { // if one of the digits is not zero
				isZero = false;
			}

		}

		if (isZero) {
			throw new IllegalArgumentException("Input out of domain"); // input is zero
		}

		// if the number is too long or is four digits but is at least five thousand
		if (length > 4 || (length == 4 && (int) arabicDigit[0] > 51)) {
			// note: postponed to after non-digit check in case the input was not an Arabic numeral
			throw new IllegalArgumentException("Input out of domain"); // input is too large
		}

		/*
		 * 6. change character digits (char[] arabicDigit) into Roman numerals;
		 * store into string array (String[] romanNum)
		 * 7. put strings (String[] expression) together (String finalExpression)
		 * 8. return value (String finalExpression)
		 */

		RomanNum = new String[length]; // length of words string array
		for (int i = length - 1; i > -1; i--) { // changes digits into strings (words)
			RomanNum[i] = unitRomanNumeral(arabicDigit[i], length - i);
		}

		// puts strings together
		for (String singleRomanNum : RomanNum) {

			if (singleRomanNum == null) {
				continue; // skip null
			}

			// the final digits in word form
			finalRomanNum += singleRomanNum;

		}

		return finalRomanNum;
	}

	public static void main(String[] args) {

		System.out.print("Please input the number: ");
		Scanner scan = new Scanner(System.in);

		String numeral = null; // the input Arabic numeral

		try {
			numeral = scan.nextLine(); // read input Arabic numeral as string
			scan.close();

			System.out.println("The number is " + englishOf(numeral));

		} catch (IllegalArgumentException e) {
			scan.close();
			// input problem: wrong type or empty or too large
			System.out.println("\nError: " + e.getMessage());

			// tips for if the input was not Arabic numeral
			if (e.getMessage().equals("Input not an Arabic numeral")) {
				System.out.println("Check for spaces, punctuation"
						+ " (other than the negative sign and decimal point), and letters"
						+ " (please do not add commas)");
			}

			// explanation for inputs too large
			if (e.getMessage().equals("That is probably a googolplex")) {
				System.out.println("The input was larger than one thousand centillion" + " (or equal to it)");
				System.out.println("In fact, the number had " + numeral.length() + " digits."
						+ " One thousand centillion has 307 digits");
			}

		}

	}

}
