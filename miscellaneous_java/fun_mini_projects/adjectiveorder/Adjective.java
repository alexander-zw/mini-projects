package adjectiveorder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Adjective {
	
	//the adjective
	protected String word;
	
	// where the adjectives are stored (folder)
	private String folder = "/Users/AlexanderWu/Code/self/Java/database/adjectives/";

	public Adjective(String adj){
		word = adj;
	}

	public Adjective(){
		this("");
	}
	
	public String getWord() {
		return word;
	}
	
	/*
	 * Gets a random adjective for a noun type using the file adjFile
	 */
	public String randAdj(int nounType, String adjFilePath) throws FileNotFoundException{

		Scanner scan = new Scanner(new File(folder + adjFilePath));	//obtain information from file containing adjs
		
		String word = "";	//the scanned word in adjFile
		int count = 0;	//the noun type scan is at
		
		while(count != nounType && scan.hasNext()){	//move forward until gets to the noun type
			word = scan.next();
			if(word.equals("\\")){	//use backslash as sentinel for new noun type
				count++;
			}
		}
		
		HashSet<String> adjs = new HashSet<String>();	//set to store adjectives to choose from
		word = scan.next();
		
		while(!word.equals("\\") && scan.hasNext()){	//add all adjs for noun type to set
			adjs.add(word);
			word = scan.next();
		}
		
		count = 0;	//the word count in set
		//random number to generate random adjective
		int rand = new Random().nextInt(adjs.size()) + 1;
		
		for(String str: adjs){
			count++;
			if(count == rand){
				word = str;
				break;	//stop at random number
			}
		}
		
		scan.close();
		return word;	//return random string in set
	}

}
