package com.example.rokymielsen.tgwon;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.widget.Toast;

public class Bullet {
    private Bitmap bmp;

    /**Позиция*/
    public float x;
    public float y;
    public float ex;
    public float ey;

    /**Скорость по Х=15*/
    private int mSpeed=10;

    public double angle;
    public double angle2;

    /**Ширина*/
    public int width;

    /**Ввыоста*/
    public  int height;

    public TheGame gameView;

    /**Конструктор*/
    public Bullet(TheGame gameView, Bitmap bmp, float bX,float bY,float endX,float endY) {
        this.gameView=gameView;
        this.bmp=bmp;

        this.x = bX;            //позиция по Х
        this.y = bY;
        this.ex = endX;            //позиция по Х
        this.ey = endY; //позиция по У
        this.width = 5;       //ширина снаряда
        this.height = 40;      //высота снаряда

        //угол полета пули в зависипости от координаты косания к экрану
        double otnosh =((double)(ey-y) / (ex-x));
        angle = Math.toDegrees(Math.atan(otnosh));
        angle2 = (Math.atan(otnosh));
        if((ex<x) && (ey<y)){
            angle+=180;
        }
        if ((ex<x)&&(ey>y)){
            angle+=180;
        }
        moveX_Y();

    }

    /**Перемещение объекта, его направление*/
    float vx,vy;
    private void moveX_Y(){
        this.vx=this.ex-this.x;
        this.vy=this.ey-this.y;
        float d =(float)Math.sqrt(vx*vx+vy*vy);
        this.vx=(this.vx/d)*16;
        this.vy=(this.vy/d)*16;
    }

    private void update() {
        this.x+=this.vx;
        this.y+=this.vy;
        //x += mSpeed * Math.cos(angle2);         //движение по Х со скоростью mSpeed и углу заданном координатой angle
        //y += mSpeed * Math.sin(angle2);         // движение по У -//-
    }

    /**Рисуем наши спрайты*/
    Matrix matrix = new Matrix();
    Paint paint=new Paint();
    public void onDraw(Canvas canvas) {

        update();                              //говорим что эту функцию нам нужно вызывать для работы класса
        matrix.setScale(0.3f, 0.3f);
        //Study mathematics, dear young programmer :)

        matrix.postRotate((float) angle+ 0 );
        matrix.postTranslate(x, y);
        paint.setAlpha(255);
        canvas.drawBitmap(bmp, matrix,paint);
    }
}
