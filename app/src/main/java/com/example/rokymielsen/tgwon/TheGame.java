package com.example.rokymielsen.tgwon;

        import android.annotation.SuppressLint;
        import android.content.Context;
        import android.content.SharedPreferences;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.graphics.Rect;
        import android.util.AttributeSet;
        import android.util.DisplayMetrics;
        import android.util.Log;
        import android.view.MotionEvent;
        import android.view.View;
        import android.widget.FrameLayout;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.ProgressBar;
        import android.widget.RelativeLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.ConcurrentModificationException;
        import java.util.Iterator;
        import java.util.List;
        import java.util.Random;

        import io.github.controlwear.virtual.joystick.android.JoystickView;

        import static android.content.ContentValues.TAG;
        import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Roky Mielsen on 21.04.2018.
 */

public class TheGame extends View implements Runnable{
    DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
    static int scaleWidth;
    static int scaleHeight;
    static int xStatic;
    static int yStatic;
    ControlledHero hero;
    int enemiesCount;
    int killCount=0;
    private List<Bullet> ball = new ArrayList<Bullet>();
    private List<Bullet> ballEnemy = new ArrayList<Bullet>();
    private List<Enemy> enemy = new ArrayList<Enemy>();
    float shotX,shotY;
    TextView text;
    boolean gaming=true;
    int damdge=50;
    int enemiesDamage=20;
    Bitmap bmp ;
    float bX,bY;
    Random rnd = new Random();
    private Thread thread = new Thread(this);
    float enemyX,enemyY,heroX,heroY;
    int enemyFix=10;
    int enemyDid=1;
    TextView tx;
    ProgressBar progressBar;
    int same=0;
    BackRect backRect;
    /*Rect backRect;
    Paint paint= new Paint();
    int rectX,rectY,rectEndX,rectEndY;*/

