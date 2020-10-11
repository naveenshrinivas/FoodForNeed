package com.example.donatefood;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList info_id, donor_name, location_donor, phone_no, dish_name, comments, food_type;



    Animation translate_anim;


    CustomAdapter(Context context,
                  ArrayList info_id,
                  ArrayList donor_name,
                  ArrayList location_donor,
                  ArrayList food_type,
                  ArrayList phone_no,
                  ArrayList dish_name,
                  ArrayList comments) {
        this.context = context;
        this.info_id = info_id;
        this.donor_name = donor_name;
        this.location_donor = location_donor;
        this.food_type = food_type;
        this.phone_no = phone_no;
        this.dish_name = dish_name;
        this.comments = comments;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.info_id_txt.setText(String.valueOf(info_id.get(position)));
        holder.name_of_donor_txt.setText(String.valueOf(donor_name.get(position)));
        holder.location_txt.setText(String.valueOf(location_donor.get(position)));
        holder.food_txt.setText(String.valueOf(food_type.get(position)));
        holder.phone_number_txt.setText(String.valueOf(phone_no.get(position)));
        holder.dish_name_txt.setText(String.valueOf(dish_name.get(position)));
        holder.comments_txt.setText(String.valueOf(comments.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(info_id.get(position)));
                intent.putExtra("name", String.valueOf(donor_name.get(position)));
                intent.putExtra("location", String.valueOf(location_donor.get(position)));
                intent.putExtra("typeoffood", String.valueOf(food_type.get(position)));
                intent.putExtra("mobilenumber", String.valueOf(phone_no.get(position)));
                intent.putExtra("nameofthedish", String.valueOf(dish_name.get(position)));
                intent.putExtra("commentsforpost", String.valueOf(comments.get(position)));
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return info_id.size();
    }





    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView info_id_txt, name_of_donor_txt, location_txt, food_txt, phone_number_txt, dish_name_txt, comments_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            info_id_txt = itemView.findViewById(R.id.info_id_text);
            name_of_donor_txt = itemView.findViewById(R.id.name_of_donor_txt);
            location_txt = itemView.findViewById(R.id.location_txt);
            food_txt = itemView.findViewById(R.id.food_txt);
            phone_number_txt = itemView.findViewById(R.id.phone_number_txt);
            dish_name_txt = itemView.findViewById(R.id.dish_name_txt);
            comments_txt = itemView.findViewById(R.id.comments_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate RecyclerView
            translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);

        }
    }
}
