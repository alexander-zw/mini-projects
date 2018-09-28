package io;

import java.io.File;
import java.io.FileReader;

public class EncryptionWithExor {
	public static void main(String[] args) {
		File file = new File("/Users/AlexanderWu/Documents/Other/Java & HTML/encryption.txt");
//		if(!file.exists()){	//in case the file doesn't exist, create new file
//			try {
//				file.createNewFile();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		
		try {
			FileReader fReader = new FileReader(file);
			char[] charArr = new char[1024];
			int length = fReader.read(charArr);
			fReader.close();
			
			System.out.print("Encrypted message: ");
			for(int i = 0; i < length; i++){
				charArr[i] = (char) (charArr[i] ^ (14 + i % 37));	//encryption method 1
//				charArr[i] = (char) (~charArr[i] ^ (14 + i % 37));	//encryption method 2
				System.out.print((char)charArr[i]);	//the encrypted message
			}
			
			System.out.print("\nDecrypted message: ");
			for(int i = 0; i < length; i++){
				charArr[i] = (char) (charArr[i] ^ (14 + i % 37));	//decryption method 1
//				charArr[i] = (char) (~charArr[i] ^ (14 + i % 37));	//decryption method 2
				System.out.print((char)charArr[i]);	//the decrypted message
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
