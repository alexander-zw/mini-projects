package program;

import java.util.HashSet;
import java.util.Set;

import data.*;

public class TestProgram {

	public static void main(String[] args) {

		try {

			testComputer();
			

//			Set<Position> justHit = new HashSet<Position>();
//			Position p = new Position(2, 3);
			
//			justHit.add(p);
//			justHit.add(new Position(2, 2));
//			System.out.println(justHit.remove(p));
//			System.out.println(justHit.remove(new Position(2, 2)));
//			for(Position r : justHit){
//				System.out.println(r);
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unused")
	private static Position[] hitPos = { new Position(1, 6), new Position(1, 8),
			new Position(2, 6), new Position(5, 1), new Position(10, 10), new Position(9, 10),
			new Position(3, 5) };
	
	@SuppressWarnings("unused")
	private static void testShip() {

		Position[] shipPos = { new Position(4, 6), new Position(1, 10), new Position(9, 10) };

		Ship[] ships = { new Ship(Ship.CARRIER, shipPos[0], false),
				new Ship(Ship.BATTLESHIP, shipPos[1], true),
				new Ship(Ship.PATROL_BOAT, shipPos[2], true) };

		// hit ships at designated positions
//		for (int i = 0; i < hitPos.length; i++) {
//			for (int j = 0; j < ships.length; j++) {
//				ships[j].hit(hitPos[i]);
//			}
//		}

		// print ships
		for (int i = 0; i < ships.length; i++) {
			System.out.println(ships[i] + " sunk: " + ships[i].isSunk());
		}
		
		System.out.println(ships[0].intersects(ships[1]));
		System.out.println(ships[0].touches(ships[1]));

	}

//	@SuppressWarnings("unused")
	private static void testComputer() {
		Computer computerHitting = new Computer(true, false); // hits
		Computer computerHit = new Computer(false, false); // gets hit
		
		// add specified ships for computerHit
				try {
					for (int i = 0; i < 5; i++) {
						computerHit.debugAddShip(i, shipPositions[i], shipHorizontals[i]);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		System.out.println("Ships added\n");
		
		try {
			computerHitting.debugHit(computerHit, new Position(2, 2)); // hit at (2, 2)
		} catch (ShipSunkException e1) {
			System.out.println(e1.getShipSunk() + " is sunk!");
		} catch (PositionAlreadyHitException e1) {
			e1.printStackTrace();
		}
		
		for (int i = 0; i < 4; i++) {
			try {
				computerHitting.hit(computerHit);
			} catch (ShipSunkException e) {
				System.out.println(e.getShipSunk() + " is sunk!");
			}
			
			computerHit.printPlayerStates();
			computerHitting.printJustHit();
			System.out.println();

		}

	}
	
//	@SuppressWarnings("unused")
	private static Position[] shipPositions = { new Position(5, 5), new Position(3, 6),
			new Position(2, 2), new Position(8, 7), new Position(6, 3) };
	
//	@SuppressWarnings("unused")
	private static boolean[] shipHorizontals = { true, false, false, true, true };
	
//	@SuppressWarnings("unused")
	private static Position[] hitPos2 = { new Position(2, 2), new Position(2, 3),
			new Position(2, 4), new Position(1, 6), new Position(1, 8),
			new Position(2, 6), new Position(5, 1), new Position(10, 10), new Position(9, 10),
			new Position(3, 5), new Position(3, 5) };
	
	@SuppressWarnings("unused")
	private static void testHuman(){
		
		Human humanHitting = new Human(); // hits
		Human humanHit = new Human(); // gets hit
		
		// add ships for humanHit
		try {
			for (int i = 0; i < 5; i++) {
				humanHit.addShip(i, shipPositions[i], shipHorizontals[i], false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Ships added\n");
		
			for (Position pos : hitPos2) {
				try {
					humanHitting.hit(humanHit, pos);
				} catch (ShipSunkException e) {
					System.out.println(e.getShipSunk() + " is sunk!\n");
				} catch (PositionAlreadyHitException e) {
					System.out.println("Already tried here! " + pos + "\n");
				}
			}
		
		humanHit.printPlayerStates();

	}

//	@SuppressWarnings("unused")
	public static void testPosition() {

		Position p1 = new Position(1, 1);
		Position p2 = new Position(2, 1);
		Position p3 = new Position(10, 9);
		Position p4 = new Position(10, 8);
		Position p5 = new Position(10, 7);

		Set<Position> ps = new HashSet<Position>();
		ps.add(p2);
		ps.add(p3);
		ps.add(p4);
		ps.add(p5);

		System.out.println(p1.isNearPosIn(ps, Position.LEFT_DIR));

	}

}
