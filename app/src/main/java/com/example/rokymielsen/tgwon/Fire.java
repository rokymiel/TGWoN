package com.example.rokymielsen.tgwon;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by Roky Mielsen on 03.02.2018.
 */

public class Fire {
    float x,y;
    public static boolean crash=false;
    int spriteX,spriteY=0;int xFrame=0,yFrame=0;
    int secFrame=0;
    int xFire;
    int yFire;

    Paint paint= new Paint();

    Bitmap sprites;
    public void draw (Canvas canvas){
        if (crash==true) {
            Rect to = new Rect((int) x - xFire/2, (int) y - yFire/2, (int) x + xFire/2, (int) y + yFire/2);
            Rect frame = new Rect(xFrame * xFire, yFrame * yFire, xFrame * xFire + xFire, yFrame * yFire + yFire);
            if (secFrame % 7 == 0) {
                xFrame++;
                xFrame %= 8;
                if (xFrame % 8 == 0) {
                    yFrame++;
                    yFrame %= 4;
                }
            }

            secFrame++;
            canvas.drawBitmap(sprites, frame, to, paint);
        }
    }
    public Fire(float x, float y, Bitmap sprites){
        this.x=x;
        this.y=y;
        this.sprites=sprites;
        xFire=(sprites.getWidth())/8;
        yFire=(sprites.getHeight())/4;


    }


}
