package com.k1.pack.statesFree.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.k1.pack.statesFree.GlobalParams;
import com.k1.pack.statesFree.R;
import com.k1.pack.statesFree.Main.MainActivity;
import com.mixpanel.android.mpmetrics.MixpanelAPI;

public class Start extends MainActivity implements OnClickListener {
	private Button				mChalengeButton;
	private Button				mBestScoreButton;
	private Button				mDesButton;
	private Button				mnasional;
	public static MixpanelAPI	mixpanel;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final Window win = getWindow();

		// No Status bar
		win.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// No Title bar
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_PROGRESS);

		initLayout();

		mixpanel = MixpanelAPI.getInstance(this, GlobalParams.MIXPANEL_TOKEN);
		mixpanel.track("Home Page", null);

	}

	private void initLayout() {
		setContentView(R.layout.activity_home);

		mChalengeButton = (Button) findViewById(R.id.btgotochalenge);
		mBestScoreButton = (Button) findViewById(R.id.btBestScore);
		mDesButton = (Button) findViewById(R.id.ButtonDes);
		mnasional = (Button) findViewById(R.id.ButtonMusic);

		mChalengeButton.setOnClickListener(this);
		mBestScoreButton.setOnClickListener(this);
		mDesButton.setOnClickListener(this);
		mnasional.setOnClickListener(this);
	}

	private void launchActivityFromClass(Class ac) {
		Intent i = new Intent(this, ac);
		startActivity(i);
	}

	@Override
	protected void onDestroy() {
		mixpanel.flush();
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.btgotochalenge:
				launchActivityFromClass(StartChallenge.class);
				break;
			case R.id.btBestScore:
				launchActivityFromClass(BestScores.class);
				break;
			case R.id.ButtonDes:
				Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:\"Kayvan nj\""));
				startActivity(i);
				break;
			case R.id.ButtonMusic:
				launchActivityFromClass(NasionalAlanthem.class);
				break;
			default:
				break;
		}
	}
}
