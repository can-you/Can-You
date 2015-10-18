package com.canornot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

public class GameWelcomeActivity extends Activity {

	private RelativeLayout layout;
	private AlphaAnimation alphaAnimation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_game_welcome);
		
		layout = (RelativeLayout) findViewById(R.id.game_welcome);
		layout.setBackgroundResource(R.drawable.background_welcome);
		
		alphaAnimation = new AlphaAnimation(0, 1);
		alphaAnimation.setDuration(3000);
		layout.setAnimation(alphaAnimation);
		layout.startAnimation(alphaAnimation);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		Intent intent = new Intent(GameWelcomeActivity.this, MenuActivity.class);
		startActivity(intent);
		
		return super.onTouchEvent(event);
	}
	
}
