package com.example.rokymielsen.tgwon;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by Roky Mielsen on 23.01.2018.
 */

public class Rocket {
    float x,y;
    float saveX,saveY;
    boolean end;
    float ex,ey;
    float a,b,c;
    float vy= (float) 1.5,vx=1;
    String rotation;
    Bitmap pic;

    public int width;

    public  int heigh;

    Rocket(int x,int y,int ex,int ey,Bitmap pic){

        this.y=y;
        this.x=x;
        this.ex=ex;
        this.ey=ey;
        this.pic= pic;
        saveX=ex;
        saveY=ey;
        heigh=(int)(pic.getHeight()*0.2f);
        width=(int) (pic.getWidth()*0.2f);
        /*this.rotation=rocket.rotation;*/
        motion();
    }
    void motion(){

        a=(x-ex)/((y-ey)*(y-ey));
        b=-2*a*ey;
        c=x-a*y*y-b*y;
    }
    void gag(){final String TAG= "Rocket";
        Log.d(TAG,"motion");}


    void move(){
         if (x<ex && y<ey){
            vx= a * y * y + b * y + c-x;

            x +=vx;
            y += vy;
         }
         else {Fire.crash=true;}






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
