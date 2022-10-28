package com.csse.transport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HireAdapter extends RecyclerView.Adapter<HireAdapter.MyViewHolder> {

    ArrayList<Model> hList;
    Context context;

    public HireAdapter(Context context, ArrayList<Model> hList){

        this.hList = hList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.hires, parent, false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model modelHire = hList.get(position);
        holder.Date.setText(modelHire.getDate());
        holder.Time.setText(modelHire.getTime());
        holder.Destination.setText(modelHire.getDestination());
        holder.Days.setText(modelHire.getDays());
        holder.People.setText(modelHire.getPeople());
    }

    @Override
    public int getItemCount() {
        return hList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Date, Time, Destination, Days, People;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Date = itemView.findViewById(R.id.tex_HiredDate);
            Time = itemView.findViewById(R.id.tex_HiredTime);
            Destination = itemView.findViewById(R.id.tex_HiredPlace);
            Days = itemView.findViewById(R.id.tex_HiredDays);
            People = itemView.findViewById(R.id.tex_HiredPeople);
        }
    }
}
