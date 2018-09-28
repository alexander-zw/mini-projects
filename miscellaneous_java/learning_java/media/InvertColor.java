package media;

import java.util.Scanner;

import images.APImage;
import images.Pixel;

@SuppressWarnings("restriction")
public class InvertColor {

	public static void main(String[] args) {
		APImage image = new APImage("/Users/AlexanderWu/Documents/Photos/"
				+ "Cool Stuff/Wall·e.jpg");
		Scanner scan = new Scanner(System.in);	//used to delay drawing of modified image
		image.draw();
		
		for(Pixel p: image){
			p.setRed(255 - p.getRed());
			p.setGreen(255 - p.getGreen());
			p.setBlue(255 - p.getBlue());
		}
		
		System.out.print("Press enter to continue");
		scan.nextLine();	//consume return
		image.draw();
		scan.close();

	}

}
