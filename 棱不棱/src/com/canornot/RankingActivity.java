package com.canornot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class RankingActivity extends Activity implements OnClickListener {

	private Button btnRunBackToMenu;
	private Button btnExitGame;
	SharedPreferences sp;
	private TextView tvCurrentScore;
	private TextView tvHighestScore;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_ranking);

		MediaPlayer player = MediaPlayer.create(this, R.raw.background);
		player.setLooping(true);
		player.start();
		
		btnRunBackToMenu = (Button) findViewById(R.id.btnRunBackToMenu);
		btnExitGame = (Button)findViewById(R.id.btnExitGame);
		tvCurrentScore = (TextView) findViewById(R.id.tvCurrentScore);
		tvHighestScore = (TextView) findViewById(R.id.tvHighestScore);
		
		btnRunBackToMenu.setOnClickListener(this);
		btnExitGame.setOnClickListener(this);
		
		sp = this.getPreferences(Context.MODE_WORLD_READABLE+Context.MODE_WORLD_WRITEABLE);
		int currentScore = sp.getInt("CurrentScore", -2);
		int highestScore = sp.getInt("HighestScore", -1);
		if(highestScore != -1 && currentScore != -2){
			tvCurrentScore.setText("本次获得金币数为："+currentScore);
			tvHighestScore.setText("历史最高金币数为："+highestScore);
		}
		
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnRunBackToMenu:
			startActivity(new Intent(RankingActivity.this,MenuActivity.class));
			break;

		case R.id.btnExitGame:
			finish();
			System.exit(0);
			break;
		}
		
	}
}
