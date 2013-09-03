
package com.k1.pack.statesFree;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class StartChallenge extends Activity
{
	TextView mText;
	Button btStartChallenge;
	SeekBar mSeekBar;
    /**
     * Called when the activity is first created. Responsible for initializing the UI.
     */
    @Override
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
        
        
        setContentView(R.layout.startchallenge);

        Start.mixpanel.track("Pre Challenge", null);
        
        btStartChallenge = (Button)findViewById(R.id.btstartchalenge);
        mSeekBar = (SeekBar)findViewById(R.id.SeekBar01);
        mSeekBar.setEnabled(true);//Demo Remover
        mText = (TextView)findViewById(R.id.TextView01);
        mText.setText("Select number of questions you want : 10");
        mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				mText.setText("Select number of questions you want : "+((mSeekBar.getProgress()*5)+5));
				
			}
		});
        
        btStartChallenge.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				launchChallenge();
			}
		});
        
    }
    private void launchChallenge() {
        Intent i = new Intent(this,Challenge.class);
        int progress = (mSeekBar.getProgress()*5)+5;
        i.putExtra("questionNum",progress);
        startActivity(i);
    }
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	Start.mixpanel.flush();
    	super.onDestroy();
    }

}