package program;

import data.*;

public class Player {

	protected Ship[] fleet = new Ship[5];
	protected int[][] playerStates = new int[Position.MAX_COORDINATE][Position.MAX_COORDINATE];
	protected int[][] opponentStates = new int[Position.MAX_COORDINATE][Position.MAX_COORDINATE];

	public static final int BLANK = 0;
	public static final int HIT = 1;
	public static final int MISS = 2;
	public static final int SUNK = 3;

	
	/* ------------------------ Gets and sets ------------------------ */

	/**
	 * returns the player's grid, with state of each position: hit, miss, or
	 * blank
	 * 
	 * @return matrix of states of opponent's grid
	 */
	public int[][] playerStates() {
		return playerStates;
	}

	public int getPlayerState(Position pos){
		return playerStates[pos.getY() - 1][pos.getX() - 1]; // x is column, y is row
	}

	/**
	 * sets state of one position of player's grid
	 * 
	 * @param pos
	 *            the position to update
	 * @param state
	 *            the new state of the position
	 */
	protected void setPlayerState(Position pos, int state) {
		playerStates[pos.getY() - 1][pos.getX() - 1] = state; // x is column, y is row
	}
	
	/**
	 * returns the opponent's grid, with state of each position: hit, miss, or
	 * blank
	 * 
	 * @return matrix of states of opponent's grid
	 */
	public int[][] opponentStates() {
		return opponentStates;
	}

	public int getOpponentState(Position pos){
		return opponentStates[pos.getY() - 1][pos.getX() - 1]; // x is column, y is row
	}
	

	/* ------------------------ Main actions ------------------------ */

	/**
	 * sets state of one position of opponent's grid
	 * 
	 * @param pos
	 *            the position to update
	 * @param state
	 *            the new state of the position
	 */
	protected void setOpponentState(Position pos, int state) {
		opponentStates[pos.getY() - 1][pos.getX() - 1] = state; // x is column, y is row
	}

	protected boolean canHit(Position pos){
		return pos != null && getOpponentState(pos) == BLANK;
	}
	
	/**
	 * responds to a hit by opponent
	 * 
	 * @param pos
	 *            the position opponent is trying to hit
	 * @return whether hit was successful
	 * @throws ShipSunkException
	 *             if ship was sunk
	 */
	public boolean respondHit(Position pos) throws ShipSunkException {
		for(Ship ship: fleet){
			if(ship.hit(pos)){
				if(ship.isSunk()){
					for (Position shipPos : ship.positions()) {
						setPlayerState(shipPos, SUNK);
					}
					throw new ShipSunkException(ship);
				}
				setPlayerState(pos, HIT);
				return true;
			}
		}
		setPlayerState(pos, MISS);
		return false;
	}

	/**
	 * 
	 * @return whether player has lost; that is, whether all of the player's
	 *         ships have been sunk
	 */
	public boolean hasLost() {
		for (Ship ship : fleet) {
			if (!ship.isSunk()) {
				return false;
			}
		}
		return true;
	}
	
	
	/* ------------------------ Used for debugging ------------------------ */

	/**
	 * converts player to string: lists ships
	 */
	public String toString(){
		String string = "player:\n";
		for(Ship ship: fleet){
			string += ship + "\n";
		}
		return string;
	}

	/**
	 * Prints matrix of states. Used for debugging.
	 * <p>
	 * '-': blank; '?': miss; 'x': hit; '!': sunk
	 */
	public void printOpponentStates() {

		System.out.print(" ");
		for (int i = 1; i <= 10; i++) {
			System.out.print(" " + (i % 10)); // upper x-values
		}
		System.out.println();

		for (int i = 0; i < opponentStates.length; i++) {
			System.out.print((i + 1) % 10); // leftmost y-values
			for (int j = 0; j < opponentStates.length; j++) {
				// states of each position
				System.out.print(" " + intStateToChar(opponentStates[i][j]));
			}
			System.out.println();
		}

	}

	/**
	 * Prints player's states, including unhit ships (denoted s), in a grid.
	 * Used for debugging.
	 * <p>
	 * '-': blank; '?': miss; 'o': unhit ship; 'x': hit; '!': sunk
	 */
	public void printPlayerStates() {

		System.out.print(" ");
		for (int i = 1; i <= 10; i++) {
			System.out.print(" " + (i % 10)); // upper x-values
		}
		System.out.println();

		for (int i = 0; i < Position.MAX_COORDINATE; i++) {
			System.out.print((i + 1) % 10); // leftmost y-values

			gridLoop: for (int j = 0; j < Position.MAX_COORDINATE; j++) {

				if (getPlayerState(new Position(j + 1, i + 1)) == 0) {
					for (Ship ship : fleet) { // iterate over ships
						if (ship != null && ship.contains(new Position(j + 1, i + 1))) {
							System.out.print(" o"); // unhit ship
							continue gridLoop;
						}
					}
					System.out.print(" -"); // no ship here
				} else {
					// states of each position
					System.out.print(" " +
							intStateToChar(getPlayerState(new Position(j + 1, i + 1))));
				}

			}
			System.out.println();
		}

	}
	
	// changes integer states into more recognizable symbols (used in debugging)
	private char intStateToChar(int state){
		switch(state){
		case BLANK:
			return '-';
		case HIT:
			return 'x';
		case MISS:
			return '?';
		case SUNK:
			return '!';
		default:
			throw new IllegalArgumentException("invalid state");
		}
	}

}
