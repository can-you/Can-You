package com.canornot;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class mysurfaceview extends SurfaceView implements android.view.SurfaceHolder.Callback, Runnable{
	
	private SharedPreferences speasy;
	private SharedPreferences sphard;
	
	private SurfaceHolder sfh;
	public Paint paint;
	public Canvas canvas;
	private Bitmap bmplayer;
	private Bitmap eplayerl1,eplayerl2;
	private Bitmap eplayerr1,eplayerr2;
	private Bitmap boomm;
	private Bitmap bmover;
	private Bitmap grade;
	private Bitmap grademax;
	private Bitmap bmover1;
	private Bitmap bmover2;
//	private Bitmap bmover3;
	private Bitmap mbm;
	private Bitmap mbm1;
	private Bitmap mbm2;
	private Bitmap simple;
	private Bitmap hard;
	public static int screenW, screenH;
	public static final int START = 1;
	public static final int GAMING = 2;
	public static final int OVER = 3;
	public static int state ;
	public static int pp;
	public static int gg;
	public static int bb;
	public static int gametime;
	public static int gradeindex;
	private Vector<enemy> vcenemy;
	private int tim ;
	private int cout;
	private int enemyArray[][];
	//private int enemyArray1[][]={{5,6},{5,6},{1,3,4},{4},{2,3},{6},{2,3},{4},{2},{3},{1,2,4},{3},{2},{1}};
	//private int enemyArray2[][]={{1,2},{3,4},{5,5,5},{4},{1,1},{2},{3,3},{1},{2},{4},{2,2,4},{5},{6},{2}}; 
	private int index;
	private Random random;
	private int x;
	private int y;
	private int overcount;
	private int gamecount;
	private Thread th;
	boolean flag;
	boolean k;
	boolean easym;
	boolean hardm;
	private MediaPlayer mediaplayer1;
	private MediaPlayer mediaplayer2;
	private MediaPlayer mediaover;
//	private AudioManager am;
	PLAYER pls;
	gameover gm;
	menu me;
	gameoverfront gmf;
	public mysurfaceview(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		speasy = context.getSharedPreferences("easysave", Context.MODE_PRIVATE);
		sphard = context.getSharedPreferences("hardsave", Context.MODE_PRIVATE);
		sfh=this.getHolder();
		sfh.addCallback(this);
		
		mediaplayer1=MediaPlayer.create(context, R.raw.music1);
		mediaplayer1.setLooping(true);
		mediaplayer2=MediaPlayer.create(context, R.raw.music2);
		mediaplayer2.setLooping(true);
		mediaover=MediaPlayer.create(context, R.raw.over);
	
	//	am=(AudioManager)MainActivity.instance.getSystemService(Context.AUDIO_SERVICE);
	//	MainActivity.instance.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		paint=new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);
		paint.setStrokeWidth(50);
		paint.setTextSize(80);
		
		setFocusableInTouchMode(true);
		setKeepScreenOn(true);
		setFocusable(true);
		
	}
	
	
	//enemy en;
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		screenW = this.getWidth();
		screenH = this.getHeight();
		// TODO Auto-generated method stub
		
		init();
		mediaplayer1.start();
		flag=true;
		th = new Thread(this);
		th.start();
		
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		flag=false;
	}
	//	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch(state){
			case START:
				me.onTouchEvent(event);
				break;
			case GAMING:
				pls.onTouchEvent(event);
				break;
			case OVER:
				if(overcount>15)
				gm.onTouchEvent(event);
				break;
		}
		return true;
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK)
		{
			if(state==GAMING||state==OVER)
			{
				init();
				mediaplayer2.pause();
				mediaover.pause();
				mediaplayer1.seekTo(0);
				mediaplayer1.start();
				
				
				
			}
			else if(state == START)
			{
				game22.instance.finish();
				System.exit(0);
				
			}
		}
		return true;
	}


	public void init(){
		state = START;
		k=false;
		cout=0;
		index=0;
		gamecount=0;
		gametime=0;
		mbm = BitmapFactory.decodeResource(this.getResources(), R.drawable.menu);
		mbm1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.menu1);
		mbm2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.menu2);
		simple =BitmapFactory.decodeResource(this.getResources(), R.drawable.simple);
		hard =BitmapFactory.decodeResource(this.getResources(), R.drawable.hard);
		me = new menu(mbm,mbm1,mbm2,simple,hard,0,0);
		
		bmplayer =BitmapFactory.decodeResource(this.getResources(),R.drawable.player);
		pls=new PLAYER(bmplayer,screenW/2,screenH/2);
		
		eplayerl1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.left1);
		eplayerl2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.left2);
		eplayerr1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.right1);
		eplayerr2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.right2);
		boomm = BitmapFactory.decodeResource(this.getResources(), R.drawable.booo);
		
		random = new Random();
		vcenemy = new Vector<enemy>();
		//eplayer4 =
		bmover = BitmapFactory.decodeResource(this.getResources(), R.drawable.over);
		gmf = new gameoverfront(bmover, screenW/2, screenH/2);
		grade = BitmapFactory.decodeResource(this.getResources(), R.drawable.grade);
		grademax = BitmapFactory.decodeResource(this.getResources(), R.drawable.grademax);
		bmover1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.over1);
		bmover2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.over2);
	//	bmover3 = BitmapFactory.decodeResource(this.getResources(), R.drawable.over3);
		
		
		gm = new gameover(grade,grademax,bmover1,bmover2,screenW/2,screenH/2);
		
	}
	public void mydraw(){
		if(pp==1){
			init();
			state=GAMING;
			mediaover.pause();
			mediaplayer2.seekTo(0);
			mediaplayer2.start();
			pp=0;
		}
		if(gg!=0){
			init();
			mediaplayer1.pause();
	        mediaplayer2.seekTo(0);	
			mediaplayer2.start();
			
			
			state=GAMING;
			if(gg==1)
			{	tim=10;
				easym=true;
				hardm=false;
				enemyArray=new int[][]{{6},{3,6},{1,6,6},{6},{1,2,3,4,5,6}
						
						};
			}
			else if(gg==2)
			{	hardm=true;
				easym=false;
				tim=30;
				enemyArray=new int[][]{{1,1,2,2,3,3,4,4,5,5,6,6},{1,2,3,4,5,6,3,3,2,2,5,6,6},{1,3,4,5,5,6,3,2,2,5,6,4}
						,{4,5,5,6,6,5,2,2,2,3,3,4,5,1,1,2,1,1,2,3},{2,4,4,4,3,3,3,2,1,1,2,2,3,4,5,5,5,6,6,6,3,1,1,2,3,4}
					 ,{1,1,1,2,2,2,3,4,4,4,4,5,5,5,5,6,6,6,6,3,3,3,4,5,3},{2,3,4,4,4,4,2,2,2,2,5,5,6,6,6,6,6,6,2,3,4,5,3}
				,{1,1,1,1,1,1,1,1,2,2,3,4,4,4,5,5,5,6,6,5,4,4,4,3,3,3,4},{2,3,4,5,6,2,2,1,1,1,1,1,1,1,2,2,3,3,4,5,5,6,6}
				,{1,1,1,2,2,2,3,3,3,3,4,4,5,5,5,5,6,6,6,6,5,5,5,4,4,4,3,3,3,2,2,2,1,3,2,2,3,3,4,5,6,6,4,4,5},{1,2,4,2,3,3}
				,{3,2,2,2,2,3,3,4,5,5,5,5,5,6,6,6,6,5,6},{2,4,4,5,6,4,3,3,2,3,4,5},{1,2,2,3,4,5,},{4,2,3,4,2,2,2,1,1,}
				,{2,3,4,5,6,1,1,1,1,1,1,2,2,2,3,3,3,3,4,4,4,4,4,4,4,4,4,5,5,5,5,5,5,5,5,5,6,6,6,6,6,2,2,2,2,2,2,2,2,2,2,2}
				,{1,2,3,4,5,6,1,2,3,4,5,6},{1,2,2,3,3,4,4,4,3,2,3,3,2,2,4,4,4,4,5,5,5,5,5,5,5,5,6,6,6,6,3,4,5,6},{4,5,6,2,3}
				,{1,1,1,1,1,1,2,2,2,2,2,2,3,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,5,5,5,5,5,5,5,5,5,6,6,6,6,6,6,6,6,6,6,6,6,3,3,4,4}
				,{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,5,5,5,5,5,6,6,6,6,6}
				};
				
			}
			gg=0;
		}
//		if(bb==1)
//		{
//			init();
//			bb=0;
//		}
		try{
			canvas = sfh.lockCanvas();
			if (canvas != null) {
				
				
				switch(state){
						case START:
								
								
								me.mydraw1(canvas, paint);
								me.mydrawsimple(canvas, paint);
								me.mydrawhard(canvas, paint);
								break;
				
						
						case GAMING:
									if(easym)
										gradeindex = speasy.getInt("high", 0);
									else
										gradeindex = sphard.getInt("high", 0);
							        me.mydraw(canvas, paint);
									canvas.drawText(String.valueOf(gametime), 20, 80, paint);
									pls.mydraw(canvas, paint);
									
									for(int i=0;i<vcenemy.size();i++)
									{
										vcenemy.elementAt(i).mydraw(canvas, paint);
										
									}	
									break;
						case OVER:	
									if(easym){
										if(gametime>speasy.getInt("high", 0))
										{
											speasy.edit().putInt("high", gametime).commit();
											
										}
									}
									else
									{	if(gametime>sphard.getInt("high", 0))
										{
											sphard.edit().putInt("high", gametime).commit();
										
										}
										
									}
									
									if(overcount>15){
										 me.mydraw(canvas, paint);
										gm.grade(canvas, paint);
										gm.myview1(canvas, paint);
										gm.myview2(canvas, paint);
									//	gm.myview3(canvas, paint);
									}
									else{
										me.mydraw2(canvas, paint);
										overcount++;
										gmf.myview(canvas, paint);
									}
									break;
						}
					}}catch (Exception e) {
							// TODO: handle exception
						} finally {
							if (canvas != null)
								sfh.unlockCanvasAndPost(canvas);
						}
		
			
				
			
}
	public void logic()
	{	
		switch(state){
		  		case START:
		  			me.logic();
		  			break;
				case GAMING:
					gamecount++;
					if(gamecount%20==0)
					{
						gametime++;
						gamecount=0;
					}
					pls.logic();
					for(int i=0;i<vcenemy.size();i++)
					{
						if(vcenemy.elementAt(i).isdead)
						{
							vcenemy.removeElementAt(i);
							
						}
						else
						{
							vcenemy.elementAt(i).logic();
							if(pls.iscollsion(vcenemy.elementAt(i))){
								state = OVER;
								overcount=0;
								mediaplayer2.pause();
								mediaover.seekTo(0);
								mediaover.start();
							}
						}
					}
					
					if(k==false){
					cout++;
					if(cout%tim==0)
					{
						for(int i=0;i<enemyArray[index].length;i++)
						{	
							if(enemyArray[index][i]<5){
								y=random.nextInt(screenH-100)+50;
								if(y<screenH/2){
									if(enemyArray[index][i]==1)
									{
										vcenemy.addElement(new enemy(eplayerl1, 1, -50, y));
									}
									
									else if(enemyArray[index][i]==2)
									{
										vcenemy.addElement(new enemy(eplayerl2, 2, -50, y));
									}
									else if(enemyArray[index][i]==3)
									{
										vcenemy.addElement(new enemy(eplayerr1, 3, screenW+50, y));
									}
									else if(enemyArray[index][i]==4)
									{
										vcenemy.addElement(new enemy(eplayerr2, 4, screenW+50, y));
									}	
									
								}
								else 
								{
									if(enemyArray[index][i]==1)
									{
										vcenemy.addElement(new enemy(eplayerr1, 5, -50, y));
									}
									
									else if(enemyArray[index][i]==2)
									{
										vcenemy.addElement(new enemy(eplayerr2, 6, -50, y));
									}
									else if(enemyArray[index][i]==3)
									{
										vcenemy.addElement(new enemy(eplayerl1, 7, screenW+50, y));
									}
									else if(enemyArray[index][i]==4)
									{
										vcenemy.addElement(new enemy(eplayerl2, 8, screenW+50, y));
									}	
									
								}
							}
							else
							{	x=random.nextInt(screenW-100)+50;
								if(enemyArray[index][i]==5)
								{
									vcenemy.addElement(new enemy(eplayerl2, 9, x, screenH+50));
								}
								else
								{
									vcenemy.addElement(new enemy(boomm, 10, x, -50));
								}
							}
							if (index == enemyArray.length - 1) {
								k = true;
							}
							//else
						//	{
						//		vcenemy.add(new enemy(eplayer4, 4, x, y));
						//	}
						}
							
						index++;
					}
				}
					break;
				case OVER:
					if(overcount>15)
						gm.logic();
					else
					    gmf.logic();
					
					break;
			}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(flag)
		{
			long start = System.currentTimeMillis();
			mydraw();
			logic();
			long end = System.currentTimeMillis();
			try{
				if(end - start<50)
				{
					Thread.sleep(50-(end-start));
				}
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
	}
	
	
}