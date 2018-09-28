package linearalgebra;

public class TestPolynomial {

	public static void main(String[] args) {
		
		// known vectors in orthogonal basis
		Polynomial p0 = new Polynomial(new double[]{1}); // p(t) = 1
		Polynomial p1 = new Polynomial(new double[]{0, 1}); // p(t) = t
		Polynomial p2 = new Polynomial(new double[]{-2, 0, 1}); // p(t) = -2 + t^2

		// vectors that are not orthogonal
		Polynomial p3 = new Polynomial(new double[]{0, 0, 0, 1}); // p(t) = t^3
		Polynomial p4 = new Polynomial(new double[]{0, 0, 0, 0, 1}); // p(t) = t^4
		
		// make them orthogonal with Gram-Schmidt process
		// p3 = p3 - (proj p3 onto p0 + ... + proj p3 onto p2)
		p3 = (Polynomial) p3.add(
				p3.projectionOnto(p0).add(
					p3.projectionOnto(p1).add(
						p3.projectionOnto(p2)
					)
				).scale(-1)
			 ).scale(5); // scale by 5 to make coefficients integers
		// p4 = p4 - (proj p4 onto p0 + ... + proj p4 onto p3)
		p4 = (Polynomial) p4.add(
				p4.projectionOnto(p0).add(
					p4.projectionOnto(p1).add(
						p4.projectionOnto(p2).add(
							p4.projectionOnto(p3)
						)
					)
				).scale(-1)
			 ).scale(35); // scale by 35 to make coefficients integers
		
		// do trend analysis on the data
		// data was taken at t = -2, -1, 0, 1, 2 respectively
		RVector data = new RVector(new double[]{3, 5, 5, 4, 3});
		System.out.println(data.orthogonalCoeff(p0.toRVectorForInnerP()));
		System.out.println(data.orthogonalCoeff(p1.toRVectorForInnerP()));
		System.out.println(data.orthogonalCoeff(p2.toRVectorForInnerP()));
		System.out.println(data.orthogonalCoeff(p3.toRVectorForInnerP()));
		System.out.println(data.orthogonalCoeff(p4.toRVectorForInnerP()));
		
	}

}
