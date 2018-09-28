package tictactoe;

/**
 * Created to test program. Not part of application.
 * @author AlexanderWu
 *
 */
public class TestBoard {
	
	public static void main(String[] args){
		TTTBoard board = new TTTBoard();
		for(int i = 1; i <= 3; i++)
			board.place(1, i, 2);
		System.out.print(board + "" + board.winner() + board.full());
		
	}
}
