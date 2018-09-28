package window;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;

import data.Position;


/**
 * Used to create a visual grid with states of positions
 * @author AlexanderWu
 *
 */
public class Grid extends JPanel {

	private static final long serialVersionUID = 8427228066745649989L;

	private JButton[][] grid = new JButton[Position.MAX_COORDINATE][Position.MAX_COORDINATE];

	/**
	 * 
	 * @param layout
	 *            a {@code GridLayout} of {@code Position.MAX_COORDINATE} times
	 *            {@code Position.MAX_COORDINATE
	 */
	public Grid(GridLayout layout) {

		super(layout);

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = new JButton();
				grid[i][j].setOpaque(true);
				grid[i][j].setBackground((i + j) % 2 == 0 ? Color.BLUE : Color.CYAN);
				grid[i][j].setForeground(getBackground());
				add(grid[i][j]);
			}
		}

	}
	
}
