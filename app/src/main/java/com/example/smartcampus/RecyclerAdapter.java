package com.example.smartcampus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.LeaderHolder>{
    private ArrayList<String> no;
    private ArrayList<String> points;
    private ArrayList<String>usn;
    private ArrayList<String>name;
    private Context context;

    public RecyclerAdapter(ArrayList<String> no, ArrayList<String> points, ArrayList<String> usn, ArrayList<String> name, Context context) {
        this.no = no;
        this.points = points;
        this.usn = usn;
        this.name = name;
        this.context = context;
    }

    @NonNull
    @Override
    public LeaderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new LeaderHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderHolder holder, int position) {
        holder.nol.setText(no.get(position));
        holder.pointsl.setText(name.get(position));
        holder.usnl.setText(usn.get(position));
        holder.namel.setText(name.get(position));

    }

    @Override
    public int getItemCount() {
        return no.size();
        //return no.size();
    }

    public class LeaderHolder extends RecyclerView.ViewHolder {
        private TextView nol, pointsl, usnl, namel;
        public LeaderHolder(@NonNull View itemView) {
            super(itemView);
            nol = itemView.findViewById(R.id.no);
            pointsl = itemView.findViewById(R.id.points);
            usnl = itemView.findViewById(R.id.usns);
            namel = itemView.findViewById(R.id.names);
        }
    }
    /*public class LeaderHolder extends RecyclerView.ViewHolder {

        private TextView no, points, usn, name;
        public LeaderHolder(@NonNull View itemView) {
            super(itemView);
            no = itemView.findViewById(R.id.no);
            points = itemView.findViewById(R.id.points);
            usn = itemView.findViewById(R.id.usns);
            name = itemView.findViewById(R.id.names);
        }
    }*/
}
