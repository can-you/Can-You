package com.canornot;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

public class gameoverfront {
	private int maxf;
	private int framew,frameh;
	private int fw,fh;
	private Bitmap bmover;
	private int x,y;
	private int count;
	private int findex;
	public gameoverfront(Bitmap bmover,int x,int y){
		 maxf = 2;
		   framew = (int)bmover.getWidth()/maxf;
		   frameh = (int)bmover.getHeight();
		   fw=(int)mysurfaceview.screenW/2;
		   fh=(int)mysurfaceview.screenH/6;
		   this.bmover = bmover;
		   this.x = x;
		   this.y = y;
	}
	 public void myview(Canvas canvas,Paint paint){
	   	  Matrix mat = new Matrix();
		  mat.postScale((float)mysurfaceview.screenW/(2*framew), (float)mysurfaceview.screenH/(6*frameh));
		 //  canvas.scale((float)mysurfaceview.screenW/(2*framew),(float)mysurfaceview.screenH/(2*frameh));
		  
		   Bitmap dstbmp = Bitmap.createBitmap(bmover, 0, 0, bmover.getWidth(), bmover.getHeight(),  
	               mat, true);
		   canvas.clipRect(x-fw/2-x/4, y*2-fh-y/10, x-fw/2-x/4+fw, y*2-fh-y/10+fh);
		   
		   canvas.drawBitmap(dstbmp, x-fw/2-x/4-fw*findex, y*2-fh-y/10, paint);
  }
	  public void logic()
	   {
		   		count++;
		   		if(count%2==0)
		   		{
		   			
		   			count=0;
		   			findex++;
		   		}
			   if(findex==maxf)
				   findex=0;
	   }

}
