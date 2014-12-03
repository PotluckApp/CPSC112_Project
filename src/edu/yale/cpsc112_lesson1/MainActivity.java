package edu.yale.cpsc112_lesson1;

import android.app.Activity;
import android.content.res.Resources;
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
	
	public static String end = "No matches!";

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
        			String INGREDIENTSLIST = new String("");
        			INGREDIENTSLIST = v.getText().toString();
        			v.setText("");
        			end = "No matches!";
        			System.out.println("You entered: " + INGREDIENTSLIST);
        			matchIngredients(listOfOwnedIngredients(INGREDIENTSLIST + ", "));
        			if (end.equals("No matches!"))
        			{
        				System.out.println("     " + end);
        			}
        			handled = true;
        		}
        		return handled;
        	}
        });  
    }

    public String[] getRecipes()
    {
    	Resources res = getResources();
    	String[] myRecipes = res.getStringArray(R.array.recipes);
    	
    	return myRecipes;
    }
    
    
    public String[] listOfOwnedIngredients(String input)
    {
    		int commas = 0;
    		for (int n = 0; n < input.length(); n++)
    		{
    			if (input.charAt(n) == ',')
    			{
    				commas++;
    			}
    		}
    		String[] ownedIngredients = new String[commas + 1];
    		for (int j = 0; j < commas; j++)
    		{
    			String item = input.substring(0, input.indexOf(", "));
    			ownedIngredients[j] = item;
    			input = input.substring(input.indexOf(", ") + 2);
    		}
    		return ownedIngredients;
    }
    
    public void matchIngredients(String[] input)
    {
    	for (int i = 0; i < 4; i++)
    	{
    		String wholeRecipe = getRecipes()[i];
    		int commas = 0;
    		int commonIngredients = 0;
    		for (int n = 0; n < wholeRecipe.length(); n++)
    		{
    			if (wholeRecipe.charAt(n) == ',')
    			{
    				commas++;
    			}
    		}
    		for (int j = 0; j < commas; j++)
    		{
    			String item = wholeRecipe.substring(0, wholeRecipe.indexOf(","));
    			for (int k = 0; k < input.length; k++) 
    			{
    				if (item.equals(input[k]))
    				{
    					commonIngredients++;
    				}
    			}
    			wholeRecipe = wholeRecipe.substring(wholeRecipe.indexOf(",") + 2);
    		}
    		if (commonIngredients >= commas - 1 - commonIngredients)
			{
				end = getRecipes()[i];
				System.out.println("     You can make: " + end.substring(0, end.indexOf(",")));
				System.out.println("     Ingredients: " + end.substring(end.indexOf(",") + 1, end.length() - 3));
				System.out.println("");
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
