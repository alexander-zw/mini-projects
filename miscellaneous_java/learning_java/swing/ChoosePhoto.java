package swing;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import images.APImage;
import images.ImageFileFilter;

@SuppressWarnings("restriction")
public class ChoosePhoto {

	public static void main(String[] args) {
		
		JFileChooser chooser = new JFileChooser();
		
		//tells user to choose an image
		int option = JOptionPane.showOptionDialog(null, "Please choose an image.", "Select Image",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
		if(option == JOptionPane.CANCEL_OPTION){	//if user cancels
			return;
		}
		
		//only choose JPEG files
        chooser.setFileFilter(new ImageFileFilter(".jpg", "JPEG images (*.jpg)"));
		option = chooser.showOpenDialog(null);
		if(option == JFileChooser.CANCEL_OPTION){	//if user cancels
			return;
		}
		
		try{
			File file = chooser.getSelectedFile();
			APImage image = new APImage(file.getAbsolutePath());
			image.draw();
		}catch(Exception e){
			JOptionPane.showOptionDialog(null, e.toString(), "Uh-oh!",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
		}
		
	}

}
