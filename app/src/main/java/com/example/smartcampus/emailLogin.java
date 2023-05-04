package com.example.smartcampus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class emailLogin extends AppCompatActivity {

    EditText usn, pswd, phones;
    Button signin, signup, forgot, phone;
    String mail, x, y, gf;
    SharedPreferences sharedPref;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_login);
        ActionBar actionBar = getSupportActionBar();
        Drawable background = getResources().getDrawable(R.color.my_actionbar_color);
        actionBar.setBackgroundDrawable(background);
        usn = findViewById(R.id.usn);
        pswd = findViewById(R.id.pswd);
        phones = findViewById(R.id.phones);
        signin = findViewById(R.id.signin);
        forgot = findViewById(R.id.forgot);
        //x = phones.getText().toString();
        //x="7218081467";
        //k.setText(y);


        /*phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(emailLogin.this, PhoneSign.class);
                startActivity(intent);
                finish();
            }
        });*/
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(emailLogin.this, ForgotActivity.class);
                startActivity(i);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String y=phones.getText().toString();
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        mail = (String) snapshot.child("ID1").child(y).getValue();
                        sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("mail", mail);
                        editor.apply();


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                String userMail = usn.getText().toString();
                String userPassword = pswd.getText().toString();
                signInFirebase(userMail,userPassword);


            }
        });
    }

    public void  signInFirebase(String userMail, String userPassword){
        auth.signInWithEmailAndPassword(userMail, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //String mails = phones.getText().toString();
                            Intent i = new Intent(emailLogin.this,MainMenu.class);
                            i.putExtra("key_mail",mail);
                            i.putExtra("yes",y);
                            startActivity(i);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(emailLogin.this, "Kindly recheck your credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();
        if(user != null){
            Intent i = new Intent(emailLogin.this,MainMenu.class);
            startActivity(i);
            finish();
        }
    }
}