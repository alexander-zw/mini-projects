package adjectiveorder;

import java.io.FileNotFoundException;

public class Size extends Adjective {
	
	// Stores noun type correlations with adjectives
	private static String adjCorr = "Size.txt";
		
	public Size(String adj) {
		super(adj);
	}
		
	public Size() {}

	public String randAdj(int nounType) throws FileNotFoundException{
		return super.randAdj(0, adjCorr);	//all nouns use the same size modifiers
	}
	
}
