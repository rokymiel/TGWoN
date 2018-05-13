package com.example.rokymielsen.tgwon;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Enemy {
    float x,y;
    float r =50;
    float vx=0,vy=0;
    float endX=300, endY=300;
    Paint paint= new Paint();
    Enemy(float x, float y){
        this.x=x;
        this.y=y;
        paint.setColor(Color.RED);

    }
    void setSpeed(float endX, float endY){
        this.endX=endX;
        this.endY=endY;
        this.vx=this.endX-this.x;
        this.vy=this.endY-this.y;
        float d =(float)Math.sqrt(vx*vx+vy*vy);
        this.vx=(this.vx/d)*3;
        this.vy=(this.vy/d)*3;

    }
    void move(){
        if((endX-x)*(endX-x)+(endY-y)*(endY-y)<20){
              /*vx=0;
                vy=0;*/
            return ;
        }

        x+=vx;
        y+=vy;
    }
    void draw(Canvas canvas){
        canvas.drawCircle(x,y,r,paint);

    }
}
