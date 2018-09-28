package media;

import java.util.Scanner;

import images.APImage;
import images.Pixel;

@SuppressWarnings("restriction")
public class SwitchColor {

	public static void main(String[] args) {
		APImage image = new APImage("/Users/AlexanderWu/Documents/Photos/"
				+ "Cool Stuff/Wall·e.jpg");
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);	//used to delay drawing of modified image
		image.draw();
		int oldRed;	//to store red value
		
		while(true){
			for(Pixel p: image){
				oldRed = p.getRed();
				p.setRed(p.getGreen());
				p.setGreen(p.getBlue());
				p.setBlue(oldRed);
			}
		
		System.out.print("Press enter to continue");
		scan.nextLine();	//consume return
		image.draw();
		
		}
		
	}

}
