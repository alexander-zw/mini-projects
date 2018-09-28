package ordinary;

public class TestScope {

	private int instVar;

	public TestScope() {

	}

	public TestScope(int instVar) {
		// no effect
		// instVar = instVar;
		this.instVar = instVar;
	}

	public static void main(String[] args) {

		TestScope test1 = new TestScope();
		System.out.println(test1.instVar);
		TestScope test2 = new TestScope(1);
		System.out.println(test2.instVar);

		int x;
		// compile-time error
		// System.out.println(x);

		x = 3;
		if (x == 3) {
			@SuppressWarnings("unused")
			int y = 3;
		}
		// compile-time error
		// System.out.println(y);

		@SuppressWarnings("unused")
		int y = 4;
		// compile-time error
		// if (x == 3) {
		// int y = 5;
		// }

		// compile-time error
		// int y = 3;

		while (x > 0) {
			@SuppressWarnings("unused")
			int z = 1;
			x--;
		}

	}

}
