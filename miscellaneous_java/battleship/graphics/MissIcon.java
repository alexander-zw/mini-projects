package graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

public class MissIcon implements Icon {
	
	private int diameter;
	
	public MissIcon(int diameter){
		this.diameter = diameter;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Color oldColor = g.getColor();
		g.setColor(Color.DARK_GRAY);
		g.fillOval(x, y, diameter, diameter);
		g.setColor(oldColor);
	}

	@Override
	public int getIconWidth() {
		return diameter;
	}

	@Override
	public int getIconHeight() {
		return diameter;
	}

}
