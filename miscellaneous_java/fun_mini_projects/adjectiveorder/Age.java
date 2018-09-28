package adjectiveorder;

import java.io.FileNotFoundException;

public class Age extends Adjective {

	// Stores noun type correlations with adjectives
	private static String adjCorr = "Age.txt";
		
	public Age(String adj) {
		super(adj);
	}
		
	public Age() {}

	public String randAdj(int nounType) throws FileNotFoundException{
		//other than people, animals, and plants, all nouns use the same age modifiers
		if(nounType > 3)
			nounType = 3;
		return super.randAdj(nounType, adjCorr);
	}
	
}
