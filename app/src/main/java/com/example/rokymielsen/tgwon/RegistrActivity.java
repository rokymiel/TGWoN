package com.example.rokymielsen.tgwon;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrActivity extends AppCompatActivity {
    private DatabaseReference myRef;
    public FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    public Button save;
    TextView textScore;
    EditText textName;
    DatabaseReference mUserAccount;
    int newScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registr);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        save=(Button)findViewById(R.id.save);
        textScore=(TextView)findViewById(R.id.scoreViewReg);
        textName=(EditText)findViewById(R.id.nameViewReg);
        myRef= FirebaseDatabase.getInstance().getReference("Users");
        SharedPreferences preferences= getSharedPreferences("maxScore",MODE_PRIVATE);
        newScore = Integer.parseInt(preferences.getString("maxScore","0"));

        mUserAccount = myRef.child(user.getUid());
        Toast.makeText(RegistrActivity.this,newScore+"",Toast.LENGTH_SHORT);
        //mUserAccount.child("name").setValue(user.getDisplayName());

        textScore.setText(newScore+"");
        //mUserAccount.setValue(user.getUid());
        save.setVisibility(View.VISIBLE);


    }

    public void save(View view) {
        if(textName.length()!=0){
            mUserAccount.child("name").setValue(textName.getText()+"");
            mUserAccount.child("score").setValue(newScore);
        }
        else {
            Toast.makeText(RegistrActivity.this,"Введите имя",Toast.LENGTH_SHORT);
        }
    }
}
