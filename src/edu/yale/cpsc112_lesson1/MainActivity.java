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
	
	
//	public static String INGREDIENTSLIST = "";
	public static int commonIngredients;
	public static int index;
	public static String end = "No matches!";
//	public static int commas = 0;
//	public static String[]ownedIngredients = new String[commas];

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
        			matchIngredients(listOfOwnedIngredients(INGREDIENTSLIST));
        	//		System.out.println(INGREDIENTSLIST);
        	//		System.out.println(listOfOwnedIngredients(INGREDIENTSLIST)[0]);
        	//		if (end.equals("No matches!"))
        	//		{
        				System.out.println(end);
        	//		}
        			handled = true;
        		}
        		return handled;
        	}
        });  
    }

    public String[] getRecipes()
    {
    	Resources res = getResources();
    	String[] recipes = res.getStringArray(R.array.recipes);
    	return recipes; 
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
    
    public String matchIngredients(String[] input)
    {
   // 	String end = "No match";
    	
    	//NEED TO REPLACE 3 WITH NUMBER OF RECIPES IN ARRAY*****
    		
    /*	for (int i=0; i<=3; i++)
    	{
    		String wholeRecipe = getRecipes()[i];
    		int commas = 0;
    		for (int n = 0; wholeRecipe.indexOf(", ") != -1; n++)
    		{
    			commas++;
    			wholeRecipe = wholeRecipe.substring(wholeRecipe.indexOf(", ") + 2);
    		}
    		wholeRecipe = getRecipes()[i]; */
    	for (int n = 0; n < 4; n++)
    	{
    		String[] storedRecipe = listOfOwnedIngredients(getRecipes()[n]);
    		if (storedRecipe.length < input.length)
    		{
    			for (int k = 0; k < storedRecipe.length - 1; k++)
    			{
    				if (storedRecipe[k].equals(input[k]))
    						{
    					commonIngredients++;
    						}
    				if (commonIngredients / storedRecipe.length >= 0.5)
    				{
    					end = storedRecipe[storedRecipe.length - 1];
    				}
    			}
    		}
    		else
    		{
    			for (int k = 0; k < input.length - 1; k++)
    			{
    				if (storedRecipe[k].equals(input[k]))
    						{
    					commonIngredients++;
    						} 
    				if (commonIngredients / storedRecipe.length >= 0.5)
    				{
    					end = storedRecipe[storedRecipe.length - 1];
    				}
    			}
    		}
    	}
    	
    /*	for (int j = 0; j < commas; j++)
    		{
    			String item = wholeRecipe.substring(0, wholeRecipe.indexOf(", "));
    			
    			
    			for (int k=0; k<= 3; k++) 
    			{
    				if (item.equals(input[k]))
    				{
    					commonIngredients++;
    				}
    			}
    			if (commonIngredients / 3 >= .75)
    			{
    				end = getRecipes()[i];
    				System.out.println(end.substring(0, end.length()-1));
    			}
    			wholeRecipe = wholeRecipe.substring(wholeRecipe.indexOf(", ") + 2);
    			
    			 
    /*			if (item.equals(ingredient))
    			{
    				end = getRecipes()[i];
    				System.out.println(end.substring(0, end.length()-1));
    			}
    			wholeRecipe = wholeRecipe.substring(wholeRecipe.indexOf(", ") + 2); */
    	return end;
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
