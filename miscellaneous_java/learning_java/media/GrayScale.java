package media;

import java.util.Scanner;

import images.APImage;
import images.Pixel;

@SuppressWarnings("restriction")
public class GrayScale {
	
	//computes average of RGB values of pixel according to human relative luminance
	//proportions
	public static int getWeightedAvgColor(Pixel p){
		return (int) (p.getRed() * .299 + p.getGreen() * .587 + p.getBlue() * .144);
	}
	
	public static void main(String[] args){
		APImage image = new APImage("/Users/AlexanderWu/Documents/Photos/"
				+ "Cool Stuff/Wall·e.jpg");
		Scanner scan = new Scanner(System.in);	//used to delay drawing of modified image
		image.draw();
		
		for(Pixel p: image){
			int avgColor = getWeightedAvgColor(p);
			p.setRed(avgColor);
			p.setGreen(avgColor);
			p.setBlue(avgColor);
		}
		
		System.out.print("Press enter to continue");
		scan.nextLine();	//consume return
		image.draw();
		scan.close();
	}

}
