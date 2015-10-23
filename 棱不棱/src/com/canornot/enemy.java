package com.canornot;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;


public class enemy {
	private static final int dogleft1u_type = 1;
	private static final int dogleft2u_type = 2;
	private static final int dogright1u_type = 3;
	private static final int dogright2u_type = 4;
	private static final int dogleft1d_type = 5;
	private static final int dogleft2d_type = 6;
	private static final int dogright1d_type = 7;
	private static final int dogright2d_type = 8;
	private static final int dogdown_type = 9;
	private static final int boom_type = 10;
	
	private static final int dog1_speed = 20;
	private static final int dog2_speed = 30;
	private static final int boom_speed = 80;
	
	private Bitmap bmenemy;
	private int x;
	private int y;
	//public int fw,fh;
	private int type;
	private int speed;
	public int framew;
	public int frameh;
	public boolean isdead;
	Random ra;
	//private int findex;
	//private int maxf;
	private int count;
	public enemy(Bitmap bmenemy, int type, int x ,int y)
	{	//maxf=6;
		ra = new Random();
		this.bmenemy = bmenemy;
		this.x = x;
		this.y = y;
		this.type = type;
		framew= bmenemy.getWidth();
		frameh = bmenemy.getHeight();
		switch (type)
		{
		 	case dogleft1u_type:	
		 	case dogleft1d_type:
		 	case dogright1u_type:
		 	case dogright1d_type:
		 	case dogdown_type:
		 		
		 		speed = dog1_speed;
//		 		fw=mysurfaceview.screenW/3;
//				fh=mysurfaceview.screenH/4;
		 		break;
		 	case dogleft2u_type:	
		 	case dogleft2d_type:
		 	case dogright2u_type:
		 	case dogright2d_type:
		 		
		 		speed = dog2_speed;
//		 		fw=mysurfaceview.screenW/2;
//				fh=mysurfaceview.screenH/4;
		 		break;
		 	case boom_type:
		 		speed = boom_speed;
//		 		framew*=maxf; 
		 		break;
		 	
		 	
		}
		
	}
	public void mydraw(Canvas canvas,Paint paint)
	{	
//		switch (type)
//		{
//		 	case dogleft1u_type:	
//		 	case dogleft1d_type:
//		 	case dogright1u_type:
//		 	case dogright1d_type:
//		 	case dogdown_type:
//		 		canvas.save();
//		 		Matrix mat = new Matrix();
//		 		mat.postScale((float)mysurfaceview.screenW/(3*framew),(float)mysurfaceview.screenH/(4*frameh));
//		 		Bitmap bmpf = Bitmap.createBitmap(bmenemy, 0, 0, bmenemy.getWidth(), bmenemy.getHeight(), 
//		 				mat, true);
//		 		canvas.clipRect(x,y, x+fw, y+fh);
//		 		canvas.drawBitmap(bmpf, x-findex*fw, y, paint);
//		 		canvas.restore();
//		 		canvas.drawBitmap(bmenemy, x, y, paint);
//		 		break;
//		 	case dogleft2u_type:	
//		 	case dogleft2d_type:
//		 	case dogright2u_type:
//		 	case dogright2d_type:	
//		 		canvas.save();
//		 		Matrix mat2 = new Matrix();
//		 		mat2.postScale((float)mysurfaceview.screenW/(2*framew),(float)mysurfaceview.screenH/(4*frameh));
//		 		Bitmap bmpf2 = Bitmap.createBitmap(bmenemy, 0, 0, bmenemy.getWidth(), bmenemy.getHeight(), 
//		 				mat2, true);
//		 		canvas.clipRect(x,y, x+fw, y+fh);
//		 		canvas.drawBitmap(bmpf2, x-findex*fw, y, paint);
//		 		canvas.restore();
//		 		canvas.drawBitmap(bmenemy, x, y, paint);
//		 		break;
//		 		
//		 	case boom_type:
//		 		Matrix mat3 = new Matrix();
//		 		mat3.postScale((float)mysurfaceview.screenW/(5*bmenemy.getWidth()),(float)mysurfaceview.screenH/(8*bmenemy.getHeight()));
//		 		Bitmap bmpf3 = Bitmap.createBitmap(bmenemy, 0, 0, bmenemy.getWidth(), bmenemy.getHeight(), 
//		 				mat3, true);
		 		canvas.drawBitmap(bmenemy, x, y, paint);
//		 		break;
		 	
		 	
//		}
	}
	public void logic()
	{	
		
		switch(type)
		{
			case dogleft1u_type:
			case dogleft2u_type:
				x += speed;
				y += ra.nextInt(speed);
				if(x>mysurfaceview.screenW){
					isdead=true;
					
				}
				break;
			
			case dogleft1d_type:
			case dogleft2d_type:
				x += speed;
				y -= ra.nextInt(speed);
				if(x>mysurfaceview.screenW){
					isdead=true;
				
				}
				break;
			
			case dogright1u_type:
			case dogright2u_type:
				x -= speed;
				y += ra.nextInt(speed);
				if(x<-50){
					isdead=true;
					
				}
				break;
			case dogright1d_type:
			case dogright2d_type:
				x -= speed;
				y -= ra.nextInt(speed);
				if(x<-50){
					isdead=true;
					
				}
				break;
			case dogdown_type:
				x+= speed/3;
				y-= speed+speed/3;
				if(x>mysurfaceview.screenW||y<-50){
					isdead=true;
					
				}
				break;
			case boom_type:
				speed-=ra.nextInt(3);
				y+=speed;
				if(y<-50){
					isdead=true;
					
				}
				break;
		}
	}
	public int getx()
	{
		return x;
	}
	public int gety()
	{
		return y;
	}
	
	
	

}