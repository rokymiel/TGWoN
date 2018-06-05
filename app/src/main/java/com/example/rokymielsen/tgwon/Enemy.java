package com.example.rokymielsen.tgwon;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Enemy {
    float x,y;
    float r =50;
    float vx=0,vy=0;
    float endX=300, endY=300;
    int health=100;
    Paint paint= new Paint();
    public int height;
    public int width;
    Bitmap sprites;
    Bitmap spritesLag;

    int secFrame=0;
    int sec2Frame=0;
    int xFrame=0,eFrame=0;
    boolean shoot=false;
    boolean endShoot=true;
    int xLag;
    int yLag;
    int xEnemy;
    int yEnemy;
    double angle=0;
    int xStatic,yStatic;
    boolean enemyWallStop=false;


    Enemy(float x, float y,Bitmap spritesLag, Bitmap sprites,int xStatic,int yStatic){
        this.x=x;
        this.y=y;
        this.height= (int) ( 2*r);
        this.width=this.health;
        this.sprites=sprites;
        this.spritesLag=spritesLag;
        xLag=(spritesLag.getWidth())/10;
        yLag=spritesLag.getHeight();
        xEnemy=(sprites.getWidth())/3;
        yEnemy=sprites.getHeight();
        this.xStatic=xStatic;
        this.yStatic=yStatic;

        paint.setColor(Color.RED);

    }
    float endHY, endHX;
    void setAngle(float endHX,float endHY){
        this.endHY=endHY;
        this.endHX=endHX;


        angle= Math.toDegrees(Math.atan((double)(endHY-y) / (endHX-x)));
        if((endHX<x) && (endHY<y)){
            angle+=180;
        }
        if ((endHX<x)&&(endHY>y)){
            angle+=180;
        }
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
        Rect to = new Rect((int) x - (6400/1184)*xStatic, (int) y - (6400/768)*yStatic, (int) x + (6400/1184)*xStatic, (int) y +(6400/768)*yStatic );
        Rect frame = new Rect(xFrame*xLag, 0, xFrame*xLag+xLag, yLag);
        if (secFrame % 8 == 0) {

            xFrame++;
            xFrame %= 10;

        }
        Rect toH = new Rect((int) x - (6400/1184)*xStatic, (int) y - (6400/768)*yStatic, (int) x + (6400/1184)*xStatic, (int) y +(6400/768)*yStatic  );
        Rect frameH = new Rect(eFrame*xEnemy, 0, eFrame*xEnemy+xEnemy, yEnemy);
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


}
