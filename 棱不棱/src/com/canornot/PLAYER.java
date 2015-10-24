package com.canornot;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PLAYER {
	//private SurfaceHolder sfh;
	//public Paint paint;
	//public Canvas canvas;
	private int x;
	private int y;
	private Bitmap bmplayer;
	private int m;
	private int n;
	private boolean ispress = false;
	public int xf;
	public int yf;
	private int framew,frameh;
	private int fw,fh;
	private int maxf;
	private int index;
	private int count;
	public PLAYER(Bitmap bmplayer, int x,int y)
	{   maxf = 6;
		count=0;
		framew = bmplayer.getWidth()/maxf;
		frameh = bmplayer.getHeight();
		fw = mysurfaceview.screenW/4;
		fh = mysurfaceview.screenH/6;
		this.x=x;
		this.y=y;
		this.bmplayer=bmplayer;
	
	}
	public void mydraw(Canvas canvas,Paint paint)
	{	canvas.save();
		Matrix mat = new Matrix();
		mat.postScale((float)mysurfaceview.screenW/(4*framew),(float)mysurfaceview.screenH/(6*frameh));
		Bitmap dstbmp = Bitmap.createBitmap(bmplayer, 0, 0, bmplayer.getWidth(), bmplayer.getHeight(),  
	               mat, true);
		canvas.clipRect(x-fw/2, y-fh/2, x-fw/2+fw, y-fh/2+fh);
		canvas.drawBitmap(dstbmp,x-fw/2-index*fw,y-fh/2, paint);		
		
		canvas.restore();
	}
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
			if(event.getAction()==MotionEvent.ACTION_DOWN)
			{
				m=(int) event.getX();
				n=(int) event.getY();
				if(m > (x-fw/2) && m < (x+fw/2))
				{
					if(n > (y-fh/2) && n < (y+fh/2) )
					ispress=true;
				}
			}
			if(ispress && event.getAction()==MotionEvent.ACTION_MOVE){
					x=(int) event.getX();
					y=(int) event.getY();
			}
			if(event.getAction()==MotionEvent.ACTION_UP)
			{
				ispress=false;
			}
		
		
		
		return true;
	}
	int x1,y1;
	public boolean iscollsion(enemy en)
	{
		 x1=en.getx()+en.framew/2;
		 y1=en.gety()+en.frameh/2;
		if(x1<x-fw/2+fw/8)
			return false;
		else if(y1<y-fh/2+fw/8)
			return false;
		else if(x1>x+fw/2-fw/8)
			return false;
		else if(y1>y+fh/2-fh/8)
			return false;
		else
			return true;
		
		
		
	}
	public void logic()
	{	count++;
		if(count%4==0){
			index++;
			count=0;
		}
		if(index==maxf)
			index=0;
	}
	
}