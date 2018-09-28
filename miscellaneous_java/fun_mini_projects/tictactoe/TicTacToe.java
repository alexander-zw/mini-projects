package tictactoe;

import javax.swing.JFrame;

public class TicTacToe {

	public static void main(String[] args) {
		GUIWindow window = new GUIWindow();
		window.setTitle("TIC TAC TOE");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocation(450, 300);
		window.pack();
		window.setVisible(true);
		
	}

}
