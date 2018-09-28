package ordinary;

import java.util.Random;

public class NormalDistribution {
	
	private static boolean between(double num, double bound){
		return num > -bound && num < bound;
	}

	public static void main(String[] args) {

		Random rand = new Random();
		double norm;
		int dev1 = 0;
		int dev2 = 0;
		int dev3 = 0;
		
		for(int i = 0; i < 1000000; i++){
			norm = rand.nextGaussian();
			if(between(norm, 1)){
				dev1++;
			}
			if(between(norm, 2)){
				dev2++;
			}
			if(between(norm, 3)){
				dev3++;
			}
			if(!between(norm, 5)){
				System.out.println("The number is outside (-5,5): " + norm);
			}
		}
		
		System.out.println("Between -1 and 1: " + (dev1 / 10000.0)
				+ "%\nBetween -2 and 2: " + (dev2 / 10000.0)
				+ "%\nBetween -3 and 3: " + (dev3 / 10000.0) + "%");
		
	}

}