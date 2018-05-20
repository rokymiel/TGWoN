package com.example.rokymielsen.tgwon;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class GameActivity extends FragmentActivity {
    FirstLevel firstLevel= new FirstLevel();
    TheGame game;
    LinearLayout layout;
    FrameLayout frameLayout;
    RelativeLayout.LayoutParams layoutParams;
    FrameLayout mainLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //getSupportFragmentManager().beginTransaction().add(R.id.game,firstLevel);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.add(R.id.game,firstLevel).commit();



        frameLayout=(FrameLayout) findViewById(R.id.frameLay);
        //layoutParams= (FrameLayout.LayoutParams)frameLayout.getLayoutParams();*/
        layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        //RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) layout.getLayoutParams();
        //lp.removeRule(RelativeLayout.ALIGN_PARENT_LEFT);
        //lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

         /*   Button forward = (Button) findViewById(R.id.buttonV);
            Button back = (Button) findViewById(R.id.buttonB);
            Button right = (Button) findViewById(R.id.buttonR);
            Button left = (Button) findViewById(R.id.buttonL);
            LisnerB lB = new LisnerB();
            LisnerV lV = new LisnerV();
            LisnerR lR = new LisnerR();
            LisnerL lL = new LisnerL();*/



          /*  forward.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        Toast.makeText(getApplicationContext(),
                                "Молодой человек, не прикасайтесь ко мне!",
                                Toast.LENGTH_SHORT).show();
                    }
                    return false;
                }
            });*/
            /*back.setOnTouchListener(lB);
            right.setOnTouchListener(lR);
            left.setOnTouchListener(lL);*/


    }
    public  String side;
    @Override
    protected void onResume()  {
        super.onResume();
        SharedPreferences preferences= getSharedPreferences("buttonLayout",MODE_PRIVATE);
        side =preferences.getString("buttonSide","LEFT");
        firstLevel.resume(side);
        startService(new Intent(this, MyService.class));





        //layout.setLayoutParams(layoutParams);


        /*layoutParams.setMargins(100,100,0,0);*/
        /*switch (side){
            case "LEFT":
                layout.setGravity(Gravity.LEFT);
                break;
            case "RIGHT":
                layout.setGravity(Gravity.RIGHT+Gravity.BOTTOM);
                break;
        }*/
    }

    class LisnerV implements View.OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {


            return true;

        }
    }

    public void shot(View v){
        game= (TheGame) findViewById(R.id.theGame);

        game.shotTouch();
    }
    public void onBackPressed() {
        stopService(new Intent(this, MyService.class));
        this.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopService(new Intent(this, MyService.class));

    }

    void maxScore(int killCount){
        Toast.makeText(this, killCount+"", Toast.LENGTH_SHORT).show();
        SharedPreferences preferences= getSharedPreferences("maxScore",MODE_PRIVATE);
        int maxScore=Integer.parseInt(preferences.getString("maxScore","0"));
        Toast.makeText(this, maxScore+"", Toast.LENGTH_SHORT).show();
        if (maxScore<killCount) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("maxScore", killCount + "");
            editor.apply();
        }

    }
}
