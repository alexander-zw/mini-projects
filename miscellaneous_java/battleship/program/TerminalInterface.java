package program;

import java.util.InputMismatchException;
import java.util.Scanner;

import data.ComputerWonException;
import data.GridOccupiedException;
import data.HumanWonException;
import data.Position;
import data.PositionAlreadyHitException;
import data.Ship;
import data.ShipSunkException;

/**
 * A terminal interface for the game.
 * 
 * @author AlexanderWu
 *
 */
public class TerminalInterface {
	
	public static Human player = new Human();
	public static Computer computer = new Computer(true, false);
	public static Scanner inputer = new Scanner(System.in);
	
	public static final int INSPECT_PLAYER_SEAS = 1;
	public static final int INSPECT_OPPONENT_SEAS = 2;
	public static final int CONTINUE = 3;

	public static void addShips() {
		addPlayerShips();
		computer.addShips();
	}

	public static void addPlayerShips() {
		System.out.println("Please position your fleet in your seas.");
		for (int shipType : Ship.SHIP_TYPES)
			addOneShip(shipType);
		System.out.println("All ships placed!");
	}
	
	private static void addOneShip(int shipType) {
		boolean firstTry = true;
		while (true) {
			try {
				System.out.println("\nWhere would you like to place your " + Ship.getStringType(shipType) +
						"? Please input top-left point (x y).");
				int xPos = getIntInput();
				int yPos = getIntInput();
				System.out.println("Would you like the ship to be oriented horizontally?");
				boolean horizontal = getBooleanInput();
				player.addShip(shipType, new Position(xPos, yPos), horizontal, !firstTry);
				
				showPlayerSeas();
				System.out.println("Are you satisfied with that?");
				boolean satisfied = getBooleanInput();
				if (satisfied) return;
				
				System.out.println("Fine, then you may reenter.");
				firstTry = false;
				
			} catch (GridOccupiedException e) {
				System.out.println(
						"The space is already occupied by another ship! Please try another layout.");
			} catch (IllegalArgumentException e) {
				System.out.println("The ship is not completely in your seas! Position it somewhere else.");
			}
		}
	}
	
	private static int getIntInput() {
		while (true) {
			try {
				int intInput = inputer.nextInt();
				return intInput;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please input an integer.");
				inputer = new Scanner(System.in);
			} 
		}
	}

	private static boolean getBooleanInput() {
		while (true) {
			String message = inputer.next();
			if (message.equalsIgnoreCase("yes")) return true;
			if (message.equalsIgnoreCase("no")) return false;
			System.out.println("Invalid input. Please input an \"yes\" or \"no\".");
		}
	}

	public static void playGame() {
		
		System.out.println("\nYou may begin attacking the enemy fleet!");
		try {
			if (Math.random() < 0.5) computerTakeTurn(); // randomly choose who goes first
			while (true) {
				playerTakeTurn();
				computerTakeTurn();
			}
			
		} catch (ComputerWonException e) {
			System.out.println("\nOh, no! The enemy has destroyed your fleet and taken control of the"
					+ " seas!\nTake on arms and defeat them in a new game of Battleship!");
		} catch (HumanWonException e) {
			System.out.println("\nCongratulations! You have destroyed the entire enemy fleet and brought "
					+ "peace to the world!\nBe sure to come again for another "
					+ "spectacular game of Battleship!");
		}
	}
	
	public static void playerTakeTurn() throws HumanWonException {
		
		System.out.println("It's your turn.");
//		acceptGeneralUserInput();
		
		deliverHit();
		showOpponentSeas();
		inputer.nextLine(); // eliminate last newline
		inputer.nextLine(); // user presses enter
		
		if (computer.hasLost())
			throw new HumanWonException(player);
	}
	
	@SuppressWarnings("unused")
	private static void acceptGeneralUserInput() {
		loop1: while (true) {
			int choice = getIntInput();
			switch (choice) {
			case INSPECT_PLAYER_SEAS:
				showPlayerSeas();
				break;
			case INSPECT_OPPONENT_SEAS:
				showOpponentSeas();
				break;
			case CONTINUE:
				break loop1;
			default:
				System.out.println("Please choose one of the following:");
				System.out.println("1: inspect your seas");
				System.out.println("2: inspect the enemy seas");
				System.out.println("3: continue with attack");
			}
		}
	}

	private static void showPlayerSeas() {
		System.out.println("This is what your seas look like now.");
		player.printPlayerStates();
	}

	private static void showOpponentSeas() {
		System.out.println("This is what the enemy seas look like now:");
		player.printOpponentStates();
	}
	
	public static void deliverHit() {
		
		System.out.println("Where in the enemy seas would you like to hit? Please input coordinate (x y).");
		while (true) {
			int xPos = getIntInput();
			int yPos = getIntInput();
			try {
				if (player.hit(computer, new Position(xPos, yPos)))
					System.out.println("You've hit an enemy ship!");
				else
					System.out.println("You missed.");
				break;
				
			} catch (ShipSunkException e) {
				System.out.println("Yesss! You've sunk the enemy " +
						Ship.getStringType(e.getShipSunk().getType()) + "!");
				break;
				
			} catch (PositionAlreadyHitException e) {
				System.out.println("You have already hit this location! Choose another one.");
			} catch (IllegalArgumentException e) {
				System.out.println("This is not part of the enemy seas! Hit somewhere else!");
			}
		}
	}
	
	public static void computerTakeTurn() throws ComputerWonException {
		
		System.out.println("It's the enemy's turn.");
		try {
			if (computer.hit(player))
				System.out.println("The enemy has hit one of your ships!");
			else
				System.out.println("The enemy missed.");
		} catch (ShipSunkException e) {
			System.out.println("Nooooo! The enemy sank your " +
					Ship.getStringType(e.getShipSunk().getType()) + "!");
		}
		System.out.println("The location hit by the enemy was " + computer.getPositionLastHit() + ".");
		
		showPlayerSeas();
		inputer.nextLine(); // user presses enter
		
		if (player.hasLost())
			throw new ComputerWonException(computer);

	}
	
	public static void main(String[] args) {
		
		addShips();
		playGame();
		
		inputer.close();
	}

}
