package com.example.demo;

import android.app.Application;

import cn.bmob.v3.Bmob;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, "d8b3408f937ae0ff184914479c079b28");
    }
}
