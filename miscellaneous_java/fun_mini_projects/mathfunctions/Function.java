package mathfunctions;

public interface Function {
	
	public double of(double x);
	
	default Function derivative() throws UnimplementedException {
		throw new UnimplementedException();
	}
	
}
