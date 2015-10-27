package com.canornot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Game1_Second_Descrip extends Activity implements OnClickListener{
	private Button go_first;
	private Button back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.game1_second_descrip);
		
		go_first=(Button)findViewById(R.id.go_first);
		back=(Button)findViewById(R.id.back);
		
		go_first.setOnClickListener(this);
		back.setOnClickListener(this);
	}
    public void onClick(View v){
    	if(v==go_first){
    		Intent inte=new Intent();
    		inte.setClass(Game1_Second_Descrip.this,Game1Activity.class);
    		Game1_Second_Descrip.this.startActivity(inte);
    		Game1_Second_Descrip.this.finish();
    	}
    	else if(v==back){
        	Intent newintent=new Intent();
        	newintent.setClass(Game1_Second_Descrip.this,Game1_First_Descrip.class);
        	Game1_Second_Descrip.this.startActivity(newintent);
        	Game1_Second_Descrip.this.finish();
    	}
    }
}
