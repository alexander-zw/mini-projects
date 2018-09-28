package mathfunctions;

public class PowerAndLog {

	public static final double INFINITY = 1.0 / 0.0;
	public static final double UNDEFINED = 0.0 / 0.0;

	/**
	 * Computes the natural logarithm using the midpoint rule for the integral of 1 / x from 1 to
	 * {@code limit}.
	 * 
	 * @param limit
	 *            the x-value
	 * @param delta
	 *            the length of each partition; recommended about
	 *            100,000,100th of limit
	 * @return the logarithm
	 */
	public static double logMidp(double limit, double delta) {

		if (limit < 0.0) {
			return UNDEFINED;
		}
		if (delta <= 0.0 || delta == INFINITY) {
			throw new IllegalArgumentException("delta must be > 0 and finite");
		}
		if (limit == INFINITY) {
			return INFINITY;
		}
		if (limit == 0.0) {
			return -INFINITY;
		}

		double sum = 0.0; // sum of 1 / x
		double x; // the midpoints

		if (limit > 1) {
			x = 1 + delta / 2;
			while (x < limit) {
				sum += 1 / x;
				x += delta;
			}
		} else {
			x = 1 - delta / 2;
			while (x > limit) {
				sum -= 1 / x;
				x -= delta;
			}
		}
		return delta * sum;
	}

	/**
	 * Computes e to the power of x.
	 * <p>
	 * Uses the inverse of the natural logarithm. Computes the natural
	 * logarithm with the midpoint rule. Computes inverse function with
	 * binary search.
	 * <p>
	 * More efficient with small numbers.
	 * 
	 * @param x
	 *            the exponent of e
	 * @param error
	 *            approximate percentage error allowed in the return value; to ensure
	 *            speed, recommended at most one 1,000,000th
	 * @param delta
	 *            the length of partitions in the integral for the natural
	 *            logarithm; recommended the same as {@code error}
	 * @return the result
	 */
	public static double eToThe(double x, double error, double delta) {

		if (error < 0 || error == INFINITY) {
			throw new IllegalArgumentException("error must be > 0 and finite");
		}
		if (x == INFINITY) {
			return INFINITY;
		}
		if (x == -INFINITY) {
			return 0.0;
		}

		double left = 0.0;
		double right = INFINITY;
		double mid = 1.0;
		double log = 0.0;

		while (true) {

			if (log >= x - error) {
				if (log <= x + error) {
					return mid;
				}
				right = mid;
			} else {
				left = mid;
			}

			if (right == INFINITY) {
				mid = left * 2;
			} else {
				mid = (left + right) / 2;
			}

			System.out.printf("%20.15f%20.15f%n", mid, log);
			log = logMidp(mid, delta);

		}
	}

}
