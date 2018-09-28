package program;

import data.*;

/**
 * Contains actions of the human player in battleship and stores data
 * of their ships and state of opponent's grid
 * @author AlexanderWu
 *
 */
public class Human extends Player {
	
	/**
	 * Adds specified ship into user grid
	 * 
	 * @param type
	 *            the type of ship using constants
	 * @param pos
	 *            position to put ship
	 * @param horizontal
	 *            whether the ship is oriented horizontally
	 * @param canModifyExisting
	 * 			  whether we allow existing ships to be replaced
	 * @throws IllegalArgumentException
	 *             if {@code type} is invalid (ship already added)
	 * @throws GridOccupiedException
	 *             if some grids already contain part of a ship
	 */
	public void addShip(int type, Position pos, boolean horizontal, boolean canModifyExisting)
			throws IllegalArgumentException, GridOccupiedException {

		if ((fleet[type] == null || canModifyExisting)) { // if ship is not already added or allow modify
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

	/**
	 * delivers a hit to specified position of opponent's grid
	 * 
	 * @param pos
	 *            the position to hit
	 * @return whether a ship was hit
	 * @throws ShipSunkException
	 *             if ship is sunk
	 * @throws PositionAlreadyHitException
	 *             if the position is already hit
	 */
	public boolean hit(Player opponent, Position pos) throws ShipSunkException, PositionAlreadyHitException {

		try {
			
			// if already hit, throw exception
			if (!canHit(pos)) {
				throw new PositionAlreadyHitException();
			}

			// if not sunk, set hit or miss
			if (opponent.respondHit(pos)) {
				setOpponentState(pos, Player.HIT);
				return true;
			}
			setOpponentState(pos, Player.MISS);

		} catch (ShipSunkException e) {

			for (Position sunkPos : e.getShipSunk().positions()) {
				setOpponentState(sunkPos, Player.SUNK);
			}

			throw e;

		}

		return false;

	}

}
