package com.example.rokymielsen.tgwon;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

public class SettingsAppActivity extends AppCompatActivity {
    LinearLayout layoutSet;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        radioGroup=(RadioGroup) findViewById(R.id.groupPosition);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String side="";
                switch (checkedId){
                    case R.id.left :
                        side="LEFT";
                        break;
                    case R.id.right:
                        side="RIGHT";
                        break;
                }
                SharedPreferences preferences= getSharedPreferences("buttonLayout",MODE_PRIVATE);
                SharedPreferences.Editor editor= preferences.edit();
                editor.putString("buttonSide",side);
                editor.apply();
            }
        });



    }
}
