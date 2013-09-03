
package com.k1.pack.statesFree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;



public class Challenge extends Activity
{

	State[] states = {
			new State(0, "Alabama"),
			new State(1, "Alaska"),
			new State(2, "Arizona"),
			new State(3, "Arkansas"),
			new State(4, "California"),
			new State(5, "Colorado"),
			new State(6, "Connecticut"),
			new State(7, "Delaware"),
			new State(8, "Florida"),
			new State(9, "Georgia"),	
			new State(10, "Hawaii"),	
			new State(11, "Idaho"),	
			new State(12, "Illinois"),
			new State(13, "Indiana"),	
			new State(14, "Iowa"),	
			new State(15, "Kansas"),	
			new State(16, "Kentucky"),
			new State(17, "Louisiana"),
			new State(18, "Maine"),	
			new State(19, "Maryland"),
			new State(20, "Massachusetts"),
			new State(21, "Michigan"),
			new State(22, "Minnesota"),
			new State(23, "Mississippi"),
			new State(24, "Missouri"),
			new State(25, "Montana"),	
			new State(26, "Nebraska"),
			new State(27, "Nevada"),	
			new State(28, "New Hampshire"),
			new State(29, "New Jersey"),	
			new State(30, "New Mexico"),	
			new State(31, "New York"),	
			new State(32, "North Carolina"),	
			new State(33, "North Dakota"),	
			new State(34, "Ohio"),	
			new State(35, "Oklahoma"),
			new State(36, "Oregon"),	
			new State(37, "Pennsylvania"),
			new State(38, "Rhode Island"),
			new State(39, "South Carolina"),
			new State(40, "South Dakota"),	
			new State(41, "Tennessee"),	
			new State(42, "Texas"),	
			new State(43, "Utah"),	
			new State(44, "Vermont"),
			new State(45, "Virginia"),
			new State(46, "Washington"),
			new State(47, "West Virginia"),
			new State(48, "Wisconsin"),	
			new State(49, "Wyoming")};
	int[] myImages={
    		R.drawable.img01,
    		R.drawable.img02,
    		R.drawable.img03,
    		R.drawable.img04,
    		R.drawable.img05,
    		R.drawable.img06,
    		R.drawable.img07,
    		R.drawable.img08,
    		R.drawable.img09,
    		R.drawable.img10,
    		R.drawable.img11,
    		R.drawable.img12,
    		R.drawable.img13,
    		R.drawable.img14,
    		R.drawable.img15,
    		R.drawable.img16,
    		R.drawable.img17,
    		R.drawable.img18,
    		R.drawable.img19,
    		R.drawable.img20,
    		R.drawable.img21,
    		R.drawable.img22,
    		R.drawable.img23,
    		R.drawable.img24,
    		R.drawable.img25,
    		R.drawable.img26,
    		R.drawable.img27,
    		R.drawable.img28,
    		R.drawable.img29,
    		R.drawable.img30,
    		R.drawable.img31,
    		R.drawable.img32,
    		R.drawable.img33,
    		R.drawable.img34,
    		R.drawable.img35,
    		R.drawable.img36,
    		R.drawable.img37,
    		R.drawable.img38,
    		R.drawable.img39,
    		R.drawable.img40,
    		R.drawable.img41,
    		R.drawable.img42,
    		R.drawable.img43,
    		R.drawable.img44,
    		R.drawable.img45,
    		R.drawable.img46,
    		R.drawable.img47,
    		R.drawable.img48,
    		R.drawable.img49,
    		R.drawable.img50};
	State[] rStates = states;
	Button o1;
 	Button o2;
 	Button o3;
 	Button o4;
 	Button o5;
	ProgressBar mProgressBar;
	TextView mTextView;
	ImageView mImg;
	ImageSwitcher mImgSw;
	Chronometer chronometer = new Chronometer();
	
	String answer = new String();
	int answersID;
	
	int index=0;//index 
    int col=0;//col of state list
    int debugMode = 0;
    int questionNumber=0;
    int questionLimit=5;//will be determined by user in startChallenge page 
    int[] res;
    Question[] questions;
    
    ArrayList<String> wrongAnswers;
    ArrayList<Integer> wrongAnswersId;
    