    public TheGame(final Context context, AttributeSet attrs/*,Canvas canvas*/) {
        super(context,attrs);
        scaleWidth=displaymetrics.widthPixels;
        scaleHeight=displaymetrics.heightPixels;
        xStatic =scaleWidth/100;
        yStatic=scaleHeight/100;
        backRect= new BackRect(scaleWidth*2,scaleHeight*2,20);
       /* rectX=20;
        rectY=20;
        rectEndX=scaleWidth-rectX;
        rectEndY=scaleHeight-rectY;*/
        hero= new ControlledHero(scaleWidth/2,scaleHeight/2,BitmapFactory.decodeResource(getResources(), R.drawable.alex_legs_strip),BitmapFactory.decodeResource(getResources(), R.drawable.alex_strip),xStatic,yStatic);
        hero.joyAngle=90;
        shotX=50*xStatic;
        shotY=50*yStatic;

        /*backRect= new Rect(rectX,rectY,rectEndX,rectEndY);
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);*/
        enemiesCount=100;
        //enemy.add(new Enemy(900,200));

        MyThread myThread = new MyThread();
        myThread.start();
        thread.start();
       bmp = BitmapFactory.decodeResource(getResources(),R.drawable.bullet);





        //View view = fragment.getActiiviity().findViiewById(R.id.flID);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        testCollision();
        heatHero();
        //canvas.drawRect(backRect,paint);
         backRect.draw(canvas);
         hero.draw(canvas);
         hero.move();
        // backRect.draw(canvas);
        // Log.d(TAG,"DDDRRRAAAAWWWW");

       // text.setText(killCount);
        Iterator<Bullet> j = ball.iterator();
        while(j.hasNext()) {
            Bullet b = j.next();
           // Toast.makeText(getContext(), j+"", Toast.LENGTH_LONG).show();
           // Toast.makeText(getContext(), b.x+"", Toast.LENGTH_LONG).show();
//Удаление пуль(b.x <= scaleWidth*2 && b.x>=-8*xStatic && b.y >=-13*yStatic && b.y <=2*scaleHeight
            if(b.x < backRect.eX-55* (scaleWidth/1184) && b.x>backRect.x+55*(scaleWidth/1184) && b.y >backRect.y+55*(scaleHeight/768) && b.y <backRect.eY-55*(scaleHeight/768) ) {
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

            if(bE.x < backRect.eX-55*(scaleWidth/1184) && bE.x>backRect.x+55*(scaleWidth/1184) && bE.y >backRect.y+55*(scaleHeight/768) && bE.y <backRect.eY-55*(scaleHeight/768)) { //Удаление пуль
                bE.onDraw(canvas);
            } else {
                jE.remove();
            }
        }
        int enemyFrame=0;
        Iterator<Enemy> i = enemy.iterator();
        while(i.hasNext()) {
            try {
                Enemy enemies = i.next();
                if (enemyFrame%10==0) {
                    enemies.setAngle(hero.x,hero.y);
                }

                enemies.move();

                enemies.draw(canvas);
                }
                catch (ConcurrentModificationException e){

                }

        }

        invalidate();

    }

    public Bullet createSprite(float bX,float bY,float shotX,float shotY) {
        //Bitmap bmp = BitmapFactory.decodeResource(getResources(), resouce);
        return new Bullet(this, bmp,bX,bY,shotX,shotY);
    }

    public void setHeroAngle(int angle){
        if(gaming) {hero.joyAngle=90-angle;}
    }
    int angle=0,strength=0;

    public void setMapMotion(int angle, int strength){
        if(gaming) {
            this.angle = angle;
            this.strength = strength;
            setRectPosition();
            setBulletMotion();
            if (!stop) {
                setenEmiesEnd();
                setSpeedEnemies();
            }
        }
    }
    public void setSpeedEnemies(){
        Iterator<Enemy> i = enemy.iterator();
        while (i.hasNext()) {
            Enemy enemies = i.next();
            float rndx=rnd.nextInt(200) + hero.x - 100;
            float rndy=rnd.nextInt(200) + hero.y - 200;
            if (backRect.x<rndx && backRect.eX>rndx && backRect.y<rndy && backRect.eY>rndy) {
                enemies.setSpeed(rndx, rndy);
            }else{
                if (rndx<backRect.x){
                    rndx=2*backRect.x-rndx;
                }
                if(rndx>backRect.eX){
                    rndx=2*backRect.eX-rndx;
                }
                if (rndy<backRect.y){
                    rndy=2*backRect.y-rndy;
                }
                if(rndy>backRect.eY){
                    rndy=2*backRect.eY-rndy;
                }
                enemies.setSpeed(rndx, rndy);
            }


        }
    }

    int motionSide=9*(scaleWidth/1184);
    int delY;
    int cX=4,cY=1;
    int chX,chY;
    int k,b;
    float vx,vy;
    int motionRectSide,motionRectX,motionRrectY;
    boolean stop=false;
    public void setRectPosition(){
        motionRectSide=20*(scaleWidth/1184);
        motionRectX= (int) (motionRectSide*Math.cos(Math.toRadians(-angle)));
        motionRrectY= (int) (motionRectSide*Math.sin(Math.toRadians(-angle)));
        if (backRect.x-motionRectX<=hero.x && backRect.eX-motionRectX>=hero.x && backRect.y-motionRrectY<=hero.y && backRect.eY - motionRrectY>= hero.y){
            backRect.x-=motionRectX;
            backRect.y-=motionRrectY;
            backRect.eX-=motionRectX;
            backRect.eY-=motionRrectY;
            Log.d(TAG,backRect.x+"");
            stop=false;
        }
        else {
           /* backRect.x+=(motionRectX/5);
            backRect.y+=motionRrectY/5;
            backRect.eX+=motionRectX/5;
            backRect.eY+=motionRrectY/5;*/
           stop=true;
           return;
        }
        /*motionRectSide=20*(scaleWidth/1184);
        motionRectX= (int) (motionSide*Math.cos(Math.toRadians(-angle)));
        motionRrectY= (int) (motionSide*Math.sin(Math.toRadians(-angle)));
        rectX-=motionRectX;
        rectY-=motionRrectY;
        rectEndX-=motionRectX;
        rectEndY-=motionRrectY;
        backRect=new Rect(rectX,rectY,rectEndX,rectEndY);
*/
    }
    int cbX,cbY;
    public void setBulletMotion(){
        Iterator<Bullet> jE = ballEnemy.iterator();
        while(jE.hasNext()) {
            Bullet bE = jE.next();
            motionSide=20*(scaleWidth/1184);
            cbX= (int) (motionSide*Math.cos(Math.toRadians(-angle)));
            cbY= (int) (motionSide*Math.sin(Math.toRadians(-angle)));
            bE.x-=cbX;
            bE.y-=cbY;
            // Toast.makeText(getContext(), j+"", Toast.LENGTH_LONG).show();
            // Toast.makeText(getContext(), b.x+"", Toast.LENGTH_LONG).show();


        }
    }
    public void setenEmiesEnd(){
        Iterator<Enemy> i = enemy.iterator();
        while (i.hasNext()) {

            Enemy enemies = i.next();
            motionSide=20*(scaleWidth/1184);
            /*if (angle>=0&&angle<=90){
                chX=-cX;
                chY=cY;
            }
            else if(angle>90&& angle<=180){
                chX=cX;
                chY=cY;
            }else if (angle>180 && angle<=270){
                chX=cX;
                chY=-cY;
            } else if (angle>270 && angle<=360){
                chX=-cX;
                chY=-cY;
            }
            enemies.x+=chX;
            motionSide= (int) (cX/Math.sin(Math.toRadians(angle)));
            motionSide*=motionSide;
            delY=motionSide-chX*chX;
            delY=(int)(Math.sqrt(delY));
            enemies.y+=chY*delY;*/
/*
           k= (int) Math.tan(angle);
           b= (int) (enemies.y-k*enemies.x);
           int eY=k*cX+b;
           enemies.y=eY;*/
            //Log.d(TAG,+"");
            /*if (angle>=0&&angle<=90){
                motionSide= (int) (3/Math.sin(Math.toRadians(angle)));
                vx=3;
                vy= (float) Math.sqrt(motionSide*motionSide-vx);
                vx=(vx/motionSide)*3;
                vy=(vy/motionSide)*3;
                enemies.x+=vx;
                enemies.y+=vy;

            }
            else if(angle>90&& angle<=180){

            }else if (angle>180 && angle<=270){

            } else if (angle>270 && angle<=360){

            }*/

            chX= (int) (motionSide*Math.cos(Math.toRadians(-angle)));
            chY= (int) (motionSide*Math.sin(Math.toRadians(-angle)));
            //Log.d(TAG,angle+"");

            /*vx=(chX/motionSide)*3;
            vy=(chY/motionSide)*3;*/
            enemies.x-=chX;
            enemies.y-=chY;


/*            this.endX=endX;
            this.endY=endY;
            this.vx=this.endX-this.x;
            this.vy=this.endY-this.y;
            float d =(float)Math.sqrt(vx*vx+vy*vy);
            this.vx=(this.vx/d)*3;
            this.vy=(this.vy/d)*3;*/


        }
    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (true){

        } else {
            if (gaming) {
                shotX = event.getX();
                shotY = event.getY();
                this.hero.setTarget(shotX, shotY);
                this.hero.setEnd(shotX, shotY);
                //hero.motion=true;
                Iterator<Enemy> i = enemy.iterator();
                while (i.hasNext()) {
                    Enemy enemies = i.next();
                    enemies.setSpeed(rnd.nextInt(200) + shotX - 100, rnd.nextInt(200) + shotY - 200);


                }
            }
        }


        return /*super.onTouchEvent(event)*/ true;
    }
    int bulletMotionSide=30;
    int bEndX,bEndY;
    public void shotTouch(){
        if(gaming) {
            if (hero.endShoot) {
                bX = hero.x;
                bY = hero.y;
                hero.heroShoot();

                bEndX=7*(scaleWidth/1184);
               /* bEndX= (int) (bX+ (int) (bulletMotionSide*Math.cos(Math.toRadians(-angle))));
                bEndY= (int) (bY+(int) (bulletMotionSide*Math.sin(Math.toRadians(-angle))));*/
               //(80000/1184)*xStatic
               /* bEndX=((bEndX*100)/1184)*xStatic;
                bEndY=((bEndY*100)/768)*yStatic;*/
                Log.d(TAG,getWidth()+"");
                if (angle>90 && angle<270){
                    bEndX=-bEndX;
                }
                bulletMotionSide= (int) (bEndX/Math.cos(Math.toRadians(-angle)));
                bEndY= (int) (bY+(int) (bulletMotionSide*Math.sin(Math.toRadians(-angle))));
                //bEndX+=bX;

                ball.add(createSprite(bX, bY, bEndX+bX, bEndY));
                Log.d(TAG,angle+"");
                Log.d(TAG,bEndX+bX+"");
                Log.d(TAG,bEndY+"");

            }
        }
    }


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
    public void action(int str){
        Log.d(TAG,str+"CCCCCCCCCC");

    }
    void enemiesShoot(){
        if(gaming) {
            Iterator<Enemy> i = enemy.iterator();
            while (i.hasNext()) {
                try {
                    Enemy enemies = i.next();
                    enemyX = enemies.x;
                    enemyY = enemies.y;
                    heroX = hero.x;
                    heroY = hero.y;
                    ballEnemy.add(createSprite(enemyX, enemyY,rnd.nextInt(200*(scaleWidth/1184)) + heroX - 100*(scaleWidth/1184), rnd.nextInt(200*(scaleHeight/768)) + heroY - 100*(scaleHeight/768)));


                    enemies.shoot = true;
                    //Log.d(TAG,"ENEMIES");
                } catch (ConcurrentModificationException e) {

                }

            }
        }
    }


    class MyThread extends Thread {
        public void run() {
            while (enemiesCount > 0) {

                try {
                    if(enemyDid%enemyFix==0) {
                        Thread.sleep(rnd.nextInt(5000) + 3500);
                    }
                    else {
                        Thread.sleep(rnd.nextInt(3000) + 1000); //rnd.nextInt(200)+1400,rnd.nextInt(200)+800
                    }
                    enemyDid++;
                    enemy.add(new Enemy(rnd.nextInt((int) (backRect.eX-backRect.x)) + backRect.x, rnd.nextInt((int) (backRect.eY-backRect.y)) + backRect.y, BitmapFactory.decodeResource(getResources(), R.drawable.enemy_legs_strip3), BitmapFactory.decodeResource(getResources(), R.drawable.enemy_1_strip3), xStatic, yStatic));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                enemiesCount--;

            }

        }
    }

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
                            ((GameActivity)this.getContext()).maxScore(killCount);
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

    @SuppressLint("ResourceAsColor")
    private void heatHero(){
        progressBar = (ProgressBar) ((GameActivity)this.getContext()).findViewById(R.id.heroHealth);

        Iterator<Bullet> b = ballEnemy.iterator();
        while(b.hasNext()) {
            try {
                Bullet balls = b.next();
                if(Math.abs(balls.x - hero.x) <=3*xStatic && Math.abs(balls.y - hero.y) <=5*yStatic){
                    try {
                        b.remove();
                        progressBar.setProgress(progressBar.getProgress()-enemiesDamage);
                    } catch (IllegalStateException e) {

                    }
                    same++;
                    Log.d(TAG,progressBar.getProgress()+" "+same);

                    if (progressBar.getProgress()<=0) {
                        hero.paint.setColor(R.color.black);
                        enemiesCount=0;
                        gaming=false;
                        FrameLayout layoutDead;
                        ImageView imageDead;
                        RelativeLayout relativeLayout;
                        relativeLayout=(RelativeLayout) ((GameActivity)this.getContext()).findViewById(R.id.layoutButton);
                        imageDead=(ImageView)((GameActivity)this.getContext()).findViewById(R.id.imageDead);
                        layoutDead=(FrameLayout) ((GameActivity)this.getContext()).findViewById(R.id.layoutDead);
                        layoutDead.setVisibility(VISIBLE);
                        imageDead.setVisibility(VISIBLE);
                        relativeLayout.setVisibility(VISIBLE);
                        ((GameActivity)this.getContext()).maxScore(killCount);
                    }
                }
            }
            catch (ConcurrentModificationException e){

            }

        }
    }
}
