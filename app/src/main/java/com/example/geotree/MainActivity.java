package com.example.geotree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.geotree.Leaderboard.Leaderboard;
import com.example.geotree.Map.MapActivity;
import com.example.geotree.User.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;

public class MainActivity extends AppCompatActivity {

    EditText mLoginEmail, mLoginPassword, mSignUpName, mSignUpEmail, mSignUpPassword;
    Button mLogin, mSignUp;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        //userIsLoggedIn();

        mLoginEmail = findViewById(R.id.loginEmail);
        mLoginPassword = findViewById(R.id.loginPassword);
        mSignUpName = findViewById(R.id.signUpName);
        mSignUpEmail = findViewById(R.id.signUpEmail);
        mSignUpPassword = findViewById(R.id.signUpPassword);

        mLogin = findViewById(R.id.login);
        mSignUp = findViewById(R.id.signUp);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logInWithEmailAuthCredential();
            }
        });

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpWithEmailAuthCredential();
            }
        });
    }

    private void logInWithEmailAuthCredential() {
        mAuth.signInWithEmailAndPassword(mLoginEmail.getText().toString(), mLoginPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            userIsLoggedIn();
                        } else {
                            mLogin.setText("Login Failed");
                        }
                    }
                });
    }

    private void signUpWithEmailAuthCredential() {
        mAuth.createUserWithEmailAndPassword(mSignUpEmail.getText().toString(), mSignUpPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User u = new User(mSignUpName.getText().toString(), mSignUpEmail.getText().toString(), mSignUpPassword.getText().toString());
                            Leaderboard.getInstance().addUser(u);
                            MapActivity.user = u;
                            userIsLoggedIn();
                        }
                    }
                });
    }

    private void userIsLoggedIn() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {

            for (User u : Leaderboard.getInstance().getUsers()) {
                if (u.equals(mLoginEmail.getText().toString())) {
                    MapActivity.user = u;
                    break;
                }
            }

            startActivity(new Intent(getApplicationContext(), MapActivity.class));
            finish();
            return;
        }
    }
}
