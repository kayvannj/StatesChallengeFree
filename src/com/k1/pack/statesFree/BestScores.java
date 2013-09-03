package com.k1.pack.statesFree;

import java.util.ArrayList;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AnalogClock;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;



public class BestScores extends Activity
{

	public static final String MYBESTSCORE = "myBestScoreList";
	ListView myLView;
	TextView myTextView;
	Button resetButton;
	ArrayList<String> listItems;

    public void onCreate(Bundle savedInstanceState)
    {
    	super.onCreate(savedInstanceState);
    	//shared pref
    	final SharedPreferences mySharedPref = getSharedPreferences(MYBESTSCORE, Activity.MODE_PRIVATE);
		final SharedPreferences.Editor mySharedPrefEditor = mySharedPref.edit();
		
		final Window win = getWindow();
        final int screenHeight = win.getWindowManager().getDefaultDisplay().getHeight();
        final int screenWidth = win.getWindowManager().getDefaultDisplay().getWidth();
        // No Statusbar
        win.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);    
        // No Titlebar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_PROGRESS); 
		
		
    	setContentView(R.layout.bestscores);
		myLView = (ListView)findViewById(R.id.ListView01);
    	myTextView = (TextView)findViewById(R.id.bestScoreText1);
		resetButton = (Button)findViewById(R.id.ResetScores);
		listItems = new ArrayList<String>();
		
		for (int i = 5; i < 51; i++) {
			int temp = mySharedPref.getInt("bestScore"+i, 0);
			//String tempStr =  mySharedPref.getString("bestScore"+i,"Anonymous");
			if(temp!=0)
			listItems.add(i+" Question test : "+temp+"%");
		}
		if(listItems.size()==0){
			listItems.add("There is no data available.");
		}
		myTextView.setText("Here are your best scores :");
		myLView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems));
		
		resetButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				for (int i = 5; i < 51; i++) {
					mySharedPrefEditor.remove("bestScore"+i);
				}
				mySharedPrefEditor.commit();
				startActivity(getIntent());
				
			}
		});
		
    }
    

}

