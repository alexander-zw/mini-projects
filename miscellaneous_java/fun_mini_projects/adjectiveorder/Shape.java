package adjectiveorder;

import java.io.FileNotFoundException;

public class Shape extends Adjective {
	
	// Stores noun type correlations with adjectives
	private static String adjCorr = "Shape.txt";
		
	public Shape(String adj) {
		super(adj);
	}
		
	public Shape() {}

	public String randAdj(int nounType) throws FileNotFoundException{
		//other than people, animals, and plants, all nouns use the same shape modifiers
		if(nounType > 3)
			nounType = 3;
		return super.randAdj(nounType, adjCorr);
	}
	
}
