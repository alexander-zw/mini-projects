package swing;

import java.awt.Container;
import java.awt.Graphics;

import javax.swing.*;

@SuppressWarnings("serial")
public class MüllerLyerIllusion extends JPanel{
	
	public void paintComponent(Graphics graph){	//paints two arrowed lines
		super.paintComponent(graph);
		graph.drawLine(100, 50, 300, 50);	//first horizontal line
		//arrows
		graph.drawLine(100, 50, 130, 30);
		graph.drawLine(100, 50, 130, 70);
		graph.drawLine(270, 30, 300, 50);
		graph.drawLine(270, 70, 300, 50);
		
		graph.drawLine(100, 150, 300, 150);	//second horizontal line
		//arrows
		graph.drawLine(100, 150, 70, 130);
		graph.drawLine(100, 150, 70, 170);
		graph.drawLine(330, 130, 300, 150);
		graph.drawLine(330, 170, 300, 150);
			
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("The M\u00FCller-Lyer Illusion");
		frame.setBounds(350, 250, 400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MüllerLyerIllusion panel = new MüllerLyerIllusion(); //automatically draws lines
		Container pane = frame.getContentPane();
		pane.add(panel);
		
		frame.setVisible(true);

	}

}
