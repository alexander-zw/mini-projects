package adjectiveorder;

import java.io.FileNotFoundException;

public class Colors extends Adjective {

	// Stores noun type correlations with adjectives
	private static String adjCorr = "Color.txt";
		
	public Colors(String adj) {
		super(adj);
	}
		
	public Colors() {}

	public String randAdj(int nounType) throws FileNotFoundException{
		if(nounType > 1)	//other than people, all nouns use the same modifiers
			nounType = 1;
		return super.randAdj(nounType, adjCorr);
	}
	
}
