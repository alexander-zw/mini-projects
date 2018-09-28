package randomsimulations;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
//import java.util.ArrayList;
//import java.util.List;

import javax.swing.JPanel;

public class ColorPanel extends JPanel {

	private static final long serialVersionUID = 3925583317972540398L;

//	private List<MovingDot> dots;
	private MovingDot dot;

	private javax.swing.Timer timer;

	public ColorPanel(Color backColor) {

		setBackground(backColor);
//		dots = new ArrayList<MovingDot>();
//		dots.add(new MovingDot(250, 200));
		dot = new MovingDot(250, 200);

		addMouseListener(new PanelListener());
		timer = new javax.swing.Timer(10, new MoveListener());
		timer.start();

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
//		for (MovingDot d : dots) {
//			d.draw(g);
//		}
		dot.draw(g);
	}

	private class PanelListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {

			// start or stop timer
			if (timer.isRunning()) {
				timer.stop();
			} else {
				timer.start();
			}
			
		}
	}

	private class MoveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// move the last dot in list and add to list
//			dots.add(dots.get(dots.size() - 1).movedDot());
			dot.randMove(5);
			repaint();
		}
	}
}
