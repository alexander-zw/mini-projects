package media;

import java.util.Scanner;

import images.APImage;
import images.Pixel;

@SuppressWarnings("restriction")
public class BlackAndWhite {
	public static void main(String[] args){
		APImage image = new APImage("/Users/AlexanderWu/Documents/Photos/"
				+ "Cool Stuff/Wall·e.jpg");
		Scanner scan = new Scanner(System.in);	//used to delay drawing of modified image
		image.draw();
		
		for(Pixel p: image){
			int avgColor = EdgeDetection.getAvgColor(p);
			if(avgColor < 128){
				p.setRed(0);
				p.setGreen(0);
				p.setBlue(0);
			}else{
				p.setRed(255);
				p.setGreen(255);
				p.setBlue(255);
				
			}
		}
		
		System.out.print("Press enter to continue");
		scan.nextLine();	//consume return
		image.draw();
		scan.close();
	}
}
