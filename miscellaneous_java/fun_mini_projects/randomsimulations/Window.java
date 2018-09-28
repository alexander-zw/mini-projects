package randomsimulations;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;

public class Window {

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.setTitle("Random Walk");
		frame.setBounds(500, 200, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ColorPanel panel = new ColorPanel(Color.WHITE);
		Container pane = frame.getContentPane();
		pane.add(panel);
		frame.setVisible(true);
		
	}

}
