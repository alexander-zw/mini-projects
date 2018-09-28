package media;

import java.util.Scanner;

import images.APImage;
import images.Pixel;

@SuppressWarnings("restriction")
public class Sepia {
	
	public static void main(String[] args){
		int red;
		int blue;
		
		APImage image = new APImage("/Users/AlexanderWu/Documents/Photos/"
				+ "Cool Stuff/Wall·e.jpg");
		Scanner scan = new Scanner(System.in);	//used to delay drawing of modified image
		image.draw();
		
		for(Pixel p: image){
			int avgColor = EdgeDetection.getAvgColor(p);
			
			if(avgColor < 63){
				red = (int)(avgColor * 1.1);
				blue = (int)(avgColor * .9);
			}else if(avgColor < 192){
				red = (int)(avgColor * 1.15);
				blue = (int)(avgColor * .85);
			}else{
				red = Math.min((int)(avgColor * 1.08), 255);
				blue = (int)(avgColor * .93);
			}
			
			p.setRed(red);
			p.setGreen(blue);
			p.setBlue(avgColor);
		}
		
		System.out.print("Press enter to continue");
		scan.nextLine();	//consume return
		image.draw();
		scan.close();
	}

}
