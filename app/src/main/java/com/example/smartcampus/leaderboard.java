package com.example.smartcampus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class leaderboard extends AppCompatActivity {
    public RecyclerView recyclerView = findViewById(R.id.re);
    String m, k, m1, m2, m3;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("reward");

    //tv = findViewById(R.id.usns);
    public  RecyclerAdapter adapter;
    public ArrayList<String>nol = new ArrayList<>();
    public ArrayList<String>pointsl = new ArrayList<>();
    public ArrayList<String>usnl = new ArrayList<>();
    public ArrayList<String>namel = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        recyclerView.setLayoutManager(new LinearLayoutManager(leaderboard.this));


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int n = (int) snapshot.getChildrenCount();
                for(int i=1; i<=n; i++)
                {
                    k=String.valueOf(i);
                    nol.add(k);
                    m1 = (String) snapshot.child(k).child("points").getValue();
                    pointsl.add(m1);
                    m2 = (String) snapshot.child(k).child("usn").getValue();
                    usnl.add(m2);
                    m3 = (String) snapshot.child(k).child("name").getValue();
                    namel.add(m3);
                }
                adapter = new RecyclerAdapter(nol, pointsl, usnl, namel,leaderboard.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}