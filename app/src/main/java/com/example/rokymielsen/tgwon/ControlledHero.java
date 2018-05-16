package com.example.rokymielsen.tgwon;

import android.graphics.Bitmap;

/**
 * Created by Roky Mielsen on 21.04.2018.
 */

public class ControlledHero extends Hero {

    float toX,toY;
    ControlledHero(float x, float y, Bitmap bitmap, Bitmap decodeResource) {
        super(x, y,bitmap,decodeResource);
    }


    void setTarget(float toX, float toY){
        this.toX=toX;
        this.toY=toY;
        this.vx=this.toX-this.x;
        this.vy=this.toY-this.y;
        float d =(float)Math.sqrt(vx*vx+vy*vy);
        this.vx=(this.vx/d)*20;
        this.vy=(this.vy/d)*20;
    }

    void move(){
         if((toX-x)*(toX-x)+(toY-y)*(toY-y)<300){
              /*vx=0;
                vy=0;*/
               return ;
             }
             super.move();
    }
}
