package swing;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class ColorWheel extends JPanel{

	public ColorWheel(Color aColor){	//constructor sets background color as aColor
		setBackground(aColor);
		
	}
	
	public void paintComponent(Graphics graph){	//paints a colorful oval in middle
		super.paintComponent(graph);
		for(int i = 1; i < 121; i++){
			drawFilledArc(graph, i);
		}
		
	}
	
	public void drawFilledArc(Graphics graph, int i){	//paints 3 degrees of an oval
		int x = getWidth() / 2 - 150;	//x as coordinate of upper corner of oval
		int y = getHeight() / 2 - 100;	//y as coordinate of upper corner of oval
		Color arcColor = Color.red;	//color of portion of the color wheel
		//default color is red; only last 3 degrees use default color
//		switch(i){	//designates color to arcColor according to value of i
		//i can only use 6 values
//			case 1:
//				arcColor = Color.red;
//				break;
//			case 2:
//				arcColor = Color.orange;
//				break;
//			case 3:
//				arcColor = Color.yellow;
//				break;
//			case 4:
//				arcColor = Color.green;
//				break;
//			case 5:
//				arcColor = Color.cyan;
//				break;
//			case 6:
//				arcColor = Color.blue;
//				
//		}
		if(i < 40){	//first 120 degrees
			//from red to green
			arcColor = new Color((int)(255 - i * 6.5 + .5), (int)(i * 6.5 + .5), 0);
		} else if(i < 80){	//middle 120 degrees
			//from green to blue
			arcColor = new Color(0, (int)(255 - (i - 40) * 6.5 + .5), (int)((i - 40) * 6.5 + .5));
		} else if (i < 120){	//last 120 degrees, not including final 3 degrees
			//from blue to red
			arcColor = new Color((int)((i - 80) * 6.5 + .5), 0, (int)(255 - (i - 80) * 6.5 + .5));
		}
		graph.setColor(arcColor);
		graph.fillArc(x, y, 300, 200, 93 - 3 * i, -3);	//draw next 3 degrees of arc
		
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Color Wheel");
		frame.setBounds(350, 250, 400, 247);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ColorWheel panel = new ColorWheel(new Color(200, 255, 250)); //set background color
		Container pane = frame.getContentPane();
		pane.add(panel);
		
		frame.setVisible(true);
		
	}

}
