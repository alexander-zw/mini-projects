package media;

import sounds.APSoundClip;

@SuppressWarnings("restriction")
public class APRecordingMoney {
	
	public static void main(String[] args) {
		APSoundClip clip = new APSoundClip("/Users/AlexanderWu/Documents/Java/Toolkits/"
				+ "sounds/Test Programs/money.wav");
		clip.draw();

	}

}
