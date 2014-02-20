package com.k1.pack.statesFree.Activities;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.k1.pack.statesFree.R;
import com.k1.pack.statesFree.Main.MainActivity;



public class NasionalAlanthem extends MainActivity
{
	private MediaPlayer mp;
    
    
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        final Window win = getWindow();
        // No Statusbar
        win.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);    
        // No Titlebar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_PROGRESS);  
 
        
        
        setContentView(R.layout.nasionalanthem);
        
        mp = new MediaPlayer().create(getApplication(), R.raw.national_anthem);
        mp.start();
        
    }
    @Override
    public void onBackPressed() {
    	// TODO Auto-generated method stub
    	mp.stop();
    	mp.release();
    	super.onBackPressed();
    }


}

