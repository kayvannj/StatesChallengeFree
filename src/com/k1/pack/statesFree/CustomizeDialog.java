package com.k1.pack.statesFree;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

/** Class Must extends with Dialog */
/** Implement onClickListener to dismiss dialog when OK Button is pressed */
public class CustomizeDialog extends Dialog implements android.view.View.OnClickListener{
	
	ImageView im;
	
	public CustomizeDialog(Context context, int myId) {
		super(context);
		/** 'Window.FEATURE_NO_TITLE' - Used to hide the title */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		/** Design the dialog in main.xml file */
		setContentView(R.layout.dialogbox);
		
		im = (ImageView)findViewById(R.id.dialogImg);
		//im.setImageResource(myId);
		//Resources r = new Resources(ContextWrapper.getAssets(), new DisplayMetrics(), new Configuration());
		//im.setForeground(r.getDrawable(myId));
		im.setImageResource(myId);
		im.setOnClickListener(this);
		
		
	}
	
	@Override
	public void onClick(View v) {
		dismiss();
	}
}
