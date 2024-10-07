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

public class Register extends AppCompatActivity {
    EditText name,email,password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth=FirebaseAuth.getInstance();
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);

        if (auth.getCurrentUser() !=null){
            startActivity(new Intent(Register.this,MainActivity.class));
            finish();
        }
    }
    public void SignUp(View view) {
        String username = name.getText().toString().trim();
        String userEmail = email.getText().toString().trim();
        String userPassword = password.getText().toString().trim();
        if (username.isEmpty()) {
            Toast.makeText(this, "please enter your Name", Toast.LENGTH_SHORT).show();
        } else if (userEmail.isEmpty()) {
            Toast.makeText(this, "please enter your Email", Toast.LENGTH_SHORT).show();
        } else if (userPassword.isEmpty()) {
            Toast.makeText(this, "please enter your password", Toast.LENGTH_SHORT).show();
        } else if (userPassword.length() < 5) {
            Toast.makeText(this, "password too short,enter minimum 5 characters !", Toast.LENGTH_SHORT).show();
        }
        auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Register.this, "Successfully Register", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this, Login.class));
                } else {
                    Toast.makeText(Register.this, "Registration Failed" + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void SignIn(View view) {
        startActivity(new Intent(Register.this,Login.class));
    }
}

