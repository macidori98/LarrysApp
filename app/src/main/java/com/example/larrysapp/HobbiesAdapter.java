package com.example.larrysapp;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HobbiesAdapter extends RecyclerView.Adapter<HobbiesAdapter.MyViewHolder> {
    private Context context;
    private List<Hobby> hobbiesList;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView hobby;
        public TextView dot;
        public TextView timestamp;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            hobby = itemView.findViewById(R.id.note);
            dot = itemView.findViewById(R.id.dot);
            timestamp = itemView.findViewById(R.id.timestamp);
        }
    }

    public HobbiesAdapter(Context context, List<Hobby> hobbiesList){
        this.context = context;
        this.hobbiesList = hobbiesList;
    }

    @NonNull
    @Override
    public HobbiesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hobby_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HobbiesAdapter.MyViewHolder holder, int position) {
        Hobby hobby = hobbiesList.get(position);
        holder.hobby.setText(hobby.getHobby());

        holder.dot.setText(Html.fromHtml("&#8226;"));

        holder.timestamp.setText(formatDate(hobby.getTimestamp()));
    }

    @Override
    public int getItemCount() {
        return hobbiesList.size();
    }

    private String formatDate(String dateStr) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = fmt.parse(dateStr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("MMM d");
            return fmtOut.format(date);
        } catch (ParseException e) {

        }

        return "";
    }
}
