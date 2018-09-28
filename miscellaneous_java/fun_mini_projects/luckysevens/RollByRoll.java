package luckysevens;

import java.util.Random;
import java.util.Scanner;

public class RollByRoll {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		
		int die1, die2;	//numbers of dices
		int money;	//amount of money
		int count = 0;	//number of rolls
		int maxMoney;	//maximum amount of money
		int countAtMax = 0;	//number of rolls to reach maximum amount of money
		
		int finalCount = 0;	//the final number of rolls
		int finalMoney = 0;	//the final amount of money
		boolean quit = false;	//whether the user has already quit
		
		try{
			System.out.print("Please enter the amount of money in dollars: ");
			money = scan.nextInt();	//input amount of money
			scan.nextLine();	//consume return
			maxMoney = money;
			System.out.println();	//change line
			
			if(money < 1){	//if the input was not a positive number
				throw new Exception("You were quite thick to come with no money at all");
			}
			
			while(money > 0){ //loop until the money is depleted
				count++;
				
				if(!quit)
					System.out.println("Roll " + count);
				
				die1 = rand.nextInt(6) + 1;	//roll dice
				die2 = rand.nextInt(6) + 1;
				if(!quit)
					System.out.print("You rolled " + die1 + " and " + die2);

				if(die1 + die2 == 7){	//change in winnings
					money += 4;
					if(!quit)
						System.out.println(" and won $4");
				}else{
					money -= 1;
					if(!quit)
						System.out.println(" and lost $1");
				}
				
				if(money > maxMoney){	//if this is a maximum, remember it
					maxMoney = money;
					countAtMax = count;
				}
				
				if(!quit){
					System.out.println("You now have $" + money);
					System.out.println("Press enter to continue");
				}
				
				if(!quit && !scan.nextLine().isEmpty()){	//if the user quits
					finalCount = count;
					finalMoney = money;
					quit = true;	//the user has quit
				}
				
			}
			
			if(!quit){
				finalCount = count;
				finalMoney = money;
			}
			
			System.out.println("\nYou quit after " + finalCount + " roll" +
					ToBreak.plural(finalCount) + " and now have $" + finalMoney);
			//decides whether to add "would have"
			System.out.println("You should have quit after " + countAtMax + " roll" +
					ToBreak.plural(countAtMax) + " when you " +
					((countAtMax > finalCount)?"would have ":"") + "had $" + maxMoney);
			System.out.println("You would have broke after " + count + " roll" +
					ToBreak.plural(count));
			
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
