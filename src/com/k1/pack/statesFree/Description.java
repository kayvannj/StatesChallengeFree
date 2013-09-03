package com.k1.pack.statesFree;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;


public class Description extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		final Window win = getWindow();
        final int screenHeight = win.getWindowManager().getDefaultDisplay().getHeight();
        final int screenWidth = win.getWindowManager().getDefaultDisplay().getWidth();
        // No Statusbar
        win.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);    
        // No Titlebar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_PROGRESS);  
 
		
		setContentView(R.layout.des);
	}
}
