package randomsimulations;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;

public class MovingDot {

	// location
	private int x;
	private int y;
	
	private int size;

	// color (default black)
	private Color color = Color.BLACK;

	private Random rand = new Random();

	public MovingDot(JFrame frame) {
		x = rand.nextInt(frame.getWidth());
		y = rand.nextInt(frame.getHeight());
	}

	public MovingDot(int initialX, int initialY) {
		x = initialX;
		y = initialY;
		size = 4;
	}

	public MovingDot(int initialX, int initialY, int dotSize) {
		x = initialX;
		y = initialY;
		size = dotSize;
	}
	
	public MovingDot(int initialX, int initialY, Color col) {
		x = initialX;
		y = initialY;
		color = col;
		size = 4;
	}
	
	public MovingDot(int initialX, int initialY, int dotSize, Color col) {
		x = initialX;
		y = initialY;
		color = col;
		size = dotSize;
	}

	public void draw(Graphics g) {

		Color oldColor = g.getColor();
		g.setColor(color);
		g.fillOval(x, y, size, size);
		g.setColor(oldColor);

	}

	/**
	 * Moves the dot
	 * 
	 * @param direction
	 *            the direction in degrees to move, with right as 0 degrees and
	 *            down as 90 degrees
	 * @param distance
	 *            the distance in pixels to move
	 */
	public void move(double direction, double distance) {
		x = x + (int) (Math.cos(direction) * distance);
		y = y + (int) (Math.sin(direction) * distance);
	}

	/**
	 * Moves the dot a random distance in a random direction
	 * 
	 * @param maxDistance
	 *            the maximum distance in pixels the dot can move
	 */
	public void randMove(double maxDistance) {
		move(rand.nextInt(360), rand.nextDouble() * maxDistance);
	}

	/**
	 * Randomly move one pixel right, down, left, or up
	 */
	public void randMove() {
		int direction = rand.nextInt(4);
		switch (direction) {
		case 0:
			x++;
			break;
		case 1:
			y++;
			break;
		case 2:
			x--;
			break;
		case 3:
			y--;
		}
	}

	public MovingDot movedDot() {
		int direction = rand.nextInt(4);
		switch (direction) {
		case 0:
			return new MovingDot(x + 1, y);
		case 1:
			return new MovingDot(x, y + 1);
		case 2:
			return new MovingDot(x - 1, y);
		case 3:
			return new MovingDot(x, y - 1);
		default:
			return null;
		}
	}
	
}
