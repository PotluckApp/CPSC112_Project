package edu.yale.cpsc112_lesson1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

	private String path;

	public ReadFile(String file_path){
		//System.out.println("readfile works too");
		path = file_path;
	}
		
	public String[] OpenFile() throws IOException{
	//	System.out.println("does this get called?");
		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);
		
	/*	int numberOfLines = readLines();
		String[ ] textData = new String[numberOfLines];
		
		for (int i=0; i < numberOfLines; i++) {
			textData[ i ] = textReader.readLine();
			}
		
		textReader.close( );
		*/
		String[] textData = new String[1];
		textData[1] = textReader.readLine();
		return textData;
		
	}

	public int readLines() throws IOException{
		FileReader file_to_read = new FileReader(path);
		BufferedReader bf = new BufferedReader(file_to_read);
		
		String aLine;
		int numberOfLines =0;
		
		while ((aLine = bf.readLine()) != null) {
			numberOfLines++;
			System.out.println("hey");
		}
		bf.close();
		return numberOfLines;
	}
	

}
