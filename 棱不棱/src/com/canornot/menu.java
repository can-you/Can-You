package com.canornot;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.MotionEvent;

public class menu {
	
	private Bitmap mbm,mbm1,mbm2,mbm3;
	private Bitmap simple,hard;
	private int x,y;
	private int framew,frameh,framew1,frameh1,framew2,frameh2,framew3,frameh3,framebtnh,framebtnw;
	private int mx,my;
	private float p,q;
	private int flag;
	private int maxf;
	private int count;
	private int findex;
	private int fw1,fh1,fbtnw,fbtnh;
	public menu(Bitmap mbm, Bitmap mbm1,Bitmap mbm2,Bitmap mbm3,Bitmap simple,Bitmap hard, int x, int y) {
		// TODO Auto-generated constructor stubframew=mbm.getWidth();
		maxf=4;
		frameh=mbm.getHeight();
		framew=mbm.getWidth();
		frameh2=mbm2.getHeight();
		framew2=mbm2.getWidth();
		frameh3=mbm3.getHeight();
		framew3=mbm3.getWidth();
		framew1=mbm1.getWidth()/maxf;
		frameh1=mbm1.getHeight();
		fw1=mysurfaceview.screenW;
		fh1=mysurfaceview.screenH;
		
		framebtnw = simple.getWidth();
		framebtnh = simple.getHeight();
		fbtnw =mysurfaceview.screenW/2;
		fbtnh = mysurfaceview.screenH/6;
				
		this.mbm=mbm;
		this.mbm1=mbm1;
		this.mbm2=mbm2;
		this.mbm3=mbm3;
		this.simple = simple;
		this.hard = hard;
		this.x=x;
		this.y=y;
	}
	public void mydraw(Canvas canvas,Paint paint)
	{	
		//canvas.clipRect(0,0,framew,frameh);
		p=((float)mysurfaceview.screenW)/framew;
		q=((float)mysurfaceview.screenH)/frameh;
		//p=(float) 0.7;
		//q=(float)1;
		Matrix mat = new Matrix();
		mat.postScale(p, q);
		Bitmap bstm = Bitmap.createBitmap(mbm , 0,0,mbm.getWidth(),mbm.getHeight(),
				 mat,true);
		canvas.drawBitmap(bstm, x, y, paint);
		
	
	}
	
	public void mydraw1(Canvas canvas,Paint paint)
	{
		p=((float)mysurfaceview.screenW)/framew1;
		q=((float)mysurfaceview.screenH)/frameh1;
		//p=(float) 0.7;
		//q=(float)1;
		Matrix mat = new Matrix();
		mat.postScale(p, q);
		Bitmap bstm = Bitmap.createBitmap(mbm1 , 0,0,mbm1.getWidth(),mbm1.getHeight(),
				 mat,true);
		
		canvas.drawBitmap(bstm, 0-fw1*findex, 0, paint);
		
	}
	public void mydraw2(Canvas canvas,Paint paint)
	{	
		//canvas.clipRect(0,0,framew,frameh);
		p=((float)mysurfaceview.screenW)/framew2;
		q=((float)mysurfaceview.screenH)/frameh2;
		//p=(float) 0.7;
		//q=(float)1;
		Matrix mat = new Matrix();
		mat.postScale(p, q);
		Bitmap bstm = Bitmap.createBitmap(mbm2 , 0,0,mbm2.getWidth(),mbm2.getHeight(),
				 mat,true);
		canvas.drawBitmap(bstm, x, y, paint);
		
	
	}
	public void mydraw3(Canvas canvas,Paint paint)
	{	
		//canvas.clipRect(0,0,framew,frameh);
		p=((float)mysurfaceview.screenW)/framew3;
		q=((float)mysurfaceview.screenH)/frameh3;
		//p=(float) 0.7;
		//q=(float)1;
		Matrix mat = new Matrix();
		mat.postScale(p, q);
		Bitmap bstm = Bitmap.createBitmap(mbm3 , 0,0,mbm3.getWidth(),mbm3.getHeight(),
				 mat,true);
		canvas.drawBitmap(bstm, x, y, paint);
		
	
	}
	public void mydrawsimple(Canvas canvas,Paint paint)
	{
		p=((float)mysurfaceview.screenW)/(framebtnw*2);
		q=((float)mysurfaceview.screenH)/(framebtnh*6);
		//p=(float) 0.7;
		//q=(float)1;
		Matrix mat = new Matrix();
		mat.postScale(p, q);
		Bitmap bstm = Bitmap.createBitmap(simple , 0,0,simple.getWidth(),simple.getHeight(),
				 mat,true);
		canvas.drawBitmap(bstm, mysurfaceview.screenW/2-fbtnw/2, mysurfaceview.screenH/10, paint);
		
	}
	public void mydrawhard(Canvas canvas,Paint paint)
	{
		p=((float)mysurfaceview.screenW)/(framebtnw*2);
		q=((float)mysurfaceview.screenH)/(framebtnh*6);
		//p=(float) 0.7;
		//q=(float)1;
		Matrix mat = new Matrix();
		mat.postScale(p, q);
		Bitmap bstm = Bitmap.createBitmap(hard , 0,0,hard.getWidth(),hard.getHeight(),
				 mat,true);
		canvas.drawBitmap(bstm, mysurfaceview.screenW/2-fbtnw/2,
				mysurfaceview.screenH/10+fbtnh+mysurfaceview.screenH/15, paint);
		
	}
	boolean onTouchEvent(MotionEvent event){
		if(event.getAction()==MotionEvent.ACTION_DOWN)
		{
			mx=(int) event.getX();
			my=(int) event.getY();
			if(mx> mysurfaceview.screenW/2-fbtnw/2&&mx< mysurfaceview.screenW/2-fbtnw/2+fbtnw)
			{
				if(my>mysurfaceview.screenH/10&&my<mysurfaceview.screenH/10+fbtnh)
				{
					flag=1;
				}
				if(my>mysurfaceview.screenH/10+fbtnh+mysurfaceview.screenH/15
						&&my<mysurfaceview.screenH/10+fbtnh+mysurfaceview.screenH/15+fbtnh)
				{
					flag=2;
				}
			}
			
				
			
			
			
		}
		if(flag!=0&&event.getAction()==MotionEvent.ACTION_UP)
		{
			if(flag==1)
				mysurfaceview.gg=1;
			else if(flag==2)
				mysurfaceview.gg=2;
			flag=0;
		}
		
		return true;
		
	}
	public void logic()
	{	findex++;
		if(findex==maxf)
		{
			findex = 0;
		}
	}
	

}