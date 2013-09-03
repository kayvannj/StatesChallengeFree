package com.k1.pack.statesFree;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.style.LeadingMarginSpan;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;



public class ResForFullAnswer extends Activity
{

	public static final String MYBESTSCORE = "myBestScoreList";
	int[] testRes;
	LinearLayout lowerLayout;
	TextView myTVpercentage;
	TextView myTVwronganswers;
	TextView myTVrecord;
	TextView myTVwhatyoumissed;
	TextView myTVtaptovew;
	ListView mListView;
	EditText usersName;
	Button tryAgain;

    public void onCreate(Bundle savedInstanceState)
    {
    	super.onCreate(savedInstanceState);
    	//shared pref
    	SharedPreferences mySharedPref = getSharedPreferences(MYBESTSCORE, Activity.MODE_PRIVATE);
		SharedPreferences.Editor mySharedPrefEditor = mySharedPref.edit();
		//getting intent info
    	Intent i = this.getIntent();
		testRes = i.getIntArrayExtra("testResult");
		
		final Window win = getWindow();
        final int screenHeight = win.getWindowManager().getDefaultDisplay().getHeight();
        final int screenWidth = win.getWindowManager().getDefaultDisplay().getWidth();
        // No Statusbar
        win.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);    
        // No Titlebar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_PROGRESS); 
        
		setContentView(R.layout.res);
		myTVpercentage = (TextView)findViewById(R.id.TextViewPercentage);
		myTVwronganswers = (TextView)findViewById(R.id.TextViewWrongAns);
		myTVrecord = (TextView)findViewById(R.id.TextViewRecord);
		myTVwhatyoumissed = (TextView)findViewById(R.id.TV_WhatYouMissed);
		myTVtaptovew = (TextView)findViewById(R.id.TV_TapToView);
		mListView = (ListView)findViewById(R.id.listView1);
		lowerLayout = (LinearLayout)findViewById(R.id.LL_buttomOfRes);
		myTVwhatyoumissed.setText("");
		myTVtaptovew.setText("");
		tryAgain = (Button)findViewById(R.id.try_again);
		tryAgain.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				launchStartChallenge(StartChallenge.class);
			}
		});
		Start.mixpanel.track("Post Challenge", null);
		
		usersName = new EditText(getBaseContext());
		int percentage = (testRes[1]*100)/testRes[0];
		int record = mySharedPref.getInt("bestScore"+testRes[0], 0);
		if(record<percentage){
			//new AlertDialog.Builder(getBaseContext()).setMessage("Please enter your name :").setView(usersName).show();
			mySharedPrefEditor.putInt("bestScore"+testRes[0], percentage);
			//mySharedPrefEditor.putString("bestScore"+testRes[0], usersName.getText().toString());
			record = percentage;
			mySharedPrefEditor.commit();
		}
		

		myTVpercentage.setText(percentage+"% Correct");
		myTVwronganswers.setText("That was outstanding.");
		if (testRes[0]!=50) {
			myTVrecord.setText("Why don't you challenge yourself with more questions?");
		}else{
			myTVrecord.setText("Why don't you try our other applications?");
		}
		
		Button gotoOtherApps = new Button(this);
		gotoOtherApps.setText("More from us");
		gotoOtherApps.setGravity(Gravity.CENTER);
		gotoOtherApps.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		gotoOtherApps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:\"Kayvan nj\""));
				startActivity(i);
            }
        });
		
		lowerLayout.addView(gotoOtherApps);
		
		
    }
    private void launchStartChallenge(Class ac) {
        Intent i = new Intent(this, ac);
        startActivity(i);
    }
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	Start.mixpanel.flush();
    	super.onDestroy();
    }
}

