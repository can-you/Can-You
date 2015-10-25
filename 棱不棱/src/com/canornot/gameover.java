package com.canornot;

import java.math.MathContext;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;

public class gameover {
	private int x;
	private int y;
	private Bitmap grade,grademax,bmover1,bmover2,bmover3;
	
	private int framew1,frameh1, framew2,frameh2,framew3,frameh3;
	private int fw1,fh1,fw2,fh2;
	private int mx,my;
	private int flag;
	private int findex;
	private int maxf;
	private int count = 0;
   public gameover(Bitmap grade,Bitmap grademax,Bitmap bmover1,Bitmap bmover2,int x,int y)
   {   maxf = 5;
	   
	   framew1 = (int)bmover1.getWidth()/maxf;
	   frameh1 = (int)bmover1.getHeight();
	   framew2 = (int)bmover2.getWidth();
	   frameh2 = (int)bmover2.getHeight();
	  
	   
	   fw1=(int)mysurfaceview.screenW*2/5;
	   fh1=(int)mysurfaceview.screenH*2/5;   
	   fw2=(int)mysurfaceview.screenW*2/4;
	   fh2=(int)mysurfaceview.screenH/7;
	   
	   this.grade = grade;
	   this.grademax = grademax;
	   this.bmover1 = bmover1;
	   this.bmover2 = bmover2;
	 //  this.bmover3 = bmover3;
	   this.x = x;
	   this.y = y;
	   
	   
   } 
 
   public void myview1(Canvas canvas,Paint paint){
	  canvas.save();
	   Matrix mat = new Matrix();
	  mat.postScale((float)mysurfaceview.screenW*2/(5*framew1), (float)mysurfaceview.screenH*2/(5*frameh1));
	 //  canvas.scale((float)mysurfaceview.screenW/(2*framew),(float)mysurfaceview.screenH/(2*frameh));
	  
	   Bitmap dstbmp = Bitmap.createBitmap(bmover1, 0, 0, bmover1.getWidth(), bmover1.getHeight(),  
               mat, true);
	   canvas.clipRect(x-fw1/2, y-fh1/2-mysurfaceview.screenH/4-y/8, x-fw1/2+fw1, y-fh1/2-mysurfaceview.screenH/4+fh1-y/8);
	   
	   canvas.drawBitmap(dstbmp, x-fw1/2-fw1*findex, y-fh1/2-mysurfaceview.screenH/4-y/8, paint);
	   canvas.restore();
   }
   public void grade(Canvas canvas,Paint paint){
	   canvas.drawBitmap(grade,x-grade.getWidth(),y,paint);
	   canvas.drawBitmap(grademax,x-grade.getWidth(),y+grade.getHeight(),paint);
	   
	   canvas.drawText(String.valueOf(mysurfaceview.gametime), x+x/4, y+grade.getHeight()-grade.getHeight()/3, paint);
	   canvas.drawText(String.valueOf(mysurfaceview.gradeindex), x+x/4, y+2*grade.getHeight()-grade.getHeight()/3, paint);
   }
   public void myview2(Canvas canvas,Paint paint){
	  // canvas.save();
	   if(flag==0){
		   Matrix mat = new Matrix();
		   mat.postScale((float)mysurfaceview.screenW*2/(4*framew2), (float)mysurfaceview.screenH/(7*frameh2));
		   Bitmap dstbmp = Bitmap.createBitmap(bmover2, 0, 0, bmover2.getWidth(), bmover2.getHeight()   ,  
	               mat, true);  
		   canvas.drawBitmap(dstbmp, x-fw2/2, y*2-fh2-y/10, paint);
	   }
	   if(flag==1){
	   
		   Matrix mat = new Matrix();
		   mat.postScale((float)0.9*mysurfaceview.screenW*2/(4*framew2), (float)0.9*mysurfaceview.screenH/(6*frameh2));
		   Bitmap dstbmp = Bitmap.createBitmap(bmover2, 0, 0, bmover2.getWidth(), bmover2.getHeight()   ,  
	               mat, true);  
		   canvas.drawBitmap(dstbmp, x-fw2/2+fw2/20,y*2-fh2-y/10+fh2/20, paint);
		   
		   
	   }
	   
	   //canvas.restore();
   }
//   public void myview3(Canvas canvas,Paint paint){
//	   if(flag==0||flag==1){
//		   Matrix mat = new Matrix();
//		   mat.postScale((float)mysurfaceview.screenW*2/(4*framew3), (float)mysurfaceview.screenH/(6*frameh3));
//		   Bitmap dstbmp = Bitmap.createBitmap(bmover3, 0, 0, bmover3.getWidth(), bmover3.getHeight()   ,  
//	               mat, true);  
//		   canvas.drawBitmap(dstbmp, x-fw2/2, y+fh2+y/5, paint);
//	   }
//	   if(flag==2){
//		   Matrix mat = new Matrix();
//		   mat.postScale((float)0.9*mysurfaceview.screenW*2/(4*framew2), (float)0.9*mysurfaceview.screenH/(6*frameh2));
//		   Bitmap dstbmp = Bitmap.createBitmap(bmover3, 0, 0, bmover3.getWidth(), bmover3.getHeight()   ,  
//	               mat, true);  
//		   canvas.drawBitmap(dstbmp, x-fw2/2+fw2/20, y+fh2+y/5+fh2/20, paint);
//		   
//	   }
//   }
   public boolean onTouchEvent(MotionEvent event)
   {		mx=(int)event.getX();
		   my=(int)event.getY();
//	   if(event.getAction()==MotionEvent.ACTION_DOWN||
//			   event.getAction()== MotionEvent.ACTION_MOVE)
//	   {
//		   
//		   if(mx>x-fw2/2 && mx<fw2+x-fw2/2
//				   && my>y+y/10 && my<y+y/10+fh2)
//		   {
//				   flag=1;
//		   }
//		   else
//			   flag=0;
//		   
//	   }
//	   if(flag==1&&event.getAction()==MotionEvent.ACTION_UP)
//	   {
//		   
//		   if(mx>x-fw2/2&&mx<fw2+x-fw2/2)
//		   {
//			   if(my>y+y/10&&my<y+y/10+fh2){
//				   flag=0; 
//				   mysurfaceview.pp=1;
//			   }
//		   }
//		 
//		   
//	   }
	   if(event.getAction()==MotionEvent.ACTION_DOWN||
			   event.getAction()== MotionEvent.ACTION_MOVE)
	   {
		  
		   if(mx>x-fw2/2&&mx<fw2+x-fw2/2)
		   {
			   if(my>y+fh2+y/5&&my<y+fh2+y/5+fh2)
				   flag=1;
		   }
		   
	   }
	   if(flag==1&&event.getAction()==MotionEvent.ACTION_UP)
	   {
		   flag=0;
		   mysurfaceview.pp=1;
		   
	   }
	   return true;
	   
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