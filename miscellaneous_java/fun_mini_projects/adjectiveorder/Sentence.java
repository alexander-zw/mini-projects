package adjectiveorder;

import java.io.FileNotFoundException;

public class Sentence {
	
	private String sent;
	
	public Sentence(int nounType, String noun) throws FileNotFoundException{
		Opinion op = new Opinion();
		Size size = new Size();
		Age age = new Age();
		Shape shape = new Shape();
		Colors col = new Colors();
		Origin orig = new Origin();
		Material mat = new Material();
		Purpose pur = new Purpose();
		sent = "the " + op.randAdj(nounType) + " " + size.randAdj(nounType) + " " +
				age.randAdj(nounType) + " " + shape.randAdj(nounType) + " " +
				col.randAdj(nounType) + " " + orig.randAdj(nounType) + " " + mat.randAdj(nounType)
				+ " " + pur.randAdj(nounType) + " " + noun;
	}

	public String getSentence() {
		return sent;
	}
	
}
