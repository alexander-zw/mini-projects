package mathfunctions;

public class LogFunction implements Function {

	private static final double INFINITY = 1.0 / 0.0;
	private static final double UNDEFINED = 0.0 / 0.0;
	
	private double constant;
	private int numPartitions;

	public LogFunction(double constantTerm, int partitions) {
		constant = constantTerm;
		numPartitions = partitions;
	}

	public LogFunction(double constantTerm) {
		this(constantTerm, 100000000);
	}

	public LogFunction() {
		this(0);
	}

	/**
	 * Computes the natural logarithm using the midpoint rule for the integral of 1 / x from 1 to
	 * {@code limit}.
	 * 
	 * @param limit
	 *            the x-value
	 * @param partitionLength
	 *            the length of each partition; recommended about
	 *            100,000,000th of limit
	 * @return the logarithm
	 */
	private double logMidp(double limit) {
		
		double partitionLength = limit / numPartitions;

		if (limit < 0.0) {
			return UNDEFINED;
		}
		if (partitionLength <= 0.0 || partitionLength == INFINITY) {
			throw new IllegalArgumentException("partitionLength must be > 0 and finite");
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
			x = 1 + partitionLength / 2;
			while (x < limit) {
				sum += 1 / x;
				x += partitionLength;
			}
		} else {
			x = 1 - partitionLength / 2;
			while (x > limit) {
				sum -= 1 / x;
				x -= partitionLength;
			}
		}
		return partitionLength * sum;
	}

	@Override
	public double of(double x) {
		return logMidp(x) + constant;
	}

	public Function derivative() {
		return new Function() {

			@Override
			public double of(double x) {
				return 1 / x;
			}

		};
	}

}
