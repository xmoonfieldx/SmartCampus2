package com.example.smartcampus;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;


public class displayqr extends AppCompatActivity {
    //Initialize variables
    Button btGenerate;
    ImageView ivOutput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayqr);

        //Assign variables
        btGenerate = findViewById(R.id.idBtnGenerateQR);
        ivOutput = findViewById(R.id.idIVQrcode);
        Intent r = getIntent();
        String text = r.getStringExtra("key_mail");
        //TextView t = findViewById(R.id.tt);
        //t.setText(text);
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(text, BarcodeFormat.QR_CODE, 400, 400);

            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            ivOutput.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        btGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get input value from edit text
                Intent i = new Intent(displayqr.this,MainMenu.class);
                startActivity(i);
                finish();
            }
        });
    }
}