    /**
     * Called when the activity is first created. Responsible for initializing the UI.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        final Window win = getWindow();
        // No Statusbar
        win.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);    
        // No Titlebar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_PROGRESS); 
        setContentView(R.layout.challenge);
        
        
        
        //declearing option buttons
        o1 = (Button)findViewById(R.id.Option1);
        o1.setOnClickListener(new myListener());
        
    	o2 = (Button)findViewById(R.id.Option2);
    	o2.setOnClickListener(new myListener());
    	
    	o3 = (Button)findViewById(R.id.Option3);
    	o3.setOnClickListener(new myListener());
    	
    	o4 = (Button)findViewById(R.id.Option4);
    	o4.setOnClickListener(new myListener());
    	
    	o5 = (Button)findViewById(R.id.Option5);//next button
    	
    	mImgSw = (ImageSwitcher)findViewById(R.id.ImageSwitcher01);
    	mProgressBar =(ProgressBar)findViewById(R.id.ProgressBar01);
        mTextView = (TextView)findViewById(R.id.TextViewStatusTitle);
    	Intent inte = this.getIntent();
        questionLimit = inte.getIntExtra("questionNum", 10);
    	
        try {
			Start.mixpanel.track("Challenge", new JSONObject(questionLimit+""));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Start.mixpanel.track("Challenge",null);
			e.printStackTrace();
		}
        
        wrongAnswers = new ArrayList<String>();
        wrongAnswersId = new ArrayList<Integer>();
        res = new int[questionLimit+3];
        res[0]=questionLimit;//total question numbers
        res[1]=0;//correct answers
        res[2]=0;//wrong answers
        Collections.shuffle(Arrays.asList(rStates));
    	
        questions = new Question[questionLimit];
        
        questions = makeTest();
        
        //displaye first question
        displayeTest();

        o5.setText("Next");
        o5.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				next();
			}
		});
        mProgressBar.setMax(questionLimit-1);
    }
    //picking random state as an answer and 3 other states as an options 
    private Question[] makeTest(){
    	Question[] myQuestions = new Question[questionLimit];
    	for (int j = 0; j < questionLimit; j++) {
    		int[] IDs = new int[4];
        	State[] tests = new State[4];
        	
        	for(int i =0;i<4;i++){
        		tests[i]=rStates[((col*4)+i+index)%50];
            	IDs[i]=((col*4)+i+index)%50;
            	if(debugMode==1)tests[i].name += IDs[i]+"";
        	}
            
            if(col*4+index<=49 && col*4+index+3>=49){
                index++;
                col=-1;
            }
            
            myQuestions[j]=new Question(j+1, tests[0].toString(), tests[0].getID(),tests);
            Collections.shuffle(Arrays.asList(tests));
            myQuestions[j].setOptions(tests);
            col++;
           
            
		}
    	
    	
        return myQuestions;
    	
    } 
    
    //check the buttons text with ans and return true if there were same
    private void checkAnswer(String ans,String opt,int ansID){
    	//check if ansewr is correct 
    	if(ans.equals(opt)){
    		//correct answers
    		res[1]++;
    		//add the answer
    		
    	}else{
    		//wrong answers
    		res[2]++;
    		//add the answer
    		//if (!ans.contains("")) {
    			wrongAnswers.add(ans);
    			wrongAnswersId.add(myImages[ansID]);
			//}
    	}
    }
    
    //finnish
    private void finnish(){
    	if (wrongAnswers.size()==0) {
    		Intent intent = new Intent(Challenge.this, ResForFullAnswer.class);
	    	intent.putExtra("testResult", res);
	    	startActivity(intent);
		}else{
	    	//send the res to the new activity
	    	Intent intent = new Intent(Challenge.this, Res.class);
	    	intent.putExtra("testResult", res);
	    	intent.putStringArrayListExtra("wrongAnswers", wrongAnswers);
	    	intent.putIntegerArrayListExtra("wrongAnswersId", wrongAnswersId);
	    	startActivity(intent);
		}
    }
    //next
    private void next(){
    	checkAnswer(questions[questionNumber-1].getAnswer(), questions[questionNumber-1].getUserInput(),questions[questionNumber-1].getAnswerID());
    	
    	if(questionNumber==questionLimit){
    		finnish();
    	}else{
    		resetButtonBG();
    		displayeTest();
    	}
    }
    
    //changing the image of imageVew and texts of Buttons and status text
    private void displayeTest(){
    		
	    	State[] opt= questions[questionNumber].getOptions();
	    	o1.setText(opt[0].getName());
	    	o2.setText(opt[1].getName());
	    	o3.setText(opt[2].getName());
	    	o4.setText(opt[3].getName());
	    	mTextView.setText((1+questionNumber)+"/"+questionLimit);
	    	Resources r = new Resources(getAssets(), new DisplayMetrics(), new Configuration());
	    	mImgSw.setForeground(r.getDrawable(myImages[questions[questionNumber].getAnswerID()]));
	    	mProgressBar.setProgress((questionNumber));
	    	questionNumber++;
	    
    }
    private void resetButtonBG(){
		o1.setBackgroundResource(R.drawable.bluebut);
		o2.setBackgroundResource(R.drawable.bluebut);
		o3.setBackgroundResource(R.drawable.bluebut);
		o4.setBackgroundResource(R.drawable.bluebut);
	}

    class myListener implements OnClickListener{
    	@Override
    	public void onClick(View v) {
    		Button btOpt = (Button)v;
    		String opt = String.valueOf(btOpt.getText());
    		questions[questionNumber-1].userInput = opt;
    		resetButtonBG();
    		btOpt.setBackgroundResource(R.drawable.orengebut);
    	}
    	
    }
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	Start.mixpanel.flush();
    	super.onDestroy();
    }
}





