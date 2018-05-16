package com.example.rokymielsen.tgwon;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Roky Mielsen on 21.04.2018.
 */

public class Hero {
    float r =60;
    float x,y;
    float centralX, centralY;
    float vx=0,vy=0;
    Paint paint= new Paint();
    Bitmap sprites;
    Bitmap spritesLag;
    Hero( /*Bitmap sprites,*//*Canvas canvas*/float x, float y,Bitmap spritesLag, Bitmap sprites ){
       // this.sprites= sprites;
        /*x=canvas.getWidth()/2;
        y=canvas.getHeight()/2;*/
        this.x=x;
        this.y=y;
        centralX=x;
        centralY=y;
        this.sprites=sprites;
        this.spritesLag=spritesLag;
        //paint.setColor(Color.YELLOW);
    }

    void move(){
       // if ((vx+x>centralX-50 && vx+x<centralX+50)&&(vy+y>centralY-50&& vy+y<centralY+50)){
            x+=vx;
            y+=vy;

       // }

    }
    Bitmap bitmap;
    int secFrame=0;
    int xFrame=0,yFrame=0;


    void draw(Canvas canvas)
    {
        Rect to = new Rect((int) x - 64, (int) y - 64, (int) x + 64, (int) y +64 );
        Rect frame = new Rect(xFrame*64, 0, xFrame*64+64, 64);
        if (secFrame % 10 == 0) {
            xFrame++;
            xFrame %= 10;
            /*if (xFrame % 8 == 0) {
                yFrame++;
                yFrame %= 4;
            }*/
        }
        Rect toH = new Rect((int) x - 64, (int) y - 64, (int) x + 64, (int) y +64 );
        Rect frameH = new Rect(0, 0, 64, 64);


        secFrame++;


        canvas.drawBitmap(spritesLag, frame, to, paint);
        canvas.drawBitmap(sprites, frameH, toH, paint);


       //canvas.drawCircle(x,y,r,paint);


    }


}
