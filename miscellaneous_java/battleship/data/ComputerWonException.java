package data;

import program.Computer;

public class ComputerWonException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3477605644514823874L;
	private Computer winner;

	public Computer getWinner() {
		return winner;
	}

	public ComputerWonException(Computer winner) {
		this.winner = winner;
	}

	public ComputerWonException(String message, Computer winner) {
		super(message);
		this.winner = winner;
	}

}
