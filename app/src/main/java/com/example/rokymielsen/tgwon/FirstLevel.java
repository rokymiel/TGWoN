package com.example.rokymielsen.tgwon;


import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import io.github.controlwear.virtual.joystick.android.JoystickView;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Roky Mielsen on 06.02.2018.
 */

public class FirstLevel extends Fragment {
    LinearLayout layout;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.first_level,null);
    }
    TheGame game;
    TextView text;
    RelativeLayout.LayoutParams lp;
    RelativeLayout.LayoutParams joyLp;Context context;
    LinearLayout joystickLayout;
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        layout=(LinearLayout) view.findViewById(R.id.layoutBut);
        joystickLayout=(LinearLayout) view.findViewById(R.id.joystickLayout);

        context= view.getContext();
       text = (TextView) view.findViewById(R.id.killCount);
       game = (TheGame) view.findViewById(R.id.theGame);
        joyLp=(RelativeLayout.LayoutParams) joystickLayout.getLayoutParams();
         lp = (RelativeLayout.LayoutParams) layout.getLayoutParams();
        resume(((GameActivity)this.getContext()).side);
        ((GameActivity)this.getContext()).music();
        JoystickView joystick = (JoystickView) ((GameActivity)this.getContext()).findViewById(R.id.joystickView);
        joystick.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
                //.makeText(view.getContext(),angle,Toast.LENGTH_SHORT).show();
                //game.action(angle);
                if (angle!=0) {
                    game.setHeroAngle(angle);
                    game.setMapMotion(angle,strength);
                }

            }

        });


        //super.onViewCreated(view, savedInstanceState);
    }
    String side;


    public void resume(String side){

        this.side= side;

       switch (side) {
            case "LEFT":
                joyLp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                break;
            case "RIGHT":
                joyLp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                break;
        }
    }



}
