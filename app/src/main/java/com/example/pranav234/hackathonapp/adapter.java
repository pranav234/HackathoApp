package com.example.pranav234.hackathonapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class adapter extends  RecyclerView.Adapter<adapter.noteholder> {

    private ArrayList<Details> arrayList;

    public adapter(ArrayList<Details> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public noteholder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tender_layout,viewGroup,false);
        return new noteholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final noteholder noteholder, int i) {
        noteholder.company_name.setText(arrayList.get(i).getCompany_name());
        noteholder.tender_category.setText(arrayList.get(i).getTender_category());
        noteholder.venue.setText(arrayList.get(i).getVenue());
        noteholder.deadline.setText(arrayList.get(i).getDead_line());
        noteholder.one_line.setText(arrayList.get(i).getDetail());
        noteholder.base_price.setText(arrayList.get(i).getBase_price());
        noteholder.people_going.setText(arrayList.get(i).getPeople_going());
        noteholder.interested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(noteholder.people_going.getText().toString());
                noteholder.people_going.setText(String.valueOf(a+1));
                noteholder.interested.setVisibility(View.INVISIBLE);
            }
        });
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class noteholder extends RecyclerView.ViewHolder{
        TextView company_name,tender_category,venue,deadline,one_line,base_price,people_going;
        TextView interested;
        CheckBox checkBox;
        public noteholder(@NonNull View itemView) {
            super(itemView);
            company_name=itemView.findViewById(R.id.company_name);
            tender_category=itemView.findViewById(R.id.tender_category);
            venue=itemView.findViewById(R.id.venue);
            deadline=itemView.findViewById(R.id.dead_date);
            one_line=itemView.findViewById(R.id.one_line);
            base_price=itemView.findViewById(R.id.base_price);
            interested = itemView.findViewById(R.id.interest);
            people_going=itemView.findViewById(R.id.people_going);


        }
    }
}
