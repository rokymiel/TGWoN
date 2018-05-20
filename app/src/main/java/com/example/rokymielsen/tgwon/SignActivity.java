package com.example.rokymielsen.tgwon;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG ="ONCLICK" ;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private EditText ETemail;
    private EditText ETpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        ETemail = (EditText) findViewById(R.id.eTeMail);
        ETpassword = (EditText) findViewById(R.id.eTpassword);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(SignActivity.this,ScoreActivity.class);
                    startActivity(intent);

                } else {
                    // User is signed out

                }


            }
        };


        findViewById(R.id.signIn).setOnClickListener(this);
        findViewById(R.id.registr).setOnClickListener(this);
        FirebaseUser user = mAuth.getCurrentUser();
        /*if (user != null) {
            Intent intent = new Intent(SignActivity.this,ScoreActivity.class);
            startActivity(intent);

        } else {
            // User is signed out

        }*/
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, ETemail.getText().toString());
        if (ETemail.getText().toString().length() == 0 || ETpassword.getText().toString().length() == 0) {
            Toast.makeText(SignActivity.this, "Заполните пожалуйста поля", Toast.LENGTH_SHORT).show();
        } else {
            if (view.getId() == R.id.signIn) {
                signin(ETemail.getText().toString(), ETpassword.getText().toString());
            } else if (view.getId() == R.id.registr) {
                registration(ETemail.getText().toString(), ETpassword.getText().toString());
            }
        }
    }

    public void signin(String email , String password)
    {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(SignActivity.this, "Aвторизация успешна", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignActivity.this,ScoreActivity.class);
                    startActivity(intent);
                }else
                    Toast.makeText(SignActivity.this, "Aвторизация провалена", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void registration (String email , String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(SignActivity.this, "Регистрация успешна", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignActivity.this,RegistrActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(SignActivity.this, "Регистрация провалена", Toast.LENGTH_SHORT).show();
            }
        });
    }
}