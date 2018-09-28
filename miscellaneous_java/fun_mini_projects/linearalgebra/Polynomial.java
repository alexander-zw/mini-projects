package linearalgebra;

import java.util.Arrays;

public class Polynomial extends Vector implements Function {
	
	double[] coefficients;
	
	public Polynomial() {
		coefficients = new double[0];
	}

	public Polynomial(double[] coefficients) {
		// check that last coefficient is not 0
		if (coefficients[coefficients.length - 1] == 0) {
			// if it is, delete all 0's from end of array
			for (int i = coefficients.length - 1; i >= 0; i--) {
				if (coefficients[i] != 0) {
					coefficients = Arrays.copyOfRange(coefficients, 0, i + 1);
					break;
				}
				if (i == 0) { // if all coefficients are 0
					coefficients = new double[0];
				}
			}
		}
		
		this.coefficients = coefficients;
	}
	
	public double[] getCoefficients() {
		return coefficients;
	}

	@Override
	public double of(double x) {
		double sumTerms = 0;
		double xTerm = 1;
		for (double coeff : coefficients) {
			sumTerms += coeff * xTerm;
			xTerm *= x;
		}
		return sumTerms;
	}

	@Override
	public int dimension() {
		return coefficients.length;
	}

	@Override
	public Vector add(Vector other) {
		if (!(other instanceof Polynomial))
			throw new IllegalArgumentException("Addend not a polynomial");
		double[] thisCoeffs = coefficients;
		double[] otherCoeffs = ((Polynomial) other).getCoefficients();
		if (dimension() > other.dimension()) {
			// add 0's at end of other coeffs so they can add
			otherCoeffs = addZeros(otherCoeffs, dimension());
		} else if (dimension() < other.dimension()) {
			// add 0's at end of this coeffs so they can add
			thisCoeffs = addZeros(thisCoeffs, other.dimension());
		}
		return new Polynomial(RVector.sumArrayVals(thisCoeffs, otherCoeffs));
	}
	
	private static double[] addZeros(double[] arr, int totalLength) {
		double[] arrWith0s = new double[totalLength];
		for (int i = 0; i < arr.length; i++) {
			arrWith0s[i] = arr[i];
		}
		return arrWith0s;
	}

	@Override
	public Vector scale(double scalor) {
		return new Polynomial(RVector.scaleArrayVals(coefficients, scalor));
	}

	/**
	 * 
	 * @param other
	 * @return
	 */
	@Override
	public double innerP(Vector other) {
		if (!(other instanceof Polynomial))
			throw new IllegalArgumentException("Argument not a polynomial");
		return toRVectorForInnerP().innerP(((Polynomial) other).toRVectorForInnerP());
	}
	
	public RVector toRVector(int dim) {
		return new RVector(addZeros(coefficients, dim));
	}
	
	public RVector toRVectorForInnerP() {
		double[] values = new double[5];
		for (int x = -2; x <= 2; x++) {
			values[x + 2] = of(x);
		}
		return new RVector(values);
	}
	
	@Override
	public String toString() {
		String strPoly = "p(t) = ";
		if (coefficients.length == 0)
			return strPoly + "0";
		
		for (int power = 0; power < dimension(); power++) {
			if (coefficients[power] != 0) // only display if coefficient is not 0
				strPoly += strTerm(power) + " + ";
		}
		return strPoly.substring(0, strPoly.length() - 3);
	}
	
	private String strTerm(int power) {
		switch (power) {
		case 0:
			return "" + coefficients[power];
		case 1:
			return coefficients[power] + "t";
		default:
			return coefficients[power] + "t^" + power;
		}
	}

}
