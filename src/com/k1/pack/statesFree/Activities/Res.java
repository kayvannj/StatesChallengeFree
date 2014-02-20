package com.k1.pack.statesFree.Activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.k1.pack.statesFree.CustomizeDialog;
import com.k1.pack.statesFree.R;
import com.k1.pack.statesFree.Main.MainActivity;


public class Res extends MainActivity
{

	public static final String MYBESTSCORE = "myBestScoreList";
	int[] testRes;
	ArrayList<String> wrongAns;
	ArrayList<Integer> wrongAnsId;
	TextView myTVpercentage;
	TextView myTVwronganswers;
	TextView myTVrecord;
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
		wrongAns = i.getStringArrayListExtra("wrongAnswers");
		wrongAnsId = i.getIntegerArrayListExtra("wrongAnswersId");
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
		mListView = (ListView)findViewById(R.id.listView1);
		mListView.setAdapter(new ArrayAdapter<String>(this, R.layout.list_item,wrongAns));
		usersName = new EditText(getBaseContext());
		tryAgain = (Button)findViewById(R.id.try_again);
		tryAgain.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				launchStartChallenge(StartChallenge.class);
			}
		});
		Start.mixpanel.track("Post Challenge", null);
		int percentage = (testRes[1]*100)/testRes[0];
		int record = mySharedPref.getInt("bestScore"+testRes[0], 0);
		if(record<percentage){
			//new AlertDialog.Builder(getBaseContext()).setMessage("Please enter your name :").setView(usersName).show();
			mySharedPrefEditor.putInt("bestScore"+testRes[0], percentage);
			//mySharedPrefEditor.putString("bestScore"+testRes[0], usersName.getText().toString());
			record = percentage;
			mySharedPrefEditor.commit();
		}
		
		
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				TextView mytextView = (TextView)arg1;
				int myId = 0;
				for (int j = 0; j < wrongAns.size(); j++) {
					if (wrongAns.get(j).equals(mytextView.getText())) {
						myId = wrongAnsId.get(j);
						break;
					}
				}
				
				CustomizeDialog myDialog = new CustomizeDialog(Res.this,myId);
				myDialog.setCancelable(true);
				Start.mixpanel.track("Check Mistakes", null);
				myDialog.show();
			}
		});
		
		myTVpercentage.setText(percentage+"% Correct");
		myTVwronganswers.setText(testRes[2]+" mistake.");
		if(percentage==record){
			myTVrecord.setText("new record with "+testRes[0]+" questions.");
		}else{
			myTVrecord.setText("Best record is : "+record+"%");
		}

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

