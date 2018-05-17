package com.example.rokymielsen.tgwon;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import static android.content.ContentValues.TAG;

/**
 * Created by Roky Mielsen on 23.01.2018.
 */

public class MyDraw extends View{
    Sky sky;
    Rocket rocket;
    Rocket rocket1;
    DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
    static int scaleWidth;
    static int scaleHeight;
    static int xStatic;
    static int yStatic;

    public MyDraw(Context context, AttributeSet attrs) {
        super(context,attrs);
        sky =new Sky();
        sky.makeSky();
        scaleWidth=displaymetrics.widthPixels;
        scaleHeight=displaymetrics.heightPixels;
        xStatic =scaleWidth/1184;
        yStatic=scaleHeight/768;

        Log.d(TAG,displaymetrics.widthPixels+"><><");
        Log.d(TAG,displaymetrics.heightPixels+"><><");
        rocket= new Rocket(-50,-20,1000,300, BitmapFactory.decodeResource(getResources(), R.drawable.rocket));
        fire = new Fire(rocket.ex-(int)(rocket.width/(1.4)),rocket.ey,BitmapFactory.decodeResource(getResources(), R.drawable.fire));


    }
    Fire fire;

    @Override
    protected void onDraw(Canvas canvas) {
        sky.drawSky(canvas);
        rocket.draw(canvas);
        rocket.move();
        fire.draw(canvas);

        invalidate();

    }
}
