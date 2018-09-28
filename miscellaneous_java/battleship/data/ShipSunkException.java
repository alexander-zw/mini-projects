package data;

public class ShipSunkException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private Ship shipSunk;
	
	public ShipSunkException(Ship shipSunk){
		this.shipSunk = shipSunk;
	}

	public Ship getShipSunk(){
		return shipSunk;
	}
	
}
