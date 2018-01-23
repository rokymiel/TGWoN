package com.example.rokymielsen.tgwon;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

/**
 * Created by Roky Mielsen on 23.01.2018.
 */

public class Rocket {
    float x,y;

    float ex,ey;
    float a,b,c;
    float vy=1,vx=1;
    String rotation;
    static Bitmap pic = ImageManager.get(R.drawable.rocket);
    Rocket(int x,int y,int ex,int ey){
        this.x=x;
        this.y=y;
        this.ex=ex;
        this.ey=ey;
        /*this.rotation=rocket.rotation;*/
        motion();
    }
    void motion(){
        a=(x-ex)/((y-ey)*(y-ey));
        b=-2*a*ey;
        c=x-a*y*y-b*y;
    }

    void move(){
        while (x<ex) {
            vx= a * y * y + b * y + c-x;
            x +=vx;
            y += vy;
        }

    }
    Matrix matrix = new Matrix();
    Paint paint=new Paint();
    void draw(Canvas canvas)
    {
        matrix.setScale(0.2f, 0.2f);
        //Study mathematics, dear young programmer :)
        matrix.postRotate((float)Math.toDegrees(Math.atan2(vy, vx)) + 45);
        matrix.postTranslate(x, y);
        paint.setAlpha(255);
        canvas.drawBitmap(pic, matrix, paint);
    }

}
