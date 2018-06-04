package com.example.rokymielsen.tgwon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;

public class BackRect  implements Drawable {
    float x,y,eX,eY;
    Paint paint= new Paint();
    Paint backPaint= new Paint();

    @SuppressLint("ResourceAsColor")
    public BackRect(float scaleWidth, float scaleHeight, float distance){
        this.x =distance;
        this.y = distance;
        this.eX=scaleWidth-distance;
        this.eY=scaleHeight-distance;
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(distance/2);
        paint.setStyle(Paint.Style.STROKE);
        backPaint.setColor(Color.parseColor("#3b2822"));

    }


    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(x,y,eX,eY,backPaint);
        canvas.drawRect(x,y,eX,eY,paint);
    }
}
