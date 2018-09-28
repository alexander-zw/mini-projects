package io;

import java.io.File;
import java.io.FileOutputStream;

public class UsingFileOutputStream {
	public static void main(String[] args){
		File file = new File("/Users/AlexanderWu/Documents/Other/Java & HTML/Adjectives/del.txt");
		if(file.exists()){
			file.delete();
		}
		try{
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			byte[] byt = "I love you Java!!!!!!!".getBytes();
			fos.write(byt);
			fos.close();
			System.out.println("Success!");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
