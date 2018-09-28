package media;

import java.util.Scanner;

import images.APImage;
import images.Pixel;

@SuppressWarnings("restriction")
public class EdgeDetection {
	
	//computes average of RGB values of pixel
	public static int getAvgColor(Pixel p){
		return (p.getRed() + p.getGreen() + p.getBlue()) / 3;
	}
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);	//used to delay drawing of modified image
		try{
			APImage originalImage = new APImage("/Users/AlexanderWu/Documents/Photos/"
					+ "Cool stuff/Wall·e.jpg");
			originalImage.draw();
			
			int threshold = 5;	//the difference in color value required to become an edge
			int height = originalImage.getHeight();
			int width = originalImage.getWidth();
			APImage newImage = new APImage(width, height);
			
			for(int y = 0; y < height - 1; y++){
				for(int x = 1; x < width; x++){
					Pixel oldP = originalImage.getPixel(x, y);
					Pixel leftP = originalImage.getPixel(x - 1, y);
					Pixel bottomP = originalImage.getPixel(x, y + 1);
					int oldAvg = getAvgColor(oldP);
					int leftAvg = getAvgColor(leftP);
					int bottomAvg = getAvgColor(bottomP);
					
					Pixel newP = newImage.getPixel(x, y);
					if(Math.abs(oldAvg - leftAvg) <= threshold ||
							Math.abs(oldAvg - bottomAvg) <= threshold){
						newP.setRed(255);
						newP.setGreen(255);
						newP.setBlue(255);
					}
				}
			}
			
			System.out.print("Press enter to continue");
			scan.nextLine();	//consume return
			newImage.draw();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			scan.close();
		}
		
	}
	
}
