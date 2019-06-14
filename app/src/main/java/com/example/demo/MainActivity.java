package com.example.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class MainActivity extends AppCompatActivity {
    private Button btnReg;
    private Button btnLogin;
    private Button btnAdd;
    private Button btnQuery;
    private Button btnUpdate;
    private Button btnDel;
    private Button btnUpdateUser;
    private Button btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBtns();
    }

    /**
     * 初始化所有按钮
     */
    private void initBtns() {
        btnReg = findViewById(R.id.btn_reg);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });

        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser bmobUser = new BmobUser();
                bmobUser.setUsername("迪丽热巴");
                bmobUser.setPassword("123456");
                bmobUser.login(new SaveListener<BmobUser>() {
                    @Override
                    public void done(BmobUser bmobUser, BmobException e) {
                        if(e == null){
                            toast("登陆成功" + bmobUser.getUsername());
                        }else{
                            toast("登陆失败" + e.getMessage());
                        }
                    }
                });
            }
        });

      //更新按钮点击事件
        btnUpdateUser = findViewById(R.id.btn_updateUser);
        btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
                if(myUser == null){
                    toast("请先登陆");
                    return;
                }

                myUser.setGender("男");
                myUser.setAge(18);
                myUser.update(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e == null){
                            toast("用户信息更新成功");
                        }else
                        {
                            toast("用户信息更新失败 " + e.getMessage());
                        }
                    }
                });

            }
        });
    //添加按钮点击事件
        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Student student = new Student();
                student.setName("张三");
                student.setAge(20);
                student.setProfession("软件");
                student.setScore(80);
                student.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if(e == null){
                            toast("保存成功  " + student.getName());
                        }
                    }
                });
            }
        });
    //查询按钮点击事件
        btnQuery = findViewById(R.id.btn_query);
        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobQuery<Student> studentBmobQuery = new BmobQuery<>();
                 studentBmobQuery.addWhereEqualTo("age", 18);
                studentBmobQuery.findObjects(new FindListener<Student>() {
                    @Override
                    public void done(List<Student> list, BmobException e) {
                        if(e == null){
                            StringBuffer buffer = new StringBuffer();
                            for(Student student :list){
                                buffer.append(student.toString()).append("\n");
                            }
                            toast(buffer.toString());
                        }
                    }
                });
            }
        });

        btnList = findViewById(R.id.btn_list);
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, StudentListActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 添加学生方法
     */
    private void addUser() {
        MyUser myUser = new MyUser();
        myUser.setUsername("胡浩浩");
        myUser.setPassword("123456");
        myUser.setAge(19);
        myUser.setGender("女");
        myUser.signUp(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if(e == null){
                    toast("注册成功" + bmobUser.getObjectId());
                }else{
                    toast("注册失败" + e.getMessage());
                }
            }
        });
    }

    private void toast(String s) {
        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
    }
}
