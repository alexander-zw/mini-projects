package adjectiveorder;

import java.util.Scanner;

public class TestAdj {

	public static void main(String[] args) {
		
		try {
			System.out.println(new Noun());	//show noun types
			Scanner scan = new Scanner(System.in);
			
			//scan the noun type and noun
			Sentence sent = new Sentence(scan.nextInt(), scan.next());
			System.out.println(sent.getSentence());
			
			scan.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
