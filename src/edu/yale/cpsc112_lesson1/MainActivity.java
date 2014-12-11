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
import java.util.Random;

public class MainActivity extends Activity {
	
	public static String end = "No matches!";
	public static Random r = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView out = (TextView) findViewById(R.id.textOut); //allow users to input text
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
        			INGREDIENTSLIST = v.getText().toString(); //saves user-entered text as INGREDIENTSLIST
        			v.setText("");
        			end = "No matches!";
        			System.out.println("You entered: " + INGREDIENTSLIST); //remind the user what they entered
        			matchIngredients(listOfOwnedIngredients(INGREDIENTSLIST + ", ")); //match ingredients with recipes and print if there is a match
        			if (end.equals("No matches!")) //let the user know that it didn't match a recipe
        			{
        				System.out.println("     " + end);
        				System.out.println("");
        			}
        			handled = true;
        		}
        		return handled;
        	}
        });  
    }

    public String[] getRecipes() //read recipes off strings.xml, where they are stored
    {
    	Resources res = getResources();
    	String[] myRecipes = res.getStringArray(R.array.recipes);
    	
    	return myRecipes;
    }
    
    
    public String[] listOfOwnedIngredients(String input) //parse the user's entry into an array of single ingredients
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
    
    public void matchIngredients(String[] input) //match user-owned ingredients with those in the recipes
    {
    	for (int i = 0; i < 14; i++) //cycle through all recipes
    	{
    		String wholeRecipe = getRecipes()[i];
    		int commas = 0;
    		int commonIngredients = 0;
    		for (int n = 0; n < wholeRecipe.length(); n++) //parse out each recipe into a series of ingredients
    		{
    			if (wholeRecipe.charAt(n) == ',')
    			{
    				commas++;
    			}
    		}
    		for (int j = 0; j < commas; j++)
    		{
    			String item = wholeRecipe.substring(0, wholeRecipe.indexOf(","));
    			for (int k = 0; k < input.length; k++) //check if any of the user's ingredients are used in the recipe
    			{
    				if (item.equals(input[k])) 
    				{
    					commonIngredients++;
    				}
    			}
    			wholeRecipe = wholeRecipe.substring(wholeRecipe.indexOf(",") + 2);
    		}
    		if (commonIngredients >= commas - 1 - commonIngredients) //randomly choose an "enjoy!" message for the user
			{
				end = getRecipes()[i];
				System.out.println("     You can make: " + end.substring(0, end.indexOf(",")));
				System.out.println("     Ingredients: " + end.substring(end.indexOf(",") + 1, end.length() - 3));
				int A = r.nextInt(5) + 1;
				if (A == 1)
				{
					System.out.println("     Bon appetit!");
				}
				if (A == 2)
				{
					System.out.println("     Guten appetit!");
				}
				if (A == 3)
				{
					System.out.println("     Buon appetito!");
				}
				if (A == 4)
				{
					System.out.println("     Buen provecho!");
				}
				if (A == 5)
				{
					System.out.println("     Enjoy your meal!");
				}
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
