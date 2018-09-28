package tictactoe;

import java.util.Random;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUIWindow extends JFrame {
	
	private static final long serialVersionUID = -5343746723785860111L;
	
	private TTTBoard board = new TTTBoard();
	private int player;
	
	private JLabel[][] grid = new JLabel[3][3];
	private JLabel turn = new JLabel();
	private JButton place = new JButton("Place");
	private JLabel rowL = new JLabel("Row:");
	private JLabel columnL = new JLabel("Column:");
	private JTextArea row = new JTextArea();
	private JTextArea column = new JTextArea();
	
	public GUIWindow(){
		try {
			JPanel boardPanel = new JPanel(new GridLayout(3, 3));
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					grid[i][j] = new JLabel(" ");
					boardPanel.add(grid[i][j]);
				}
			}
			JPanel placePanel = new JPanel(new GridLayout(1, 5));
			placePanel.add(rowL);
			placePanel.add(row);
			placePanel.add(columnL);
			placePanel.add(column);
			placePanel.add(place);
			
			Container cont = getContentPane();
			cont.add(turn, BorderLayout.NORTH);
			cont.add(boardPanel, BorderLayout.CENTER);
			cont.add(placePanel, BorderLayout.SOUTH);
			
			player = randPlayer();
			turn.setText("Player " + player + "\'s turn");
			place.addActionListener(new ActionListener(){
				
				boolean gameEnded = false;

				@Override
				public void actionPerformed(ActionEvent e) {
					if (!gameEnded) {
						try {
							int rowNum = Integer.parseInt(row.getText());
							int colNum = Integer.parseInt(column.getText());

							board.place(player, rowNum, colNum); //update database

							//update GUI window
							grid[rowNum - 1][colNum - 1].setText(symbol(player) + "");

							row.setText("");
							column.setText("");
							player = nextPlayer(player);
							turn.setText("Player " + player + "\'s turn");

							if (board.winner() != 0) {
								int selection = JOptionPane.showConfirmDialog(null,
										"Player " + board.winner() + " wins! Play again?");
								if (selection == JOptionPane.YES_OPTION) {
									board.reset();
									for (int i = 0; i < grid.length; i++) {
										for (int j = 0; j < grid[i].length; j++) {
											grid[i][j].setText(" ");
										}
									}
								}else
									gameEnded = true;
								return;
							}
							if (board.full()) {
								int selection = JOptionPane.showConfirmDialog(null,
										"It's a tie! Play again?");
								if (selection == JOptionPane.YES_OPTION) {
									board.reset();
									for (int i = 0; i < grid.length; i++) {
										for (int j = 0; j < grid[i].length; j++) {
											grid[i][j].setText(" ");
										}
									}
								}else
									gameEnded = true;
							}

						} catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(null, "Invalid grid");
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, e2.getMessage());
						} 
					}
				}
				
			});
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	//determines the player's symbol
	private String symbol(int player){
		switch(player){
		case 1:
			return "X";
		case 2:
			return "O";
		default:
			throw new IllegalArgumentException("Invalid player");
		}
	}
	
	private int randPlayer(){
		Random rand = new Random();
		return rand.nextInt(2) + 1;
	}
	
	private int nextPlayer(int player){
		switch(player){
		case 1:
			return 2;
		case 2:
			return 1;
		default:
			throw new IllegalArgumentException("Invalid player");
		}
	}
	
}
