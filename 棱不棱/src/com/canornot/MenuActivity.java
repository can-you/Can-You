package com.canornot;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MenuActivity extends Activity implements OnClickListener {

	private Button btnGame1;
	private Button btnGame2;
	private Button btnExit;
	private Button btnGameHelp;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_menu);

		btnGame1 = (Button) findViewById(R.id.btnGame1);
		btnGame2 = (Button) findViewById(R.id.btnGame2);
		btnGameHelp = (Button) findViewById(R.id.btnGameHelp);
		btnExit = (Button) findViewById(R.id.btnExitGame);
		
		
		
		btnGame1.setOnClickListener(this);
		btnGame2.setOnClickListener(this);
		btnGameHelp.setOnClickListener(this);
		btnExit.setOnClickListener(this);

		MediaPlayer player = MediaPlayer.create(this, R.raw.background);
		player.setLooping(true);
		player.start();

	}

	@Override
	public void onClick(View v) {
		AlertDialog.Builder builder=new Builder(MenuActivity.this);//设置消息弹出框

		switch (v.getId()) {
		case R.id.btnGame1:
			Intent intentGame1 = new Intent(MenuActivity.this, Game1_First_Descrip.class);
			startActivity(intentGame1);
			MenuActivity.this.finish();
			
			break;

		case R.id.btnGame2:
		//	Intent intentGame2 = new Intent(MenuActivity.this, Game2Activity.class);
		//	startActivity(intentGame2);
	        builder.setMessage("记住，先过了第一关再来");
	        builder.setPositiveButton("我 去", null);
	        builder.show();
			break;
			
		case R.id.btnGameHelp:
			Intent intentHelp = new Intent(MenuActivity.this, HelpActivity.class);
			startActivity(intentHelp);
			MenuActivity.this.finish();
			break;

		case R.id.btnExitGame:
			System.exit(0);	
			break;
		}
	}

}
