package window;

import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JFrame;
import data.Position;

/**
 * Window containing grid
 * 
 * @author AlexanderWu
 *
 */
public class GUIWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	Grid grid = new Grid(new GridLayout(Position.MAX_COORDINATE, Position.MAX_COORDINATE, 2, 2));
	
	/**
	 * creates a window for battleship game
	 */
	public GUIWindow() {
		Container cont = getContentPane();
		cont.add(grid);
	}

}
