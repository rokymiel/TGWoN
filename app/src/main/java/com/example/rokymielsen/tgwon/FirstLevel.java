package com.example.rokymielsen.tgwon;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
        /*View view = inflater.inflate(R.layout.activity_game,
                container, false);*/

        //return super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.first_level,null);
    }
    TheGame game;
    TextView text;
    RelativeLayout.LayoutParams lp;Context context;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        layout=(LinearLayout) view.findViewById(R.id.layoutBut);
        //side= new GameActivity().side;
        context= view.getContext();
       text = (TextView) view.findViewById(R.id.killCount);
       game = (TheGame) view.findViewById(R.id.theGame);

         lp = (RelativeLayout.LayoutParams) layout.getLayoutParams();


       /* Button forward = (Button) view.findViewById(R.id.buttonV);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(v.getContext(),
                        "2", Toast.LENGTH_SHORT);
                toast.show();

            }
        });
        super.onViewCreated(view, savedInstanceState);*/
    }
    String side;
    GameActivity gameActivity;
    public void resume(String side){
        //side= new GameActivity().side;
        this.side= side;

       switch (side) {
            case "LEFT":
                //lp.removeRule(RelativeLayout.ALIGN_PARENT_LEFT);
                lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                break;
            case "RIGHT":
                lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //resume();

    }


}
