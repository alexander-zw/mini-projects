package threads;

import java.io.File;
import java.io.FileReader;

import javax.swing.*;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.*;

@SuppressWarnings("serial")
public class PrintEncryptedFile extends JFrame{
	
	static long waitTime = 10;	//time to wait after printing each character
	static boolean whetherStop = false;	//whether to stop program
	protected static JTextArea text1;
	protected static JTextArea text2;
	
	public PrintEncryptedFile(){	//constructor creates window
		super();
		setTitle("The Real Universe, original and encrypted");
		//position window in middle of screen; size accommodates buttons on the bottom
		setBounds(200,100,800,543);
		setLayout(null);	//absolute layout
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);	//not allow change in window size
		
		text1 = new JTextArea();	//create text area for file text
		text2 = new JTextArea();	//create text area for encrypted text
		text1.setLineWrap(true);	//auto change lines
		text2.setLineWrap(true);
		JScrollPane pane1 = new JScrollPane(text1);
		JScrollPane pane2 = new JScrollPane(text2);
		
		//buttons to monitor speed and state of text area outputs
		JButton stopButton = new JButton("stop");	//button to stop printing in both text areas
		stopButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {	//when button is pressed
				whetherStop = true;	//stop program
				
			}
		});
		JButton startButton = new JButton("continue");
		startButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				whetherStop = false;	//no longer automatically stop threads
				
			}
		});
		JButton slowButton = new JButton("slow down");	//button to slow down print speed by half
		slowButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {	//when button is pressed
				if(waitTime < 1000){	//keeping wait time within 1 second
					waitTime *= (long)2;	//multiply wait time by 2
				}
				
			}
		});
		JButton defaultButton = new JButton("default");	//button to set print speed back to default
		defaultButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {	//when button is pressed
				waitTime = 10;	//set wait time back to 10
				
			}
		});
		JButton speedButton = new JButton("speed up");	//button to double print speed
		speedButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {	//when button is pressed
				if(waitTime > 1){	//keeping wait time more than 1ms
					waitTime /= (long)2;	//divide wait time by 2
				}
				
			}
		});
		
		//grid layout panel for text areas
		JPanel textPanel = new JPanel();
		textPanel.setBounds(0, 0, 800, 500);	//position panel on top
		textPanel.setLayout(new GridLayout(1,2));	//one row and two columns
		textPanel.add(pane1);
		textPanel.add(pane2);
		
		//grid layout panel for buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(150, 500, 500, 20);	//position panel on bottom middle
		buttonPanel.setLayout(new GridLayout(1,5));	//one row and five columns
		buttonPanel.add(stopButton);
		buttonPanel.add(startButton);
		buttonPanel.add(slowButton);
		buttonPanel.add(defaultButton);
		buttonPanel.add(speedButton);
		
		//adding components to window
		Container cont = getContentPane();
		cont.add(textPanel);
		cont.add(buttonPanel);
		
		setVisible(true);	//set window visible
		
	}
	
	public WriteThread getWriteThread(JTextArea text,boolean bool){	//method to get inner class
		return new WriteThread(text,bool);
	}
	
	class WriteThread extends Thread{	//thread for displaying file content
		boolean bool;	//whether to encrypt message
		JTextArea text;	//text area to write into
		
		//constructor with text area boolean parameters
		public WriteThread(JTextArea text,boolean bool){
			super();
			this.text = text;
			this.bool = bool;
		}
		
		@Override
		public void run(){
			while(true){
				try {
					//wait time milliseconds, write into text area text, encrypt if bool=true
					threadWrite(new Thread(this),text,bool);
					text.append("\n");
					Thread.sleep(1000);	//stop for 1s after each full print of article
					
				}catch(Exception e){
					e.printStackTrace();
					break;
				}
				
			}
		}
	}
	 
	//instructions for threads to display file content in text area text; if bool=true, encrypt
	public void threadWrite(Thread thread,JTextArea text,boolean bool) throws Exception{
		//file The Real Universe 4
		File file = new File(
			"/Users/AlexanderWu/Documents/Other/Java Projects/the real universe 4.txt"
		);
		FileReader reader = new FileReader(file);	//create character input stream
		char[] charArr = new char[40960];	//a capacity of 80KB, 40960 characters
		reader.read(charArr);
		reader.close();
		
		for(int i = 0; i < charArr.length; i++){	//iterate through file content
			if(whetherStop == true){	//after button is pressed
				Thread.sleep(100);	//wait 0.1s, simulating stop
				i--;	//prevent i from incrementing
				continue;	//bypass remaining code
			}
			
			if((int)charArr[i] == 0){	//when the character is null
				break;	//stop output
			}
			
			//encrypt if bool=true, use i as encrypting parameter
			charArr[i] = this.encrypt(charArr[i],i,bool);
			text.append(String.valueOf(charArr[i]));	//add character to text area
			Thread.sleep(waitTime);	//wait 0.002s
			
		}
		
	}
	
	//the method of encoding with a parameter i; if bool=true, encrypts character
	public char encrypt(char character,int i,boolean bool){
		if(bool){
			character = (char) (character ^ (14 + i % 37));	//encryption method
		}
		return character;
	}
	
	public static void main(String[] args) {
		PrintEncryptedFile frame = new PrintEncryptedFile();	//create object
		//create thread for displaying file content
		Thread writeThread1 = frame.getWriteThread(text1,false);
		//create thread for displaying encrypted message
		Thread writeThread2 = frame.getWriteThread(text2,true);
		
		writeThread1.start();
		writeThread2.start();
		
	}

}
