package com.example.rokymielsen.tgwon;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

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
    TextTread textTread= new TextTread();
    Animation anim;
    int maxText=8;
    private List<TextView> textArray = new ArrayList<>();
    int text=1;
    boolean buttonAnim=false;
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
        tx1=(TextView) view.findViewById(R.id.textScen1);textArray.add(tx1);tx2=(TextView) view.findViewById(R.id.textScen2);textArray.add(tx2);
        tx3=(TextView) view.findViewById(R.id.textScen3);textArray.add(tx3);tx4=(TextView) view.findViewById(R.id.textScen4);textArray.add(tx4);
        tx5=(TextView) view.findViewById(R.id.textScen5);textArray.add(tx5);tx6=(TextView) view.findViewById(R.id.textScen6);textArray.add(tx6);
        tx7=(TextView) view.findViewById(R.id.textScen7);textArray.add(tx7);tx8=(TextView) view.findViewById(R.id.textScen8);textArray.add(tx8);
        tx1.setText(script.script[0]);tx2.setText(script.script[1]);tx3.setText(script.script[2]);tx4.setText(script.script[3]);
        tx5.setText(script.script[4]);tx6.setText(script.script[5]);tx7.setText(script.script[6]);tx8.setText(script.script[7]);

        //animation();
        //textTread.start();
        //delay.execute();
        //testAnim();
        /*int hope=0;
        while (text<=maxText) {
            Animation anim = AnimationUtils.loadAnimation(view.getContext(), R.anim.tv_anim);
            textArray.get(text-1).setVisibility(View.VISIBLE);
            textArray.get(text-1).startAnimation(anim);
            hope++;
            if (hope%5000==0) {
                text++;
            }
        }
        Animation anim1 = AnimationUtils.loadAnimation(view.getContext(), R.anim.tv_anim);
        button.setVisibility(View.VISIBLE);
        button.startAnimation(anim1);*/
        //tx1.setVisibility(View.INVISIBLE);
       // textTread.start();

        while (text<=maxText) {
            AnimatorSet animatorSet1 = (AnimatorSet) AnimatorInflater.loadAnimator(view.getContext(), R.animator.fade);
            animatorSet1.setTarget(textArray.get(text-1));
            AnimatorSet animFade=new AnimatorSet();
            animFade.play(animatorSet1);
            animFade.start();
            text++;
        }
        AnimatorSet animatorSetBut = (AnimatorSet) AnimatorInflater.loadAnimator(view.getContext(), R.animator.fade);
        animatorSetBut.setTarget(button);
        AnimatorSet animFade=new AnimatorSet();
        animFade.play(animatorSetBut);
        animFade.start();
        text=1;

        List<Animator> animatorSetList = new ArrayList<>();
        while (text<=maxText) {

            //textArray.get(text - 1).setVisibility(View.VISIBLE);
            AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(view.getContext(), R.animator.blink);
            animatorSet.setTarget(textArray.get(text - 1));
            text++;

            animatorSetList.add(animatorSet);
        }
        AnimatorSet animatorSetButBlink = (AnimatorSet) AnimatorInflater.loadAnimator(view.getContext(), R.animator.blink);
        animatorSetButBlink.setTarget(button);
        animatorSetList.add(animatorSetButBlink);
                AnimatorSet anim = new AnimatorSet();
        anim.playSequentially(animatorSetList);
        anim.start();

                // tx1.setVisibility(View.VISIBLE);






        v=view;
        super.onViewCreated(view, savedInstanceState);
    }
boolean doAnim=true;
    class TextTread extends Thread{
        @Override
        public void run() {
            while (text<=9) {
                try {

                    Thread.sleep(3000);
                    text++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    int line=0;
    public void testAnim(){
        int fdf=0;
        while (fdf<9000) {
            Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.tv_anim);
            tx1.startAnimation(anim);
            fdf++;
        }
    }

public void animation(){
while (text<=maxText) {
    Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.tv_anim);
    textArray.get(text-1).startAnimation(anim);
}
while (buttonAnim){
    Animation anim1 = AnimationUtils.loadAnimation(getContext(), R.anim.tv_anim);
    button.startAnimation(anim1);
}

}
    class Delay extends AsyncTask<Void,Integer,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            while (line<=9){
                publishProgress(line++);
                //line++;
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
           Animation a = AnimationUtils.loadAnimation(getActivity(), R.anim.alpha);
                switch (line) {
                    case 1:
                        tx1.setVisibility(View.VISIBLE);
                        tx1.startAnimation(a);
                        break;
                    case 2:
                        tx2.setVisibility(View.VISIBLE);
                        tx2.startAnimation(a);
                        break;
                    case 3:
                        tx3.setVisibility(View.VISIBLE);
                        tx3.startAnimation(a);
                        break;
                    case 4:
                        tx4.setVisibility(View.VISIBLE);
                        tx4.startAnimation(a);
                        break;
                    case 5:
                        tx5.setVisibility(View.VISIBLE);
                        tx5.startAnimation(a);
                        break;
                    case 6:
                        tx6.setVisibility(View.VISIBLE);
                        tx6.startAnimation(a);
                        break;
                    case 7:
                        tx7.setVisibility(View.VISIBLE);
                        tx7.startAnimation(a);
                        break;
                    case 8:
                        tx8.setVisibility(View.VISIBLE);
                        tx8.startAnimation(a);
                        break;
                    case 9:
                        button.setVisibility(View.VISIBLE);
                        button.startAnimation(a);
                        break;


                }

        }


    }


}
