package mathfunctions;
public class TestNewtonsMethod {

	public static void main(String[] args) {
		LogFunction f = new LogFunction(-1, 100000000); // f(x) = ln(x) - 1
//		System.out.println("Using Newton's method...");
		System.out.println(NewtonsMethod.newtonZero(f, 1, 1e-9));
//		System.out.println(f.of(2.718281828459045));
	}

}
