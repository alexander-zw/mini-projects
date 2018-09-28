package data;

import program.Human;

public class HumanWonException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1128787567192688178L;
	private Human winner;

	public HumanWonException(Human winner) {
		this.winner = winner;
	}

	public HumanWonException(String message, Human winner) {
		super(message);
		this.winner = winner;
	}

	public Human getWinner() {
		return winner;
	}

}
