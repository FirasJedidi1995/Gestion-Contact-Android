package com.example.gestioncontact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText email,password;
    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth=FirebaseAuth.getInstance();
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);

    }
    public void SignIn(View view) {
        String userEmail=email.getText().toString().trim();
        String userPassword=password.getText().toString().trim();
        if (userEmail.isEmpty()){
            Toast.makeText(this, "please enter your Email", Toast.LENGTH_SHORT).show();
        }else if (userPassword.isEmpty()){
            Toast.makeText(this, "please enter your password", Toast.LENGTH_SHORT).show();
        }else if (userPassword.length()<6){
            Toast.makeText(this, "password too short,enter minimum 6 characters !", Toast.LENGTH_SHORT).show();
        }
        auth.signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, MainActivity.class));
                }else {
                    Toast.makeText(Login.this, "Login Failed"+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void SignUp(View view) {
        startActivity(new Intent(Login.this, Register.class));
    }

}