package com.example.smartcampus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    EditText usn, pswd;
    Button enter;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usn = findViewById(R.id.usn1);
        pswd = findViewById(R.id.pswd1);
        enter = findViewById(R.id.signup1);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userusn = usn.getText().toString();
                String userpswd = pswd.getText().toString();

                signUpFirebase(userusn, userpswd);
            }
        });

    }
    public void signUpFirebase(String userusn, String userpswd){
        auth.createUserWithEmailAndPassword(userusn, userpswd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(SignUp.this, "Your account has been created", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(SignUp.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignUp.this, "Some problem was encountered. Please try again later.", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}