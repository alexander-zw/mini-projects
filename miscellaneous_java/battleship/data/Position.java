package data;

import java.util.Random;
import java.util.Set;

/**
 * Represents a position in a 10 by 10 grid, used for battleship. Position
 * objects are immutable.
 * 
 * @author AlexanderWu
 *
 */
public class Position {

	private int x;
	private int y;
	
	public static final int MAX_COORDINATE = 10;
	
	public static final int RIGHT_DIR = 1;
	public static final int DOWN_DIR = 2;
	public static final int LEFT_DIR = 3;
	public static final int UP_DIR = 4;

	/* ------------------------ Constructors ------------------------ */

	public Position(int xPos, int yPos) throws IllegalArgumentException {
		
		// check limits
		if (xPos < 1 || xPos > MAX_COORDINATE || yPos < 1 || yPos > MAX_COORDINATE) {
			throw new IllegalArgumentException("coordinates must be 1 to 10");
		}
		x = xPos;
		y = yPos;

	}

	/**
	 * creates random position
	 */
	public Position() {
			Random rand = new Random();
			x = rand.nextInt(9) + 1; // 1 to 10
			y = rand.nextInt(9) + 1;
	}

	/* ------------------------ Gets and sets ------------------------ */

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	/* ------------------------ Main actions ------------------------ */

	public boolean equals(Position other) {
		if(other == null){
			return false;
		}
		return x == other.getX() && y == other.getY();
	}

	/**
	 * @param other
	 * @return whether two grids are adjacent horizontally or vertically
	 *         ("touching)"
	 */
	public boolean touches(Position other) {
		return equals(other.posNear(RIGHT_DIR)) || equals(other.posNear(DOWN_DIR)) ||
				equals(other.posNear(LEFT_DIR)) || equals(other.posNear(UP_DIR));
	}
	
	// *****************************************NOT FINISHED!
//	public int direction(Position pos){
//		return 0;
//	}

	/**
	 * @param direction
	 *            int representing the direction to move
	 * @return a new position moved 1 unit in the specified direction
	 * @throws IllegalArgumentException
	 *             if direction is not RIGHT_DIR, DOWN_DIR, LEFT_DIR, or UP_DIR
	 */
	public Position posNear(int direction) throws IllegalArgumentException {
		try {
			switch (direction) {
			case RIGHT_DIR:
				return new Position(getX() + 1, getY());
			case DOWN_DIR:
				return new Position(getX(), getY() + 1);
			case LEFT_DIR:
				return new Position(getX() - 1, getY());
			case UP_DIR:
				return new Position(getX(), getY() - 1);
			default:
				throw new IllegalArgumentException("invalid direction");
			}
			
		} catch (IllegalArgumentException e) {
			return null; // if there is no position in that direction (edge of grid)
		}
	}

	/**
	 * Here is an example: <blockquote>
	 * 
	 * <pre>
	 * others.add(new Position(2, 1));
	 * new Position(1, 1).isNearPosIn(others, Position.LEFT_DIR)
	 *     returns true
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param others
	 *            a set of positions
	 * @param direction
	 *            a direction
	 * @return whether the position is touching one of the positions in
	 *         {@code others}, where the position equals the position in
	 *         {@code others} shifted in the direction {@code direction}
	 */
	public boolean isNearPosIn(Set<Position> others, int direction) {
		for (Position other : others) {
			if (equals(other.posNear(direction))) {
				return true;
			}
		}
		return false;
	}
	
	public static int randDirection(){
		Random rand = new Random();
		return rand.nextInt(4) + 1;
	}
	
	public static int oppositeDirection(int direction){
		return (direction + 2) % 4;
	}

	/* ------------------------ Used for debugging ------------------------ */

	/**
	 * returns string representation: (x,y)
	 */
	public String toString() {
		return "(" + x + "," + y + ")";
	}

}
