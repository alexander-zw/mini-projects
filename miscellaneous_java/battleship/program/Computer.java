package program;

import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import data.*;

/**
 * Contains actions of the computer player in battleship and stores data for its
 * ships and state of opponent's grid
 * 
 * @author AlexanderWu
 *
 */
public class Computer extends Player {

	// if true, computer will deliver hits at nearby positions after an opponent's a ship is hit
	private boolean smart;
	// if false, computer will only add ships that are not touching
	private boolean allowTouch;
	
	// the position last hit by computer
	private Position positionLastHit;
	// positions corresponding to unsunk hit ships
	private Set<Position> justHit = new HashSet<Position>();

	/* ------------------------ Constructor ------------------------ */

	/**
	 * @param allowTouch
	 *            if the computer will allow ships that occupy adjacent grids
	 *            
	 * @param smartHit
	 *            if true, computer will deliver hits at nearby positions when a
	 *            ship is hit (but not sunk)
	 */
	public Computer(boolean smartHit, boolean allowTouch) {
		super();
		smart = smartHit;
		this.allowTouch = allowTouch;
	}

	/* ------------------------ Main actions ------------------------ */

	/**
	 * adds random ships to computer's grid
	 */
	public void addShips() {

		Random rand = new Random();

		for (int i = 0; i < fleet.length; i++) { // add one ship of each type
			outerWhile:
			while (true) { // loop until no error

				try {
					
					Position pos = new Position(); // random position
					// add ship at random positions with random orientations
					fleet[i] = new Ship(i, pos, rand.nextBoolean());
					
					for(int j = 0; j < i; j++){
						//check if grid is already occupied by another ship
						//if not allow touch, also check if grid is adjacent to another ship
						if(fleet[i].intersects(fleet[j]) ||
								(!allowTouch && fleet[i].touches(fleet[j]))){
							continue outerWhile;
						}
					}
					
				} catch (IllegalArgumentException e) {
					continue; // don't break if error occurs
				}
				
				break; // break if no error

			}
		}
		
	}

	
	/**
	 * delivers random or calculated hit to opponent's grid
	 * 
	 * @param opponent
	 *            the player whose fleet is to hit
	 * @param smartHit
	 *            if true, computer will hit adjacent grids after a first
	 *            successful hit, and after second successful hit, computer will
	 *            continue linearly until ship is sunk; if false, computer will
	 *            hit randomly
	 * @return whether a ship was hit
	 * @throws ShipSunkException
	 *             if a ship is sunk
	 */
	public boolean hit(Player opponent) throws ShipSunkException {
		
		Position pos;	//position to hit
		
		while (true) {	//loop until position has not been already hit
			
			if (smart) {
				pos = smartHit();
			} else {
				pos = new Position(); // fire random hit
			}
			try {
				
				if (!canHit(pos)) {
					continue; // don't break if position is already hit
				}
				
				positionLastHit = pos;

				// if not sunk, set hit or miss
				if (opponent.respondHit(pos)) {
					setOpponentState(pos, Player.HIT);
					justHit.add(pos); // keep track for smartHit
					return true;
				}
				setOpponentState(pos, Player.MISS);

			} catch (ShipSunkException e) {
				
				for (Position sunkPos : e.getShipSunk().positions()) {
					
					// this algorithm is redundant, but my method of using justHit.remove(sunkPos)
					// somehow doesn't work
					Position commonPos = null; // position both in positions of ship sunk and justHit
					for (Position hitPos : justHit) {
						if (hitPos.equals(sunkPos)) {
							// changes sunkPos so that the pointer is the same as that in justHit
							commonPos = hitPos;
							break;
						}
					}
					
					setOpponentState(sunkPos, Player.SUNK); // update opponentState
					justHit.remove(commonPos); // notify smartHit that this position's ship is sunk
				}
				
				throw e;
				
			}
			
			break;	//break if position is valid
		}
		
		return false;
		
	}
	
	public Position getPositionLastHit() {
		return positionLastHit;
	}
	
	// returns position to hit according to smart hit rules
	private Position smartHit(){
		
		if(justHit.isEmpty()){ // if there are no unsunk hit ships
			return new Position(); // fire random hit
		}
		
		Iterator<Position> hitIter = justHit.iterator();
		
		Position pos1; // a position just hit

		do {
			pos1 = hitIter.next();
			
			for (int i = 1; i <= 4; i++) {
				// if pos1 is to the direction of i of another that is hit
				if (pos1.isNearPosIn(justHit, i)
						// and the position in this direction of pos1 (i) is not already hit (or missed)
						&& canHit(pos1.posNear(i))) {

					// hit opposite direction
					return pos1.posNear(i);
				}
			}
		} while (hitIter.hasNext());
		
		return pos1.posNear(Position.randDirection());
		
	}

	/* ------------------------ Used for debugging ------------------------ */

	@Override
	public String toString() {
		return "computer " + super.toString();
	}

	// debug: add ships in specified positions
	public void debugAddShip(int type, Position pos, boolean horizontal)
			throws IllegalArgumentException, GridOccupiedException {

		if (fleet[type] == null) { // if ship is not already added
			fleet[type] = new Ship(type, pos, horizontal); // add ship
		} else {
			throw new IllegalArgumentException("ship already added");
		}

		for(Ship ship : fleet){
			// if a ship has already been added intersects the newly added ship
			if(ship != null && ship != fleet[type] && ship.intersects(fleet[type])){
				fleet[type] = null; // remove newly added ship
				throw new GridOccupiedException();
			}
		}

	}

	// debug: hit specified position
	public boolean debugHit(Player opponent, Position pos)
			throws ShipSunkException, PositionAlreadyHitException {

		try {
			
			// if already hit, throw exception
			if (!canHit(pos)) {
				throw new PositionAlreadyHitException();
			}

			// if not sunk, set hit or miss
			if (opponent.respondHit(pos)) {
				setOpponentState(pos, Player.HIT);
				justHit.add(pos);
				return true;
			}
			setOpponentState(pos, Player.MISS);

		} catch (ShipSunkException e) {

			for (Position sunkPos : e.getShipSunk().positions()) {
				setOpponentState(sunkPos, Player.SUNK);
				justHit.remove(pos); // notify smartHit that this position's ship is sunk
			}

			throw e;

		}

		return false;

	}
	
	// used for debugging
	public void printJustHit(){
		System.out.print("Just hit: ");
		for(Position pos: justHit){
			System.out.print(pos);
		}
		System.out.println();
	}
	
}
