package adjectiveorder;

import java.io.FileNotFoundException;

public class Origin extends Adjective {

	// Stores noun type correlations with adjectives
	private static String adjCorr = "Origin.txt";
		
	public Origin(String adj) {
		super(adj);
	}
		
	public Origin() {}

	public String randAdj(int nounType) throws FileNotFoundException{
		//all have same modifiers
		return super.randAdj(0, adjCorr);
	}
	
}
