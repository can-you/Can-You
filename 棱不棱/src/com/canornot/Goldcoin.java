package com.canornot;

import java.util.HashMap;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;


public class Goldcoin {
	
	float x,y;		//金币位置
	int width,height;	//金币宽高
	float rotation;		//金币旋转角度
	float speed;		//金币掉落的速度
	float rotationSpeed;	//金币旋转速度
	Bitmap bitmapGoldcoin;	//金币位图
	
	static HashMap<Integer, Bitmap> bitmapGoldcoinMap = new HashMap<Integer,Bitmap>();	//金币位图集合
	
	//根据给定的size创建Goldcoin对象
	public static Goldcoin createGoldcoin(float size,Bitmap bitmap,Context context){
		Goldcoin goldcoin = new Goldcoin();
		
		//获取屏幕密度对象DisplayMetrics
		Resources myResources;
		if(context == null){
			myResources = Resources.getSystem();
		}else {
			myResources = context.getResources();
		}
		DisplayMetrics metrics = myResources.getDisplayMetrics();
		
		goldcoin.width = (int) (5 + (float) Math.random() * 50);
        float hwRatio = bitmap.getHeight() / bitmap.getWidth();
        goldcoin.height = (int) (goldcoin.width * hwRatio + 40);
        
        goldcoin.x = (float) Math.random() * (size - goldcoin.width);
        goldcoin.y = 0 - (goldcoin.height + (float) Math.random() * goldcoin.height);
        
        goldcoin.speed = 50 + (float) Math.random() * 150;

        // Flakes start at -90 to 90 degrees rotation, and rotate between -45 and 45
        // degrees per second
        goldcoin.rotation = (float) Math.random() * 180 - 90;
        goldcoin.rotationSpeed = (float) Math.random() * 90 - 45;
        
        goldcoin.bitmapGoldcoin = bitmapGoldcoinMap.get(goldcoin.width);
        if(goldcoin.bitmapGoldcoin == null){
        	goldcoin.bitmapGoldcoin = Bitmap.createScaledBitmap(bitmap,goldcoin.width, goldcoin.height,true);
        	bitmapGoldcoinMap.put(goldcoin.width, goldcoin.bitmapGoldcoin);
        }
		
		return goldcoin;
	}
	
}
