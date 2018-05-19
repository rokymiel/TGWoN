package com.example.rokymielsen.tgwon;

        import android.annotation.SuppressLint;
        import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.Canvas;
        import android.graphics.Rect;
        import android.util.AttributeSet;
        import android.util.Log;
        import android.view.MotionEvent;
        import android.view.View;
        import android.widget.ProgressBar;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.Iterator;
        import java.util.List;
        import java.util.Random;

        import static android.content.ContentValues.TAG;

/**
 * Created by Roky Mielsen on 21.04.2018.
 */

public class TheGame extends View implements Runnable{
    ControlledHero hero;
    int enemiesCount;
    int killCount=0;
    private List<Bullet> ball = new ArrayList<Bullet>();
    private List<Bullet> ballEnemy = new ArrayList<Bullet>();
    private List<Enemy> enemy = new ArrayList<Enemy>();
    float shotX,shotY;
    TextView text;


    public TheGame(Context context, AttributeSet attrs/*,Canvas canvas*/) {
        super(context,attrs);


        hero= new ControlledHero(500,400,BitmapFactory.decodeResource(getResources(), R.drawable.alex_legs_strip),BitmapFactory.decodeResource(getResources(), R.drawable.alex_strip));
        enemiesCount=1;
        //enemy.add(new Enemy(900,200));
        MyThread myThread = new MyThread();
        myThread.start();
        thread.start();
        //View view = fragment.getActiiviity().findViiewById(R.id.flID);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        testCollision();
        heatHero();

         hero.draw(canvas);
         hero.move();

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
        Iterator<Bullet> jE = ballEnemy.iterator();
        while(jE.hasNext()) {
            Bullet bE = jE.next();
            // Toast.makeText(getContext(), j+"", Toast.LENGTH_LONG).show();
            // Toast.makeText(getContext(), b.x+"", Toast.LENGTH_LONG).show();

            if(bE.x <= 2500 && bE.x>=-100 && bE.y >=-100 && bE.y <=1300 ) { //Удаление пуль
                bE.onDraw(canvas);
            } else {
                jE.remove();
            }
        }
        int enemyFrame=0;
        Iterator<Enemy> i = enemy.iterator();
        while(i.hasNext()) {
            Enemy enemies = i.next();
                if (enemyFrame%20==0) {
                    enemies.setAngle(hero.x,hero.y);
                }

                enemies.move();

                enemies.draw(canvas);

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
        this.hero.setEnd(shotX,shotY);
        //hero.motion=true;
        Iterator<Enemy> i = enemy.iterator();
        while(i.hasNext()) {
            Enemy enemies= i.next();
            enemies.setSpeed(rnd.nextInt(200)+shotX-100,rnd.nextInt(200)+shotY-200);



        }


        return /*super.onTouchEvent(event)*/ true;
    }


    public void shotTouch(){
        /*Toast toast = Toast.makeText(getContext(),
                "Пора покормить кота!", Toast.LENGTH_SHORT);
        toast.show();*/
        //ball.add(createSprite(R.drawable.bullet));
        if (hero.endShoot) {
            bX = hero.x;
            bY = hero.y;
            hero.heroShoot();
            ball.add(createSprite(R.drawable.bullet, bX, bY, shotX, shotY));
        }
    }
    Random rnd = new Random();
    private Thread thread = new Thread(this);
    @Override
    public void run() {
        try {
            Thread.sleep(rnd.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true){
            //enemiesShootFalse();
            try {
                Thread.sleep(rnd.nextInt(3000)+1000);
                enemiesShoot();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
    void enemiesShootFalse(){

        Iterator<Enemy> i = enemy.iterator();
        while(i.hasNext()) {
            Enemy enemies = i.next();
            enemies.shoot=false;
            //Log.d(TAG,"ENEMIES");
        }
    }
    float enemyX,enemyY,heroX,heroY;
    void enemiesShoot(){
        Iterator<Enemy> i = enemy.iterator();
        while(i.hasNext()) {
            Enemy enemies = i.next();
            enemyX=enemies.x;
            enemyY=enemies.y;
            heroX=hero.x;
            heroY=hero.y;
            ballEnemy.add(createSprite(R.drawable.bullet,enemyX,enemyY,heroX,heroY));


            enemies.shoot=true;
            //Log.d(TAG,"ENEMIES");
        }
    }


    class MyThread extends Thread{
        public void run(){
            while (enemiesCount>0){

                try {
                    Thread.sleep(rnd.nextInt(2000));//rnd.nextInt(200)+1400,rnd.nextInt(200)+800
                    enemy.add(new Enemy(rnd.nextInt(200)+900,rnd.nextInt(200)+400,BitmapFactory.decodeResource(getResources(), R.drawable.enemy_legs_strip3),BitmapFactory.decodeResource(getResources(), R.drawable.enemy_1_strip3)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                enemiesCount--;

            }
        }
    }

    int damdge=50;
    int enemiesDamage=20;


    TextView tx;
    private void testCollision() {
        tx = (TextView)((GameActivity)this.getContext()).findViewById(R.id.killCount);
        Iterator<Bullet> b = ball.iterator();
        while(b.hasNext()) {
            Bullet balls = b.next();
            Iterator<Enemy> i = enemy.iterator();
            while(i.hasNext()) {
                Enemy enemies = i.next(); //((enemies.x-balls.x)*(enemies.x-balls.x)+(enemies.y-balls.y)*(enemies.y-balls.y)<=2490 )Math.abs(balls.x - enemies.x) <=30 && Math.abs(balls.y - enemies.y) <=30
                if (Math.abs(balls.x - enemies.x) <= (balls.width + enemies.width) / 4f
                        && Math.abs(balls.y - enemies.y) <= (balls.height + enemies.height) / 4f) {
                    enemies.health -= damdge;
                    if (enemies.health <=0) {
                        try {
                            i.remove();
                            b.remove();
                            killCount++;

                            tx.setText(killCount+"");
                        } catch (IllegalStateException e) {

                        }
                    }
                    else {

                        Log.d(TAG, "ENEMIES" + enemies.health);
                        try {
                            b.remove();
                        } catch (IllegalStateException e) {

                        }
                    }
                   //Log.d(TAG,"ENEMIES");


                   // text.setText(killCount);
                }
            }
        }
    }

    ProgressBar progressBar;
    int same=0;

    @SuppressLint("ResourceAsColor")
    private void heatHero(){
        progressBar = (ProgressBar) ((GameActivity)this.getContext()).findViewById(R.id.heroHealth);

        Iterator<Bullet> b = ballEnemy.iterator();
        while(b.hasNext()) {
            Bullet balls = b.next();
            if(Math.abs(balls.x - hero.x) <=40 && Math.abs(balls.y - hero.y) <=40){
                try {
                    b.remove();
                    progressBar.setProgress(progressBar.getProgress()-enemiesDamage);
                } catch (IllegalStateException e) {

                }
                same++;
                Log.d(TAG,progressBar.getProgress()+" "+same);

                if (progressBar.getProgress()<=0) {
                    hero.paint.setColor(R.color.black);
                }
            }
        }
    }
}





/*flag++;
                    if (flag%3==1){ enemies.health-=damdge;Log.d(TAG,"ENEMIES"+ enemies.health);
                        try {

                            b.remove();
                        } catch (IllegalStateException e) {

                        }
                    }
                    //Log.d(TAG,"ENEMIES"+ flag);
                    if (enemies.health <=0) {
                        try {
                            i.remove();
                            b.remove();
                            killCount++;
                        } catch (IllegalStateException e) {

                        }
                    }*/