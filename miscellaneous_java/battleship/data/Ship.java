package data;

/**
 * A ship in battleship. Can be created, put in a specific location, hit, and
 * sunk. Once created, only the state of the ship can be changed (where it is
 * hit, whether it is sunk)
 * 
 * @author AlexanderWu
 *
 */
public class Ship {

	private Position position;
	private boolean horizontal;
	private int type;

	public Position getPosition() {
		return position;
	}

	public boolean isHorizontal() {
		return horizontal;
	}

	public int getType() {
		return type;
	}

	// returns string form of ship type
	public static String getStringType(int type) throws IllegalStateException {
		switch (type) {
		case 0:
			return "carrier";
		case 1:
			return "battleship";
		case 2:
			return "destroyer";
		case 3:
			return "submarine";
		case 4:
			return "patrol boat";
		default:
			throw new IllegalStateException("invalid ship type");
		}
	}

	private boolean[] hit;

	public static final int CARRIER = 0;
	public static final int BATTLESHIP = 1;
	public static final int DESTROYER = 2;
	public static final int SUBMARINE = 3;
	public static final int PATROL_BOAT = 4;

	public static final int[] SHIP_TYPES = { CARRIER, BATTLESHIP, DESTROYER, SUBMARINE, PATROL_BOAT };

	// constructor to create ship
	public Ship(int shipType, Position shipPosition, boolean isHorizontal)
			throws IllegalArgumentException {

		position = shipPosition;
		horizontal = isHorizontal;
		type = shipType;

		switch (type) {
		case CARRIER:
			hit = new boolean[5];
			break;
		case BATTLESHIP:
			hit = new boolean[4];
			break;
		case DESTROYER:
		case SUBMARINE:
			hit = new boolean[3];
			break;
		case PATROL_BOAT:
			hit = new boolean[2];
			break;
		default:
			throw new IllegalArgumentException("invalid ship type");
		}

		terminalPosition();

	}

	/**
	 * Determines if ship is hit and updates accordingly
	 * 
	 * @param pos
	 *            the position to test hit
	 * @return whether ship is hit
	 */
	public boolean hit(Position pos) {

		// loop from first position to last, checking if ship is at position
		// different for horizontal and vertical ships
		int shipPos1 = position.getX();
		int shipPos2 = position.getY();
		int hitPos1 = pos.getX();
		int hitPos2 = pos.getY();

		if (horizontal) { // first check if ship is horizontal or vertical
			// if horizontal, swap 1 and 2 for both ship and hit
			shipPos1 = shipPos1 ^ shipPos2;
			shipPos2 = shipPos1 ^ shipPos2;
			shipPos1 = shipPos1 ^ shipPos2;

			hitPos1 = hitPos1 ^ hitPos2;
			hitPos2 = hitPos1 ^ hitPos2;
			hitPos1 = hitPos1 ^ hitPos2;

		}

		// NOTE: to illustrate, the comments below assume the ship is horizontal
		if (shipPos1 == hitPos1) { // first check that hit and ship are at the
									// same row

			for (int i = 0; i < hit.length; i++) { // then check each column
				if (shipPos2 + i == hitPos2) {
					hit[i] = true;
					return true;
				}
			}

		}

		return false;

	}
	
	/**
	 * @param other
	 *            other ship
	 * @return whether two ships intersect
	 */
	public boolean intersects(Ship other) {
		
		Position[] positions = positions();
		Position[] otherPositions = other.positions();
		
		for (Position position: positions) {
			for (Position otherPosition: otherPositions) {
				if (position.equals(otherPosition)) {
					return true;
				}
			} 
		}
		
		return false;
		
	}

	/**
	 * @param other
	 *            other ship
	 * @return whether two ships occupy adjacent grids (or are "touching")
	 */
	public boolean touches(Ship other) {

		Position[] positions = positions();
		Position[] otherPositions = other.positions();

		for (Position position: positions) {
			for (Position otherPosition: otherPositions) {
				if (position.touches(otherPosition)) {
					return true;
				}
			} 
		}

		return false;
	}

	/**
	 * @return an array of the positions of ship
	 */
	public Position[] positions() {
		
		Position[] positions = new Position[hit.length];
		
		for (int i = 0; i < positions.length; i++) {
			if (horizontal) { // first check if ship is horizontal or vertical
				positions[i] = new Position(position.getX() + i, position.getY());
			}else{
				positions[i] = new Position(position.getX(), position.getY() + i);
			}
		}
		return positions;

	}

	/**
	 * @param pos
	 *            a position
	 * @return whether ship contains this position
	 */
	public boolean contains(Position pos) {
		for (Position shipPos : positions()) {
			if (shipPos.equals(pos)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return if ship is sunk
	 */
	public boolean isSunk() {
		for (boolean isHit : hit) {
			if (!isHit) {
				return false;
			}
		}
		return true;
	}

	/**
	 * returns string representation: ship type followed by hits (x for hit and
	 * o for unhit each representing a part of the ship) then followed by
	 * positions occupied
	 */
	public String toString() {

		String stringShip = "";
		for (boolean isHit : hit) {
			if (isHit) {
				stringShip += " x";
			} else {
				stringShip += " o";
			}
		}

		String positionsString = "; positions: " + position + "-" + terminalPosition();

		return getStringType(type) + stringShip + positionsString;
	}

	// returns position of ship's right- or bottom-most part
	// also checks if ship is completely in grid
	private Position terminalPosition() throws IllegalArgumentException {

		int terminalX = position.getX();
		int terminalY = position.getY();

		// check if ship is horizontal or vertical
		if (horizontal) {
			terminalX += hit.length - 1;
		} else {
			terminalY += hit.length - 1;
		}

		try {
			return new Position(terminalX, terminalY);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("ship out of grid");
		}

	}

}
