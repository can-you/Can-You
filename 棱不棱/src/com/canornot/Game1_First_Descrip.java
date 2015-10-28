package com.canornot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Game1_First_Descrip extends Activity implements OnClickListener{
	private Button nextPage,jumpPage;
	public static int width;
	public static int height;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//设置全屏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//显示自定义的SurfaceView视图
		
		setContentView(R.layout.game1_first_descrip);
		
		nextPage=(Button)findViewById(R.id.nextPage);
		jumpPage=(Button)findViewById(R.id.jumpPage);
		
		nextPage.setOnClickListener(this);
		jumpPage.setOnClickListener(this);
		
	}
	@Override
    public void onClick(View v){
    	if(v==nextPage){
    	  Intent myIntent=new Intent();
    	  myIntent.setClass(Game1_First_Descrip.this, Game1_Second_Descrip.class);
    	  Game1_First_Descrip.this.startActivity(myIntent);
    	  Game1_First_Descrip.this.finish();
    	}
     else if(v==jumpPage){
    	 Intent intent=new Intent();
    	 intent.setClass(Game1_First_Descrip.this, Game1Activity.class);
    	 Game1_First_Descrip.this.startActivity(intent);
    	 Game1_First_Descrip.this.finish();
       }
    	  
    }
}
