package com.canornot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
		alphaAnimation.setDuration(7000);
		layout.setAnimation(alphaAnimation);
		layout.startAnimation(alphaAnimation);
		
		mHandler.sendEmptyMessageDelayed(GOTO_MAIN_ACTIVITY, 3000);//3ÃëÌø×ª
	}

	private static final int GOTO_MAIN_ACTIVITY = 0;
    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
 
            switch (msg.what) {
                case GOTO_MAIN_ACTIVITY:
                    Intent intent = new Intent();
                    intent.setClass(GameWelcomeActivity.this, MenuActivity.class);
                    startActivity(intent);
                    finish();
                    break;
 
                default:
                    break;
            }
        };
    };
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		Intent intent = new Intent(GameWelcomeActivity.this, MenuActivity.class);
		startActivity(intent);
		
		return super.onTouchEvent(event);
	}
	
}
