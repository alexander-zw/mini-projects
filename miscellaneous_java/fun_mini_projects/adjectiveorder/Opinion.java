package adjectiveorder;

import java.io.FileNotFoundException;

public class Opinion extends Adjective {

	// Stores noun type correlations with adjectives
	private static String adjCorr = "Opinion.txt";
	
	public Opinion(String adj) {
		super(adj);
	}
	
	public Opinion() {}

	public String randAdj(int nounType) throws FileNotFoundException{
		return super.randAdj(nounType, adjCorr);
	}
	
}
