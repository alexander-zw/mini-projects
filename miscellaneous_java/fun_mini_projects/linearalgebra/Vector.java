package linearalgebra;

public abstract class Vector {
	
	public abstract int dimension();
	
	public abstract Vector add(Vector other);

	public abstract Vector scale(double scalor);
	
	public abstract double innerP(Vector other);
	
	public double orthogonalCoeff(Vector basisVect) {
		// coefficient of v2 with basis v1 = <v1, v2> / <v1, v1>
		return innerP(basisVect) / basisVect.innerP(basisVect);
	}
	
	public Vector projectionOnto(Vector basisVect) {
		return basisVect.scale(orthogonalCoeff(basisVect)); // proj v2 onto v1 = <v1, v2> / <v1, v1> * v1
	}
	
	public double norm() {
		return Math.sqrt(innerP(this));
	}

}
