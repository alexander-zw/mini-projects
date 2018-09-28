package adjectiveorder;

import java.io.FileNotFoundException;

public class Material extends Adjective {

	// Stores noun type correlations with adjectives
	private static String adjCorr = "Material.txt";
		
	public Material(String adj) {
		super(adj);
	}
	
	public Material() {}
	
	public String randAdj(int nounType) throws FileNotFoundException{
		if(nounType > 3)	//tools and furniture use the same adjectives
			nounType--;
		return super.randAdj(nounType, adjCorr);
	}
	
}
