package com.example.rokymielsen.tgwon;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Roky Mielsen on 03.02.2018.
 */

public class Fire {
    float x,y;
    public static boolean crash=false;
    int spriteX,spriteY=0;int xFrame=0,yFrame=0;
    int secFrame=0;
    Paint paint= new Paint();

    Bitmap sprites;
    public void draw (Canvas canvas){
        if (crash==true) {
            Rect to = new Rect((int) x - 64, (int) y - 128, (int) x + 64, (int) y + 128);
            Rect frame = new Rect(xFrame * 128, yFrame * 256, xFrame * 128 + 128, yFrame * 256 + 256);
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

    }


}
