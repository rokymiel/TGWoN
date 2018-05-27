package com.example.rokymielsen.tgwon;

import android.content.Context;
import android.support.v4.app.Fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Roky Mielsen on 06.02.2018.
 */

public class CutScens1 extends Fragment{
    TextView tx1;
    TextView tx2;
    TextView tx3;
    TextView tx4;
    TextView tx5;
    TextView tx6;
    TextView tx7;
    TextView tx8;
    Button button;
    Script script= new Script();
    Delay delay= new Delay();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cut_scene1,null);
    }

    View v;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        button=(Button) view.findViewById(R.id.buttonNext);
        tx1=(TextView) view.findViewById(R.id.textScen1);tx2=(TextView) view.findViewById(R.id.textScen2);
        tx3=(TextView) view.findViewById(R.id.textScen3);tx4=(TextView) view.findViewById(R.id.textScen4);
        tx5=(TextView) view.findViewById(R.id.textScen5);tx6=(TextView) view.findViewById(R.id.textScen6);
        tx7=(TextView) view.findViewById(R.id.textScen7);tx8=(TextView) view.findViewById(R.id.textScen8);
        tx1.setText(script.script[0]);tx2.setText(script.script[1]);tx3.setText(script.script[2]);tx4.setText(script.script[3]);
        tx5.setText(script.script[4]);tx6.setText(script.script[5]);tx7.setText(script.script[6]);tx8.setText(script.script[7]);

        delay.execute();
        v=view;
        super.onViewCreated(view, savedInstanceState);
    }


    int line=0;

    class Delay extends AsyncTask<Void,Integer,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            while (line<=9){
                publishProgress(line++);
                try {
                    Thread.sleep(3000);
                    if (isCancelled()) return null;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //super.onProgressUpdate(values);
            Animation a = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
            switch (line){
                case 1:tx1.setVisibility(View.VISIBLE); tx1.startAnimation(a);break;
                case 2:tx2.setVisibility(View.VISIBLE); tx2.startAnimation(a);break;
                case 3:tx3.setVisibility(View.VISIBLE); tx3.startAnimation(a);break;
                case 4:tx4.setVisibility(View.VISIBLE); tx4.startAnimation(a);break;
                case 5:tx5.setVisibility(View.VISIBLE); tx5.startAnimation(a);break;
                case 6:tx6.setVisibility(View.VISIBLE); tx6.startAnimation(a);break;
                case 7:tx7.setVisibility(View.VISIBLE); tx7.startAnimation(a);break;
                case 8:tx8.setVisibility(View.VISIBLE); tx8.startAnimation(a);break;
                case 9:button.setVisibility(View.VISIBLE); button.startAnimation(a);break;


            }
        }


    }
}
