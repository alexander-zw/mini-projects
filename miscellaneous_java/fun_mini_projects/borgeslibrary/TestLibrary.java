package borgeslibrary;

public class TestLibrary {

	public static void main(String[] args) {
		String str = "I am so happy that you are here.";
		for (String child : StringMethods.offspringOf(str))
			System.out.println(child);
	}

}
