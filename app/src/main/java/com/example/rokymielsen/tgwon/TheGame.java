package com.example.rokymielsen.tgwon;

        import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.Canvas;
        import android.util.AttributeSet;
        import android.view.MotionEvent;
        import android.view.View;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.Iterator;
        import java.util.List;
        import java.util.Random;

/**
 * Created by Roky Mielsen on 21.04.2018.
 */

public class TheGame extends View implements Runnable{
    ControlledHero hero;
    int enemiesCount;
    int killCount=0;
    private List<Bullet> ball = new ArrayList<Bullet>();
    private List<Enemy> enemy = new ArrayList<Enemy>();
    float shotX,shotY;
    TextView text;


    public TheGame(Context context, AttributeSet attrs/*,Canvas canvas*/) {
        super(context,attrs);


        hero= new ControlledHero(500,400);
        enemiesCount=1;
        enemy.add(new Enemy(900,200));
        thread.start();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        hero.draw(canvas);
         hero.move();
        testCollision();
       // text.setText(killCount);
        Iterator<Bullet> j = ball.iterator();
        while(j.hasNext()) {
            Bullet b = j.next();
           // Toast.makeText(getContext(), j+"", Toast.LENGTH_LONG).show();
           // Toast.makeText(getContext(), b.x+"", Toast.LENGTH_LONG).show();

            if(b.x <= 2500 && b.x>=-100 && b.y >=-100 && b.y <=1300 ) { //Удаление пуль
                b.onDraw(canvas);
            } else {
                j.remove();
            }
        }
        Iterator<Enemy> i = enemy.iterator();
        while(i.hasNext()) {
            Enemy e = i.next();
            if(e.x >= 1000 || e.x <= 1000) {
                e.move();
                e.draw(canvas);
            } else {
                i.remove();
            }
        }

        invalidate();

    }
    public Bullet createSprite(int resouce,float bX,float bY,float shotX,float shotY) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resouce);
        return new Bullet(this, bmp,bX,bY,shotX,shotY);
    }
    float bX,bY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        shotX =  event.getX();
        shotY = event.getY();
        this.hero.setTarget(shotX,shotY);
        Iterator<Enemy> i = enemy.iterator();
        while(i.hasNext()) {
            Enemy e = i.next();
            e.setSpeed(shotX,shotY);


        }


        return /*super.onTouchEvent(event)*/ true;
    }


    public void shotTouch(){
        /*Toast toast = Toast.makeText(getContext(),
                "Пора покормить кота!", Toast.LENGTH_SHORT);
        toast.show();*/
        //ball.add(createSprite(R.drawable.bullet));
        bX=hero.x;
        bY=hero.y;
        ball.add(createSprite(R.drawable.bullet,bX,bY,shotX,shotY));
    }
    Random rnd = new Random();
    private Thread thread = new Thread(this);
    @Override
    public void run() {
        while (enemiesCount>0){

            try {
                Thread.sleep(rnd.nextInt(2000));//rnd.nextInt(200)+1400,rnd.nextInt(200)+800
                enemy.add(new Enemy(rnd.nextInt(200)+900,rnd.nextInt(200)+400));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            enemiesCount--;

        }

    }

    private void testCollision() {
        Iterator<Bullet> b = ball.iterator();
        while(b.hasNext()) {
            Bullet balls = b.next();
            Iterator<Enemy> i = enemy.iterator();
            while(i.hasNext()) {
                Enemy enemies = i.next();

                if (Math.abs(balls.x - enemies.x) <=30 && Math.abs(balls.y - enemies.y) <30) {
                    try {
                        i.remove();
                        b.remove();
                    }
                    catch (IllegalStateException e){

                    }

                   // text.setText(killCount);
                }
            }
        }
    }
}
