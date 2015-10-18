package com.canornot;

import android.app.Activity;
import android.content.Intent;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_menu);

		btnGame1 = (Button) findViewById(R.id.btnGame1);
		btnGame2 = (Button) findViewById(R.id.btnGame2);
		btnExit = (Button) findViewById(R.id.btnExitGame);

		btnGame1.setOnClickListener(this);
		btnGame2.setOnClickListener(this);
		btnExit.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnGame1:
			Intent intent = new Intent(MenuActivity.this, Game1Activity.class);
			startActivity(intent);
			break;

		case R.id.btnGame2:
			break;
			
		case R.id.btnExitGame:
			break;
		}
	}

}
