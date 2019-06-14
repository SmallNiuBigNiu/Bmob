package com.example.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class StudentListActivity extends Activity{
    private RecyclerView stuList;
    private StudentAdapter studentAdapter;
    private ArrayList<Student> studentArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        stuList = findViewById(R.id.stu_list);
        studentAdapter = new StudentAdapter(studentArrayList);
        stuList.setAdapter(studentAdapter);
        stuList.setLayoutManager(new LinearLayoutManager(StudentListActivity.this));
        getStudentFromBmob();
    }

    // 监听者

    private void getStudentFromBmob() {
        BmobQuery<Student> studentBmobQuery = new BmobQuery<>();
        studentBmobQuery.findObjects(new FindListener<Student>() {
            @Override
            public void done(List<Student> list, BmobException e) {
                if(e == null){
                    Log.e("BMOB", "listsize = " + list.size());
                    studentArrayList.clear();
                    studentArrayList.addAll(list);
                    studentAdapter.changeData(studentArrayList);
                }else{
                    Log.e("BMOB", e.getMessage());
                }
            }
        });
    }
}
