package com.canornot;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class Game1Activity extends Activity implements OnClickListener {

	private GoldcoinView goldcoinView; // 金币掉落的主体动画
	private ImageButton iBtnPress1, iBtnPress2;
	private TextView tvScore, tvTime;
	private int score;
	private PopupWindow popupWindow;

	SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_game1);

		goldcoinView = new GoldcoinView(this);

		iBtnPress1 = (ImageButton) findViewById(R.id.iBtnPress1);

		iBtnPress1.setOnClickListener(this);

		goldcoin = MediaPlayer.create(this, R.raw.goldcoin);
		MediaPlayer player = MediaPlayer.create(this, R.raw.background);
		player.setLooping(true);
		player.start();

		sp = this.getSharedPreferences("Game1RankData", Context.MODE_WORLD_READABLE+Context.MODE_WORLD_WRITEABLE);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iBtnPress1:
			showPopWindows(iBtnPress1, "20", true);
			break;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		goldcoinView.resume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		goldcoinView.pause();
	}

	int time = 20;
	private MediaPlayer goldcoin;

	private PopupWindow showPopWindows(View view, String string, boolean show) {

		view = this.getLayoutInflater().inflate(R.layout.view_gold_drop, null);

		iBtnPress2 = (ImageButton) view.findViewById(R.id.ibtnPress2);
		tvScore = (TextView) view.findViewById(R.id.tvScore);
		tvTime = (TextView) view.findViewById(R.id.tvTime);

		final LinearLayout container = (LinearLayout) view
				.findViewById(R.id.container);
		container.addView(goldcoinView);

		final Timer timer;
		timer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {

				runOnUiThread(new Runnable() {
					public void run() {
						tvTime.setText("" + time);
						time--;
						if (time < 0) {
							timer.cancel();
							tvTime.setText("时间到！");
							iBtnPress2.setEnabled(false);
							container.removeAllViews();

							int index = sp.getInt("HighestScore", -1);
							int currentScore = sp.getInt("CurrentScore", -2);
							if (index == -1 && currentScore == -2) {
								sp.edit().putInt("HighestScore", score);
								sp.edit().putInt("CurrentScore", score);
							} else if (score > index) {
								index = score;
								sp.edit().putInt("HighestScore", index);
								sp.edit().putInt("CurrentScore", score);
							}

							Intent intent = new Intent(Game1Activity.this,
									RankingActivity.class);
							startActivity(intent);

						}
					}
				});

			}
		};

		timer.schedule(task, 1000, 1000);

		iBtnPress2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				score++;
				tvScore.setText("您的金币数是：" + score);
				goldcoin.start();
			}
		});

		popupWindow = new PopupWindow(view,
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setFocusable(true);
		popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

		// final Thread thread = new Thread(new Runnable() {
		// @Override
		// public void run() {
		// try {
		// // 设置2秒后
		// Thread.sleep(2000);
		// runOnUiThread(new Runnable() {
		// @Override
		// public void run() {
		// container.removeAllViews();
		// }
		// });
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		//
		// }
		// });
		// if (time == 0)
		// thread.start();

		return popupWindow;
	}
}
