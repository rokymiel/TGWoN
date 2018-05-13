package com.example.rokymielsen.tgwon;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

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
    Hero( /*Bitmap sprites,*//*Canvas canvas*/float x, float y ){
       // this.sprites= sprites;
        /*x=canvas.getWidth()/2;
        y=canvas.getHeight()/2;*/
        this.x=x;
        this.y=y;
        centralX=x;
        centralY=y;
        paint.setColor(Color.YELLOW);
    }

    void move(){
       // if ((vx+x>centralX-50 && vx+x<centralX+50)&&(vy+y>centralY-50&& vy+y<centralY+50)){
            x+=vx;
            y+=vy;

       // }

    }



    void draw(Canvas canvas)
    {
       canvas.drawCircle(x,y,r,paint);


    }


}
