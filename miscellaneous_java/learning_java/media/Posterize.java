package media;

import java.util.Random;
import java.util.Scanner;

import images.APImage;
import images.Pixel;

@SuppressWarnings("restriction")
public class Posterize {
	
	private static int getRandomColor(){
		Random randGen = new Random();
		return randGen.nextInt(256);
	}
	
	public static void main(String[] args){
		APImage image = new APImage("/Users/AlexanderWu/Documents/Photos/"
				+ "Cool Stuff/Wall·e.jpg");
		Scanner scan = new Scanner(System.in);	//used to delay drawing of modified image
		image.draw();
		int red1 = getRandomColor();
		int green1 = getRandomColor();
		int blue1 = getRandomColor();
		int red2 = getRandomColor();
		int green2 = getRandomColor();
		int blue2 = getRandomColor();
		
		for(Pixel p: image){
			int avgColor = EdgeDetection.getAvgColor(p);
			if(avgColor < 128){
				p.setRed(red1);
				p.setGreen(green1);
				p.setBlue(blue1);
			}else{
				p.setRed(red2);
				p.setGreen(green2);
				p.setBlue(blue2);
				
			}
		}
		
		System.out.print("Press enter to continue");
		scan.nextLine();	//consume return
		image.draw();
		scan.close();
		
		System.out.println("The colors are " + red1 + "/" + green1 + "/" + blue1 + " and "
				+ red2 + "/" + green2 + "/" + blue2);
	}
}
