package luckysevens;

import java.util.Random;
import java.util.Scanner;

/*
 * Note: from Fundamentals of Java
 */
public class ToBreak {
	
	 //returns "s" if count > 1
	public static String plural(int count){
		if(count == 1){	//if there was only one roll
			return "";	//don't add "s"
		}
		return "s";	//otherwise add an "s"
	}
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		
		int die1, die2;	//numbers of dices
		int money;	//initial amount of money
		int count = 0;	//number of rolls
		int maxMoney;	//maximum amount of money
		int countAtMax = 0;	//number of rolls to reach maximum amount of money
		
		try{
			System.out.print("Please enter the amount of money in dollars: ");
			money = scan.nextInt();	//input amount of money
			maxMoney = money;
			
			if(money < 1){	//if the input was not a positive number
				throw new Exception("You were quite thick to come with no money at all");
			}
			
			while(money > 0){	//loop until the money is depleted
				count++;
				
				die1 = rand.nextInt(6) + 1;	//roll dice
				die2 = rand.nextInt(6) + 1;

				if(die1 + die2 == 7){	//change in winnings
					money += 4;
				}else{
					money -= 1;
				}
				
				if(money > maxMoney){	//if this is a maximum, remember it
					maxMoney = money;
					countAtMax = count;
				}
				
			}
			
			System.out.println("You are broke after " + count + " roll" + plural(count) +
					"\nYou should have quit after " + countAtMax + " roll" + plural(countAtMax)
					+ " when you had $" + maxMoney);
			
		}catch(Exception e){
			//if the input was not an integer
			if(e.toString().equals("java.util.InputMismatchException")){
				System.out.println("We only accept whole US dollars");	//error message
				
			}	//if the integer is too large
			else if(e.toString().startsWith("java.util.InputMismatchException")){
				//error message
				System.out.println("Sorry, we don't accept more money than two billion dollars");
			}else{
				System.out.println(e.getMessage());
			}
			
		}finally{
			scan.close();
		}
		
	}

}
