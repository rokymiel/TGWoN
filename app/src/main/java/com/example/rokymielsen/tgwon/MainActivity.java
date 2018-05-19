package com.example.rokymielsen.tgwon;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
MyDraw myDraw;
    Intent intent;
    Intent intent2;
    Intent intent3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDraw=(MyDraw)findViewById(R.id.myDraw1);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


    }
    public void game (View view){intent= new Intent(this,GameActivity.class);
        startActivity(intent);
    }
    public  void  settings(View view){
        intent2= new Intent(this,SettingsAppActivity.class);
        startActivity(intent2);
    }
    public void status(View view){
        intent3= new Intent(this,SignActivity.class);
        startActivity(intent3);
    }



}
