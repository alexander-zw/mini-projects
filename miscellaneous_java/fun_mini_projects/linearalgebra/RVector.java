package linearalgebra;

public class RVector extends Vector {
	
	private double[] values;

	public RVector(double[] values) {
		this.values = values;
	}
	
	public double[] getValues() {
		return values;
	}

	@Override
	public int dimension() {
		return values.length;
	}

	@Override
	public Vector add(Vector other) {
		if (!(other instanceof RVector))
			throw new IllegalArgumentException("Addend not a vector in R^n");
		return new RVector(sumArrayVals(values, ((RVector) other).getValues()));
	}
	
	public static double[] sumArrayVals(double[] arr1, double[] arr2) {
		if (arr1.length != arr2.length)
			throw new IllegalArgumentException("Arrays not of equal length");
		double[] sumArr = new double[arr1.length];
		for (int i = 0; i < arr1.length; i++) {
			sumArr[i] = arr1[i] + arr2[i];
		}
		return sumArr;
	}
	
	@Override
	public Vector scale(double scalor) {
		return new RVector(scaleArrayVals(values, scalor));
	}

	public static double[] scaleArrayVals(double[] arr, double scalor) {
		double[] scaledArr = new double[arr.length];
		for (int i = 0; i < arr.length; i++) {
			scaledArr[i] = scalor * arr[i];
		}
		return scaledArr;
	}

	@Override
	public double innerP(Vector other) {
		if (!(other instanceof RVector))
			throw new IllegalArgumentException("Argument not a vector in R^n");
		double sumVals = 0;
		for (int i = 0; i < values.length; i++) {
			sumVals += values[i] * ((RVector) other).getValues()[i];
		}
		return sumVals;
	}
	
	@Override
	public String toString() {
		String strVector = "[";
		
		for (double val : values) {
			strVector += val + " ";
		}
		return strVector.substring(0, strVector.length() - 1) + "]";
	}

}
