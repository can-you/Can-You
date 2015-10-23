package com.canornot;



import android.app.Activity;
import android.app.DownloadManager.Request;
import android.os.Bundle;
//import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class game22 extends Activity {
	public static game22 instance;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance = this;
		//setContentView(R.layout.activity_main);
		
		
		    
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(new mysurfaceview(this));
	}
}
	

	

