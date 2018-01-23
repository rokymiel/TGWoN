package com.example.rokymielsen.tgwon;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Roky Mielsen on 23.01.2018.
 */

public class MyDraw extends View{
    Sky sky;
    Rocket rocket;
    public MyDraw(Context context, AttributeSet attrs) {
        super(context,attrs);
        sky =new Sky();
        sky.makeSky();
        rocket= new Rocket(10,10,100,50);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        sky.drawSky(canvas);
        rocket.draw(canvas);
        rocket.move();

        invalidate();

    }
}
