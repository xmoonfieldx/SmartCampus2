package com.example.smartcampus;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainMenu extends AppCompatActivity {

    Button signOut, qr;
    TextView textView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        signOut = findViewById(R.id.signout);
        qr = findViewById(R.id.qr);
        textView = findViewById(R.id.tv);

        Intent r = getIntent();
        String mail = r.getStringExtra("key_mail");
        //String n = r.getStringExtra("yes");
        try{
            //String mails = mail.replace("//.",",");
        }
        finally {
            textView.setText(mail);
        }


        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenu.this,displayqr.class);
                i.putExtra("key_mail",mail);
                startActivity(i);
                finish();
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(MainMenu.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}