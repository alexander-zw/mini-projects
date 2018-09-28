package window;

import javax.swing.JFrame;

public class Application {

	/**
	 * begins game
	 * @param args
	 */
	public static void main(String[] args){
		GUIWindow window = new GUIWindow();
		window.setTitle("BattleShip");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(450, 300, 350, 350);
		window.setVisible(true);
		
	}

}
