package com.canornot;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class Game1Activity extends Activity implements OnClickListener {

	private GoldcoinView goldcoinView; // 金币掉落的主体动画
	private ImageButton iBtnPress1, iBtnPress2;
	private TextView tvScore,tvTime;
	private int score;
	private PopupWindow popupWindow;

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
	private PopupWindow showPopWindows(View view, String string, boolean b) {

		view = this.getLayoutInflater().inflate(R.layout.view_gold_drop, null);

		iBtnPress2 = (ImageButton) view.findViewById(R.id.ibtnPress2);
		tvScore = (TextView) view.findViewById(R.id.tvScore);
		tvTime = (TextView) view.findViewById(R.id.tvTime);
		
		
//		Timer timer = new Timer();
//		
//		TimerTask task = new TimerTask() {
//			
//			@Override
//			public void run() {
//				time--;
//				tvTime.setText(time);
//			}
//		};
//		timer.schedule(task, 1000,1000);
		
		iBtnPress2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				score++;
				tvScore.setText("您的得分是：" + score);
			}
		});

		final LinearLayout container = (LinearLayout) view
				.findViewById(R.id.container);
		container.addView(goldcoinView);

		popupWindow = new PopupWindow(view,
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setFocusable(true);
		popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

		return popupWindow;
	}

}
