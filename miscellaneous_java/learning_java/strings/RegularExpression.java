package strings;

public class RegularExpression {

	public static void main(String[] args) {
		
		String aString = "I am. Happy\n.today";
		String[] strArr = aString.split("[.o]");
		
		for(String str: strArr){
			System.out.println(str);
		}
		
	}

}
