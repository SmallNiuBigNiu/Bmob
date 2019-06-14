package com.example.demo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class StuViewHolder extends RecyclerView.ViewHolder {
    ImageView headImg;
    TextView nameView;
    TextView ageView;

    public StuViewHolder(@NonNull View itemView) {
        super(itemView);
        headImg = itemView.findViewById(R.id.head_img);
        nameView = itemView.findViewById(R.id.name_text);
        ageView = itemView.findViewById(R.id.age_text);
    }
}
