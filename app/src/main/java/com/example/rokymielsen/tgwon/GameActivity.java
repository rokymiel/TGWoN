package com.example.rokymielsen.tgwon;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentActivity;

import android.os.Bundle;

import android.view.View;

import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class GameActivity extends FragmentActivity {
    FirstLevel firstLevel= new FirstLevel();
    TheGame game;
    LinearLayout layout;
    FrameLayout frameLayout;
    RelativeLayout.LayoutParams layoutParams;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.add(R.id.game,firstLevel).commit();



        frameLayout=(FrameLayout) findViewById(R.id.frameLay);

        layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);


    }
    public  String side;
    @Override
    protected void onResume()  {
        super.onResume();
        SharedPreferences preferences= getSharedPreferences("buttonLayout",MODE_PRIVATE);
        side =preferences.getString("buttonSide","LEFT");
        firstLevel.resume(side);
        startService(new Intent(this, MyService.class));

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

        SharedPreferences preferences= getSharedPreferences("maxScore",MODE_PRIVATE);
        int maxScore=Integer.parseInt(preferences.getString("maxScore","0"));

        if (maxScore<killCount) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("maxScore", killCount + "");
            editor.apply();
        }

    }
}
