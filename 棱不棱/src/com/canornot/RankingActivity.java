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
import android.widget.ImageView;
import android.widget.TextView;

public class RankingActivity extends Activity implements OnClickListener {

	private Button btnRunBackToMenu;
	private Button btnExitGame;
	public static SharedPreferences sp;
	private TextView tvCurrentScore;
	private TextView tvHighestScore;
	private ImageView ivStar;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_ranking);

		MediaPlayer player = MediaPlayer.create(this, R.raw.background);
		
		MediaPlayer over = MediaPlayer.create(this, R.raw.over_game1);
		over.start();
		player.setLooping(true);
		player.start();
		
		
		
		
		btnRunBackToMenu = (Button) findViewById(R.id.btnRunBackToMenu);
		btnExitGame = (Button)findViewById(R.id.btnExitGame);
		tvCurrentScore = (TextView) findViewById(R.id.tvCurrentScore);
		tvHighestScore = (TextView) findViewById(R.id.tvHighestScore);
		ivStar = (ImageView) findViewById(R.id.ivStar);
		
		btnRunBackToMenu.setOnClickListener(this);
		btnExitGame.setOnClickListener(this);
		
		sp = getSharedPreferences("Game1RankData",
				MODE_PRIVATE);
		int currentScore = sp.getInt("CurrentScore", -2);
		int highestScore = sp.getInt("HighestScore", -1);
		if(highestScore != -1 && currentScore != -2){
			tvCurrentScore.setText("本次记录："+currentScore);
			tvHighestScore.setText("最高记录："+highestScore);
		}
		sp.edit().commit();
		
		Intent intent = getIntent();
		int score = intent.getIntExtra("CurrentScore", -1);
		int flag = 0;
		if(score <= 150){
			flag = 6;
		}
		else if(score <= 160){
			flag = 1;
		}else if (score <= 180) {
			flag = 2;
		}else if(score <= 230){
			flag = 3;
		}else if (score <= 250) {
			flag = 4;
		}else if(score <= 260){
			flag = 5;
		}
		
		switch(flag){
		case 1:
			ivStar.setImageResource(R.drawable.one_star);
			break;
		case 2:
			ivStar.setImageResource(R.drawable.two_stars);
			break;	
		case 3:
			ivStar.setImageResource(R.drawable.three_stars);
			break;
		case 4:
			ivStar.setImageResource(R.drawable.four_stats);
			break;
		case 5:
			ivStar.setImageResource(R.drawable.five_stars);
			break;
		case 6:
			ivStar.setImageResource(R.drawable.laji);
		}
		String string = "本次记录:  " + score;
		tvCurrentScore .setText(string);
		
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
