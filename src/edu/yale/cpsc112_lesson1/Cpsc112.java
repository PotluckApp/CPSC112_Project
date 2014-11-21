package edu.yale.cpsc112_lesson1;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

	public class Cpsc112{

		public static String[] readListOfRecipes () throws IOException {
			String file_name = "C:/Users/nikitadutta/CPSC112_Project/ListOfRecipes.txt";
			//System.out.println("this method works");
			try{
				ReadFile file = new ReadFile(file_name);
				String[] arrayLines = (file.OpenFile());
				int x = 0;
				for ( int i=0; i < arrayLines.length; i++ ) {
					System.out.println( arrayLines[ i ] ) ;
					//return arrayLines[i];
					x = i;
					//can get rid of this part
					}
				//arrayLines = the text file that contains all of the lines in the recipes file
				return arrayLines;
			}
			catch (IOException e){
				System.out.println( e.getMessage() );
				return null;
			}
		//C:/CPSC112_Project:/ListOfRecipes.txt
		}
}