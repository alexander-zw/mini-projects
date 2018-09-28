package graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.Icon;

public class HitIcon implements Icon {

	private int length;

	public HitIcon(int length){
		this.length = length;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Color oldColor = g.getColor();
		g.setColor(Color.RED);
		g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, length));
		g.drawString("x", x, y);
		g.setColor(oldColor);

	}

	@Override
	public int getIconWidth() {
		return length;
	}

	@Override
	public int getIconHeight() {
		return length;
	}

}
