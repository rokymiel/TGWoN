package com.example.rokymielsen.tgwon;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by Roky Mielsen on 21.04.2018.
 */

public class Hero {
    float r =60;
    float x,y;
    float oldX,oldY;
    float centralX, centralY;
    float vx=0,vy=0;
    Paint paint= new Paint();
    Bitmap sprites;
    Bitmap spritesLag;
    int health=100;
    int xStatic,yStatic;
    Bitmap bitmap;
    int secFrame=0;
    int sec2Frame=0;
    int xFrame=0,eFrame=0;
    boolean shoot=false;
    boolean endShoot=true;
    int xLag;
    int yLag;
    int xAlex;
    int yAlex;
    double angle=0;
    float eX,eY;

    Hero( float x, float y,Bitmap spritesLag, Bitmap sprites,int xStatic,int yStatic ){

        this.xStatic=xStatic;
        this.yStatic=yStatic;
        this.x=x;
        this.y=y;
        centralX=x;
        centralY=y;
        this.sprites=sprites;
        this.spritesLag=spritesLag;
        xLag=(spritesLag.getWidth())/10;
        yLag=spritesLag.getHeight();
        xAlex=(sprites.getWidth())/3;
        yAlex=sprites.getHeight();
        oldX=x;
        oldY=y;
    }

    void move(){


            x+=vx;
            y+=vy;


    }



    void  setEnd(float toX, float toY){
        this.eX=toX;
        this.eY=toY;
        angle= Math.toDegrees(Math.atan((double)(eY-y) / (eX-x)));
        if((eX<x) && (eY<y)){
            angle+=180;
        }
        if ((eX<x)&&(eY>y)){
            angle+=180;
        }

    }
    void draw(Canvas canvas)
    {
        Rect to = new Rect((int) x - (6400/1184)*xStatic, (int) y - (6400/768)*yStatic, (int) x + (6400/1184)*xStatic, (int) y +(6400/768)*yStatic);
        Rect frame = new Rect(xFrame*xLag, 0, xFrame*xLag+xLag, yLag);
        if (secFrame % 8 == 0) {

                xFrame++;
                xFrame %= 10;

        }


        Rect toH = new Rect((int) x - (6400/1184)*xStatic, (int) y - (6400/768)*yStatic, (int) x + (6400/1184)*xStatic, (int) y +(6400/768)*yStatic );
        Rect frameH = new Rect(eFrame*xAlex, 0, eFrame*xAlex+xAlex, yAlex);
        if (sec2Frame%5==0) {
            if (shoot) {
                eFrame++;
                if (eFrame == 3) {
                    eFrame = 0;
                    shoot = false;
                    endShoot=true;
                }
            }
        }
        sec2Frame++;

        secFrame++;

        canvas.save();
        canvas.rotate((float)angle+0+90,x,y);
        canvas.drawBitmap(spritesLag, frame, to, paint);
        canvas.restore();

        canvas.save();
        canvas.rotate((float)angle+0+90,x,y);
        canvas.drawBitmap(sprites, frameH, toH, paint);
        canvas.restore();


    }
    void heroShoot(){
        shoot=true;
        endShoot=false;

    }



}
