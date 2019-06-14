package com.example.demo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import cn.bmob.v3.datatype.BmobFile;

class StudentAdapter extends RecyclerView.Adapter<StuViewHolder> {
    private ArrayList<Student> studentArrayList;

    public StudentAdapter(ArrayList<Student> studentArrayList) {
        this.studentArrayList = studentArrayList;
    }

    @NonNull
    @Override
    public StuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        StuViewHolder stuViewHolder = new StuViewHolder(itemView);
        return stuViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StuViewHolder stuViewHolder, int index) {
        Student student = studentArrayList.get(index);
        BmobFile headimg = student.getHeadimg();
        if(headimg != null){
            Glide.with(stuViewHolder.headImg.getContext()).load(student.getHeadimg().getUrl()).into(stuViewHolder.headImg);
        }
        stuViewHolder.nameView.setText(student.getName());
        stuViewHolder.ageView.setText(String.valueOf(student.getAge()));
    }

    @Override
    public int getItemCount() {
        return studentArrayList.size();
    }

    public void changeData(ArrayList<Student> studentArrayList) {
        this.studentArrayList = studentArrayList;
        notifyDataSetChanged();
    }
}
