package edu.yale.cpsc112_lesson1;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class Logic {

	private static String path="C:/CPSC112_Project:/ListOfRecipes.txt";

	public static void main(String[] args) {
		
		try {
			System.out.print(readListOfRecipes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String readListOfRecipes () throws IOException {

		try{
			//FileReader file = new FileReader(file_name);
			String [] arrayLines = openFile();

			for ( int i=0; i < arrayLines.length; i++ ) {
				System.out.println( arrayLines[ i ] ) ;
				//can get rid of this part
			}
			//arrayLines = the text file that contains all of the lines in the recipes file

		}
		catch (IOException e){
			System.out.println( e.getMessage() );
		}
		
		return null;

	}
	public static String[] openFile() throws IOException{
		
		int numberOfLines = readLines();

		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);
		
		String[] textData = new String[numberOfLines];
		
		for (int i=0; i < numberOfLines; i++) {
			textData[ i ] = textReader.readLine();
		}
		
		textReader.close( );
		return textData;
		
	}
		
	public static String readFile(String file_path){
		return path = file_path;
	}
	
	public static int readLines() throws IOException{
		FileReader file_to_read = new FileReader(path);
		BufferedReader bf = new BufferedReader(file_to_read);
		
		String aLine;
		int numberOfLines =0;
		
		while ((aLine = bf.readLine()) != null) {
			numberOfLines++;
		}
		bf.close();
		return numberOfLines;
	}
}
