package com.k1.pack.statesFree.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.k1.pack.statesFree.R;
import com.k1.pack.statesFree.Main.MainActivity;

public class ResForFullAnswer extends MainActivity implements OnClickListener {

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
	Button feedback;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// shared pref
		SharedPreferences mySharedPref = getSharedPreferences(MYBESTSCORE, Activity.MODE_PRIVATE);
		SharedPreferences.Editor mySharedPrefEditor = mySharedPref.edit();
		// getting intent info
		Intent i = this.getIntent();
		testRes = i.getIntArrayExtra("testResult");

		initLayout();

		Start.mixpanel.track("Post Challenge", null);

		usersName = new EditText(getBaseContext());

		int record = mySharedPref.getInt("bestScore" + testRes[0], 0);
		int percentage = (testRes[1] * 100) / testRes[0];
		if (record < percentage) {
			// new
			// AlertDialog.Builder(getBaseContext()).setMessage("Please enter your name :").setView(usersName).show();
			mySharedPrefEditor.putInt("bestScore" + testRes[0], percentage);
			// mySharedPrefEditor.putString("bestScore"+testRes[0], usersName.getText().toString());
			record = percentage;
			mySharedPrefEditor.commit();
		}

	}

	private void initLayout() {

		setContentView(R.layout.res_all_correct);

		myTVpercentage = (TextView) findViewById(R.id.TextViewPercentage);
	
		myTVrecord = (TextView) findViewById(R.id.TextViewRecord);
		

		
		mListView = (ListView) findViewById(R.id.listView1);
		
		feedback = (Button) findViewById(R.id.bt_feedback);
		feedback.setOnClickListener(this);
		
		tryAgain = (Button) findViewById(R.id.try_again);
		tryAgain.setText("Start Again");
		tryAgain.setOnClickListener(this);
		
		int percentage = (testRes[1] * 100) / testRes[0];
		myTVpercentage.setText(percentage + "% Correct");
		if (testRes[0] != 50) {
			myTVrecord.setText("Why don't you challenge yourself with more questions?");
		} else {
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

		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.try_again:
				launchActivity(StartChallenge.class);
				break;
			case R.id.bt_feedback:
				Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.k1.pack.statesFree"));
				startActivity(i);
				break;
			default:
				break;
		}
		
	}

	@Override
	protected void onDestroy() {
		Start.mixpanel.flush();
		super.onDestroy();
	}
}
