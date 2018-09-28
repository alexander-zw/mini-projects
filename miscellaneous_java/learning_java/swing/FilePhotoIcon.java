package swing;

import java.awt.BorderLayout;
import javax.swing.*;

@SuppressWarnings("serial")
public class FilePhotoIcon extends JFrame{
		public FilePhotoIcon(){
			super();
			setTitle("Picture");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JTextArea text = new JTextArea();	//create text area for text
			text.setText("Please enjoy the pretty picture:");
			getContentPane().add(text, BorderLayout.NORTH);	//at the top of window
			//the picture
			Icon icon = new ImageIcon("/Users/AlexanderWu/Documents/Photos/Cool stuff/cat'.jpg");
			JLabel iconLabel = new JLabel();	//label for the picture
			iconLabel.setIcon(icon);
			//to have space for both the picture and text area
			setBounds(100,100,icon.getIconWidth(),icon.getIconHeight() + 37);
			getContentPane().add(iconLabel, BorderLayout.CENTER);
//			setResizable(false);
			
		}
	public static void main(String[] args) {
		FilePhotoIcon frame = new FilePhotoIcon();
		frame.setVisible(true);

	}

}
