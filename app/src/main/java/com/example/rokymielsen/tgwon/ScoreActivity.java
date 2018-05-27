package com.example.rokymielsen.tgwon;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

public class ScoreActivity extends AppCompatActivity {
    private static final String TAG ="-----------" ;
    private FirebaseAuth mAuth ;
    private DatabaseReference myRef;
    private List<String> ScoreList;
    ListView listView;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    public FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String name;
    private  long score;
    TextView nameView;
    TextView scoreView;
    Button change;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        nameView =(TextView)findViewById(R.id.nameView);
        scoreView=(TextView)findViewById(R.id.scoreView);
        change=(Button)findViewById(R.id.changeData);
        myRef= FirebaseDatabase.getInstance().getReference("Users");
        mAuth = FirebaseAuth.getInstance();
        Log.d(TAG,user.getUid()+"");
        ValueEventListener postListener = new ValueEventListener() {
            public static final String TAG ="_____________" ;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                name=(String) dataSnapshot.child("Users").child(user.getUid()).child("name").getValue();
                score=(Long) dataSnapshot.child("Users").child(user.getUid()).child("score").getValue();

                nameView.setText(name);
                scoreView.setText(score+"");
                change.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mDatabase.addValueEventListener(postListener);


    }



    public void changeData(View view) {
        SharedPreferences preferences= getSharedPreferences("maxScore",MODE_PRIVATE);
        int newScore = Integer.parseInt(preferences.getString("maxScore","0"));
        DatabaseReference mUserAccount = myRef.child(user.getUid());

        if (newScore>score){
            mUserAccount.child("score").setValue(newScore);
        }

    }
}
