package com.k1.pack.statesFree;



import android.util.Log;

public class Chronometer
{
	private long msBegin = 0, msEnd = 0, msTime = 0;
	private static String TAG = "MasterMindChronometer";
	
	public Chronometer() {}
	
	public void start()
	{
		msTime = 0;
		msBegin = System.currentTimeMillis();
        Log.i(Chronometer.TAG, "Chronometer started.");
	}
	
	public void stop()
	{
		msEnd = System.currentTimeMillis();
		msTime += msEnd-msBegin;
        Log.i(Chronometer.TAG, "Chronometer stopped.");
	}
	
	public void resume()
	{
		msBegin = System.currentTimeMillis();
	}
	
	public long seconds() {
		return msTime/1000;
	}
	
	public long time()
	{
		return msTime;
	}

	public String toString() {
		long secs = msTime/1000;
		long hundredth = (msTime%1000)/10;
		long minutes = secs/60;
		secs = secs%60;
		return String.format("%d:%d.%d", minutes, secs, hundredth);
	}
}