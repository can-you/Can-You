package com.canornot;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;
import android.view.WindowManager;


/**
 * 
 * @author Himi
 *
 */
public class Game1_MySurfaceView extends SurfaceView implements Callback, Runnable {
	private SurfaceHolder sfh;
	private Paint paint;
	private Thread th;
	private boolean flag;
	private Canvas canvas;
	private Canvas canvas_hand;
	private int screenW, screenH;
	//首先声明十个容量的位图数组
	private Bitmap fishBmp[] = new Bitmap[10];
	//记录当前播放帧
	private int currentFrame;
	private Bitmap bmpBackGround;
	private Resources res=this.getResources();

	//
	/**
	 * SurfaceView初始化函数
	 */
	public Game1_MySurfaceView(Context context,AttributeSet attrs) {
		super(context,attrs);
		bmpBackGround=BitmapFactory.decodeResource(res, R.drawable.teach_first);
		sfh = this.getHolder();
		sfh.addCallback(this);
		paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setAntiAlias(true);
		setFocusable(true);
		//将每张小鱼帧图生成位图存入小鱼帧数组中
		for (int i = 0; i < fishBmp.length; i++) {
			fishBmp[i] = BitmapFactory.decodeResource(this.getResources(), R.drawable.fish0 + i);
		}
	}
   
	/**
	 * SurfaceView视图创建，响应此函数
	 */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		myDraw();
		screenW = this.getWidth();
		screenH = this.getHeight();
		flag = true;
		//实例线程
		th = new Thread(this);
		//启动线程
		th.start();
	}

	/**
	 * 游戏绘图
	 */
	public void myDraw() {
		try {		
			canvas = sfh.lockCanvas();
			if (canvas != null) {
				canvas.drawColor(Color.WHITE);
				canvas.save();
				canvas.scale((float)screenW/bmpBackGround.getWidth(), (float)screenH/bmpBackGround.getHeight(),0,0);
				canvas.drawBitmap(bmpBackGround, 0, 0, paint);
				canvas.restore();
				canvas.drawBitmap(fishBmp[currentFrame], Game1_First_Descrip.width/2,canvas.getHeight()*3/4, paint);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (canvas != null)
				sfh.unlockCanvasAndPost(canvas);
		}
	}

	/**
	 * 触屏事件监听
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return true;
	}

	/**
	 * 按键事件监听
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 游戏逻辑
	 */
	private void logic() {
		currentFrame++;
		if (currentFrame >= fishBmp.length) {
			currentFrame = 0;
		}
	}

	@Override
	public void run() {
		while (flag) {
			long start = System.currentTimeMillis();
			myDraw();
			logic();
			long end = System.currentTimeMillis();
			try {
				if (end - start < 50) {
					Thread.sleep(50 - (end - start));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * SurfaceView视图状态发生改变，响应此函数
	 */
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}

	/**
	 * SurfaceView视图消亡时，响应此函数
	 */
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		flag = false;
	}
}
