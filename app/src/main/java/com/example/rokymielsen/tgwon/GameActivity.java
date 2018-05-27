package com.example.rokymielsen.tgwon;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class GameActivity extends FragmentActivity {
    private static final String TAG ="!!!!!!!!!!" ;
    FirstLevel firstLevel= new FirstLevel();
    CutScens1 cutScens1= new CutScens1();
    TheGame game;
    LinearLayout layout;
    FrameLayout frameLayout;
    RelativeLayout.LayoutParams layoutParams;
    boolean noCut=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

       getSupportFragmentManager().beginTransaction().add(R.id.scen,cutScens1).commit();



        frameLayout=(FrameLayout) findViewById(R.id.frameLay);

        layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);


    }
    public  String side;
    @Override
    protected void onResume()  {

        super.onResume();
            SharedPreferences preferences = getSharedPreferences("buttonLayout", MODE_PRIVATE);
            side = preferences.getString("buttonSide", "LEFT");
    }



    public void shot(View v){
        game= (TheGame) findViewById(R.id.theGame);
        game.shotTouch();
    }
    public void music(){
        startService(new Intent(this, MyService.class));
    }


    public void next(View v){
        noCut=true;
        try {
            cutScens1.delay.cancel(true);
            cutScens1.delay=null;
        }
        catch (NullPointerException e){

        }


if(firstLevel.isAdded()){
    getSupportFragmentManager().beginTransaction().show(firstLevel);
}else {
    getSupportFragmentManager().beginTransaction().remove(cutScens1)
            .commit();
    getSupportFragmentManager().beginTransaction().add(R.id.game, firstLevel).commit();
}
        }

    public void onBackPressed() {
        if(noCut) {
            stopService(new Intent(this, MyService.class));
            this.finish();
        }else{
            cutScens1.delay.cancel(true);
            cutScens1.delay=null;
            this.finish();
        }
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
