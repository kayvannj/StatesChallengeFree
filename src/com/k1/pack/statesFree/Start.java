package com.k1.pack.statesFree;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.*;

import com.mixpanel.android.mpmetrics.MixpanelAPI;


public class Start extends Activity
{

    private Button mChalengeButton;
    private Button mBestScoreButton;
    private Button mDesButton;
    private Button mnasional;
    public static MixpanelAPI mixpanel;
    protected static final String TOKEN = "b570612afee581dde1ed506834f92707";
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        final Window win = getWindow();
        final int screenHeight = win.getWindowManager().getDefaultDisplay().getHeight();
        final int screenWidth = win.getWindowManager().getDefaultDisplay().getWidth();
        // No Statusbar
        win.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);    
        // No Titlebar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_PROGRESS);  
 
        setContentView(R.layout.main);

        //debug
        //new  AlertDialog.Builder(this).setMessage(screenWidth+"X"+screenHeight).show();
        
        mChalengeButton = (Button) findViewById(R.id.btgotochalenge);
        mBestScoreButton = (Button) findViewById(R.id.btBestScore);
        mDesButton = (Button) findViewById(R.id.ButtonDes);
        mnasional = (Button) findViewById(R.id.ButtonMusic);
        mixpanel = MixpanelAPI.getInstance(this, TOKEN);
        
        mixpanel.track("Home Page",null);
        
        mChalengeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                launchStartChallenge(StartChallenge.class);
            }
        });
        mBestScoreButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                launchStartChallenge(BestScores.class);
            }
        });
        mDesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:\"Kayvan nj\""));
				startActivity(i);
            }
        });
        mnasional.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                launchStartChallenge(NasionalAlanthem.class);
            }
        });
        
    }

    private void launchStartChallenge(Class ac) {
        Intent i = new Intent(this, ac);
        startActivity(i);
    }
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	mixpanel.flush();
    	super.onDestroy();
    }
}

