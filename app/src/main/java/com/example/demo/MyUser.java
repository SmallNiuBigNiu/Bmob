package com.example.demo;

import java.io.Serializable;

import cn.bmob.v3.BmobUser;

public class MyUser extends BmobUser implements Serializable {
    private int age;
    private String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
