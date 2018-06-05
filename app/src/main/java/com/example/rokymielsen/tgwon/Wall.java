package com.example.rokymielsen.tgwon;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Wall implements Drawable {
    float x,y,eX,eY;
    Paint paint= new Paint();
    public Wall(float x, float y, float eX, float eY){
        this.x=x;
        this.y=y;
        this.eX=eX;
        this.eY=eY;
        paint.setColor(Color.GRAY);
    }


    @Override
    public void draw(Canvas canvas) {

        canvas.drawRect(x,y,eX,eY,paint);
    }
}
