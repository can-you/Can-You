package com.canornot;

import java.util.ArrayList;
import java.util.List;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.View;

public class GoldcoinView extends View{
	
	Bitmap goldcoinBitmap;	//所有金币使用的位图
	int numGoidcoins;	//金币数
	List<Goldcoin> goldcoinList = new ArrayList<Goldcoin>();	//当前金币的集合

	ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1);
	long startTime,previousTime;
	int frames;	//用于记录祯
	Paint textPaint;
	float fps;	//每秒帧数
	Matrix matrix = new Matrix();	//移动和旋转金币所用到的矩阵
	
	
	public GoldcoinView(Context context){
		super(context);
		//初始化资源
		goldcoinBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.goldcoin);
		textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);	//抗锯齿
		textPaint.setColor(Color.WHITE);
		textPaint.setTextSize(24);
		
		valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				long currentTime = System.currentTimeMillis();
				float secs = (float)(currentTime - previousTime) /100f;
				previousTime = currentTime;
				for(int i = 0;i < numGoidcoins;i++){
					Goldcoin goldcoin = goldcoinList.get(i);
					goldcoin.y += (goldcoin.speed * secs);
					
					if(goldcoin.y > getWidth()){
						goldcoin.y = 0 - goldcoin.height;
					}
					
					goldcoin.rotation = goldcoin.rotation + (goldcoin.speed * secs);
				}
				invalidate();
				
				valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
				valueAnimator.setDuration(20000);
			}
		});
	}
	
	int getNumGoldcoins() {
        return numGoidcoins;
    }

    private void setNumGoldcoins(int quantities) {
        numGoidcoins = quantities;
    }
	
	public void addGoldcoins(int quantities){
		for(int i = 0;i < quantities;i++){
			goldcoinList.add(Goldcoin.createGoldcoin(getWidth(),goldcoinBitmap, getContext()));
		}
		setNumGoldcoins(numGoidcoins + quantities);
	}
	
	public void subtractFlakes(int quantities) {
        for (int i = 0; i < quantities; ++i) {
            int index = numGoidcoins - i - 1;
            goldcoinList.remove(index);
        }
        setNumGoldcoins(numGoidcoins - quantities);
    }
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		goldcoinList.clear();
        numGoidcoins = 0;
        addGoldcoins(16);
        valueAnimator.cancel();
        startTime = System.currentTimeMillis();
        previousTime = startTime;
        frames = 0;
        valueAnimator.start();
	}
	
	@Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < numGoidcoins; ++i) {
            Goldcoin goldcoin = goldcoinList.get(i);
            matrix.setTranslate(-goldcoin.width / 2, -goldcoin.height / 2);
            matrix.postRotate(goldcoin.rotation);
            matrix.postTranslate(goldcoin.width / 2 + goldcoin.x, goldcoin.height / 2 + goldcoin.y);
            canvas.drawBitmap(goldcoin.bitmapGoldcoin, matrix, null);
        }
        ++frames;
        long nowTime = System.currentTimeMillis();
        long deltaTime = nowTime - startTime;
        if (deltaTime > 1000) {
            float secs = (float) deltaTime / 1000f;
            fps = (float) frames / secs;
            startTime = nowTime;
            frames = 0;
        }
    }

    public void pause() {
        valueAnimator.cancel();
    }

    public void resume() {
        valueAnimator.start();
    }

	
}
