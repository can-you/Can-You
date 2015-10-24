package com.canornot;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Game1Activity extends Activity {

	private ImageButton rotateButton;
	private ImageView image1;
	private ImageView image2;
	private ImageView image3;
	private ImageView image4;
	private ImageView image5;

	TextView firstTextView;
	TextView tvTime;
	TextView tvRemind;
	int score = 0;

	public SharedPreferences sp;
	private int time;
	private Timer timer = new Timer();
	private MediaPlayer doubi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_game1);

		sp = this.getSharedPreferences("Game1RankData", MODE_PRIVATE);

		MediaPlayer player = MediaPlayer.create(this, R.raw.background);
		player.setLooping(true);
		player.start();
		doubi = MediaPlayer.create(this, R.raw.doubi);

		time = 21;

		image1 = (ImageView) findViewById(R.id.doubi1);
		image2 = (ImageView) findViewById(R.id.doubi2);
		image3 = (ImageView) findViewById(R.id.doubi3);
		image4 = (ImageView) findViewById(R.id.doubi4);
		image5 = (ImageView) findViewById(R.id.doubi5);

		firstTextView = (TextView) findViewById(R.id.firstTextView);
		tvTime = (TextView) findViewById(R.id.tvTime);
		tvRemind = (TextView) findViewById(R.id.tvRemind);

		rotateButton = (ImageButton) findViewById(R.id.rotateButton);

		// 为图片按钮添加触屏监听
		rotateButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.rotateButton:
					tvRemind.setVisibility(View.INVISIBLE);
					score++;
					doubi.start();
					firstTextView.setText("当前逗比值为：" + score);

					AnimationSet animationSet = new AnimationSet(true);
					RotateAnimation rotateAnimation = new RotateAnimation(0,
							720, Animation.RELATIVE_TO_SELF, 0.5f,
							Animation.RELATIVE_TO_SELF, 0.5f);
					rotateAnimation.setDuration(1000);
					animationSet.addAnimation(rotateAnimation);
					TranslateAnimation translateAnimation = new TranslateAnimation(
							Animation.RELATIVE_TO_SELF, 0f,
							Animation.RELATIVE_TO_SELF, 0f,
							Animation.RELATIVE_TO_SELF, 0f,
							Animation.RELATIVE_TO_SELF, 7f);
					translateAnimation.setDuration(500);
					animationSet.addAnimation(translateAnimation);

					int c = score % 5;
					switch (c) {
					case 0:
						image1.startAnimation(animationSet);
						break;
					case 1:
						image2.startAnimation(animationSet);
						break;
					case 2:
						image3.startAnimation(animationSet);
						break;
					case 3:
						image4.startAnimation(animationSet);
						break;
					case 4:
						image5.startAnimation(animationSet);
						break;
					}
					break;
				}
			}
		});

		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 1:
					tvTime.setText("" + time);
					if (time == 0) {
						timer.cancel();
						tvTime.setText("时间到！");
						rotateButton.setEnabled(false);

						Editor editor = sp.edit();

						int index = sp.getInt("HighestScore", -1);
						if (score > index) {
							index = score;
						}
						editor.putInt("HighestScore", index);
						editor.putInt("CurrentScore", score);
						sp.edit().commit();

						editor.commit();

						Intent intent = new Intent(Game1Activity.this,
								RankingActivity.class);
						intent.putExtra("CurrentScore", score);
						startActivity(intent);

					}
				}
			}
		};

		TimerTask task = new TimerTask() {
			@Override
			public void run() {

				time--;

				Message message = new Message();
				message.what = 1;
				handler.sendMessage(message);

			}
		};

		timer.schedule(task, 1000, 1000);

	}

}
