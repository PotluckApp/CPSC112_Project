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
	
	
	public static String ingredient = "";
	public static int commonIngredients;
	public static int index;
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
        			ingredient = v.getText().toString();
        			v.setText("");
        			end = "No matches!";
        		//	System.out.println(ingredient); //just to check if it worked
        			matchIngredients(ingredient);
        		//	matchIngredients(ingredient);
        			if (end.equals("No matches!"))
        			{
        				System.out.println(end);
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
    	String[] recipes = res.getStringArray(R.array.recipes);
    	return recipes; 
    }
    
    public String matchIngredients(String input)
    {
   // 	String end = "No match";
    	for (int i=0; i<=3; i++)
    	{
    		String wholeRecipe = getRecipes()[i];
    		int commas = 0;
    		for (int n = 0; wholeRecipe.indexOf(", ") != -1; n++)
    		{
    			commas++;
    			wholeRecipe = wholeRecipe.substring(wholeRecipe.indexOf(", ") + 2);
    		}
    		wholeRecipe = getRecipes()[i];
    		for (int j = 0; j < commas; j++)
    		{
    			String item = wholeRecipe.substring(0, wholeRecipe.indexOf(", "));
    			/*for (k=0, k<=NUMBER OF INGREDIENTS INPUTED; k++) 
    			 *{if (item.equals(ownedIngredients[k])
    			 */
    			if (item.equals(ingredient))
    			{
    				end = getRecipes()[i];
    				System.out.println(end.substring(0, end.length()-1));
    			}
    			wholeRecipe = wholeRecipe.substring(wholeRecipe.indexOf(", ") + 2);
    		}
    	}
    	return end;
    }



//commonIngredients / numberOfIngredients > .75

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
