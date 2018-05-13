package com.example.rokymielsen.tgwon;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Roky Mielsen on 23.01.2018.
 */

public class MyDraw extends View{
    Sky sky;
    Rocket rocket;
    Rocket rocket1;
    public MyDraw(Context context, AttributeSet attrs) {
        super(context,attrs);
        sky =new Sky();
        sky.makeSky();
        rocket= new Rocket(-50,-20,1000,300, BitmapFactory.decodeResource(getResources(), R.drawable.rocket));
        fire = new Fire(920,307,BitmapFactory.decodeResource(getResources(), R.drawable.fire));


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
