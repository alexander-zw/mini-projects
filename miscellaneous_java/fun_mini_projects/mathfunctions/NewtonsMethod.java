package mathfunctions;

public class NewtonsMethod {

	public static double newtonZero(Function f, double guess, double tolerance) {
		Function df;
		try {
			df = f.derivative();
		} catch (UnimplementedException e) {
			e.printStackTrace();
			return 0;
		}
		while (f.of(guess) != 0 && !approxEq(f.of(guess), 0, tolerance)) {
			guess = newtonUpdate(f, df, guess);
		}
		return guess;
	}
	
	public static double newtonZero(Function f, double guess) {
		return newtonZero(f, guess, 1e-14);
	}

	public static double newtonZero(Function f) {
		return newtonZero(f, 1, 1e-14);
	}

	private static double newtonUpdate(Function f, Function df, double guess) {
		return guess - f.of(guess) / df.of(guess);
	}

	private static boolean approxEq(double x, double y, double tolerance) {
		return Math.abs(x - y) < tolerance;
	}

}
