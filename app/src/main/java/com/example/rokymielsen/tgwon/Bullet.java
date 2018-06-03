package com.example.rokymielsen.tgwon;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.widget.Toast;

public class Bullet {
    private Bitmap bmp;

    public float x;
    public float y;
    public float ex;
    public float ey;
    public double angle;
    public double angle2;
    public int width;
    public  int height;
    float vx,vy;
    public TheGame gameView;
    public Bullet(TheGame gameView, Bitmap bmp, float bX,float bY,float endX,float endY) {
        this.gameView=gameView;
        this.bmp=bmp;

        this.x = bX;
        this.y = bY;
        this.ex = endX;
        this.ey = endY;
        this.width = (int)(bmp.getWidth()*0.3f);
        this.height = (int)(bmp.getHeight()*0.3f);


        double otnosh =((double)(ey-y) / (ex-x));
        angle = Math.toDegrees(Math.atan(otnosh));
        angle2 = (Math.atan(otnosh));
        if((ex<x) && (ey<y)){
            angle+=180;
        }
        if ((ex<x)&&(ey>y)){
            angle+=180;
        }
        if (ex<x&&ey==y){
            angle+=180;
        }
        moveX_Y();

    }
    float staticSpeedX=13*(gameView.scaleWidth/1184), staticSpeedY=16*(gameView.scaleHeight/768);



    private void moveX_Y(){
        this.vx=this.ex-this.x;
        this.vy=this.ey-this.y;
        float d =(float)Math.sqrt(vx*vx+vy*vy);
        this.vx=(this.vx/d)*staticSpeedX;
        this.vy=(this.vy/d)*staticSpeedX;
    }

    private void update() {
        this.x+=this.vx;
        this.y+=this.vy;
    }


    Matrix matrix = new Matrix();
    Paint paint=new Paint();
    public void onDraw(Canvas canvas) {
        update();
        matrix.setScale(0.22f, 0.22f);
        matrix.postRotate((float) angle+ 0 );
        matrix.postTranslate(x, y);
        paint.setAlpha(255);
        canvas.drawBitmap(bmp, matrix,paint);
    }
}
