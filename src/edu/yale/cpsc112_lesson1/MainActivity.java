package edu.yale.cpsc112_lesson1;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.view.KeyEvent;
import android.widget.TextView.OnEditorActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends Activity {
	private String path;
	
	public void ReadFile(String file_path){
		path = file_path;
	}
		
	public String[] OpenFile() throws IOException{
		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);
		
		int numberOfLines = readLines();
		String[ ] textData = new String[numberOfLines];
		
		for (int i=0; i < numberOfLines; i++) {
			textData[ i ] = textReader.readLine();
			}
		
		textReader.close( );
		return textData;
		
	}

	int readLines() throws IOException{
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
	
	
	public static String ingredient = "";
	public static String ownedIngredients="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView out = (TextView) findViewById(R.id.textOut);
        out.setMovementMethod(new ScrollingMovementMethod());
        System.setOut(new TextViewPrintStream(this, out));
        EditText editText = (EditText) findViewById(R.id.textIn);
        editText.setOnEditorActionListener(new OnEditorActionListener(){
        	@Override
        	public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
        	{
        		boolean handled = false;
        		if (actionId == EditorInfo.IME_ACTION_DONE)
        		{
        			ingredient = v.getText().toString();
        			v.setText("");
        			System.out.println(ingredient); //just to check f it worked
        			handled = true;
        		}
        		return handled;
        	}
        });
        
        
    }
	//need to convert each ingredient to a number which will all be put into a string ownedIngredients^?
	int commonIngredients=0;

	public static int commonIngredientList ()
	{
		for (int i=0; i>=charAt(ownedIngredients.length()+1)
		{
			for (int j=0; i>=charAt(recipeOneIngredients.length+1)
			{
				if (ownedIngredients.charAt(i)==recipeOneIngredients.charAt(j))
				{
					commonIngredients+1
				}
			}

		}
	}
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
