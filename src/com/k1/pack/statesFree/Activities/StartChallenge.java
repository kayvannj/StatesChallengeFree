package com.k1.pack.statesFree.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.k1.pack.statesFree.R;
import com.k1.pack.statesFree.Main.MainActivity;

public class StartChallenge extends MainActivity implements OnClickListener {
	TextView	mText;
	Button		btStartChallenge;
	SeekBar		mSeekBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		initLayout();

		Start.mixpanel.track("Pre Challenge", null);

	}

	private void initLayout() {

		setContentView(R.layout.startchallenge);

		btStartChallenge = (Button) findViewById(R.id.btstartchalenge);
		mSeekBar = (SeekBar) findViewById(R.id.SeekBar01);
		mSeekBar.setEnabled(true);// Demo Remover
		mText = (TextView) findViewById(R.id.TextView01);
		mText.setText("Select number of questions you want : 10");

		mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				mText.setText("Select number of questions you want : " + ((mSeekBar.getProgress() * 5) + 5));

			}
		});

		btStartChallenge.setOnClickListener(this);
	}

	@Override
	protected void onDestroy() {

		Start.mixpanel.flush();
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btstartchalenge:
				Intent i = new Intent(this, Challenge.class);
				int progress = (mSeekBar.getProgress() * 5) + 5;
				i.putExtra("questionNum", progress);
				startActivity(i);
				break;
			default:
				break;
		}
	}

}