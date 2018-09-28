package adjectiveorder;

public class Noun {
	
	//the types of nouns
	public static final int PERSON = 0;
	public static final int ANIMAL = 1;
	public static final int PLANT = 2;
	public static final int TOOL = 3;
	public static final int FURNITURE = 4;
	public static final int BOOK = 5;
	public static final int STRUCTURE = 6;
	public static final int CLOTHING = 7;
	public static final int OTHER = 8;
	
	@Override
	public String toString() {
		return "person: " + PERSON + "\tanimal: " + ANIMAL + "\tplant: " + PLANT + "\ttool: " +
				TOOL + "\tfurniture or electronic: " + FURNITURE + "\tbook: " + BOOK +
				"\nstructure or vehicle: " + STRUCTURE + "\tclothing: " + CLOTHING + "\tother: "
				+ OTHER;
	}
	
}
