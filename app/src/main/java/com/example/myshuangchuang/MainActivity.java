package com.example.myshuangchuang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import network_package.HttpLogin;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
//初始化空件

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);
        final TextView   forgetNumber = findViewById(R.id.forget);
        forgetNumber.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
        forgetNumber.getPaint().setAntiAlias(true);
        final TextView   register = findViewById(R.id.zhuce);

        //xiang'jian'zhi'dui




               loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  //显示进度条
                    loadingProgressBar.setVisibility(View.VISIBLE);
                //判断是否符合逻辑，若不符合，提示不行
                if(usernameEditText.getText().toString().equals("")||passwordEditText.getText().toString().equals(""))
               {
                   Toast.makeText(MainActivity.this,"用户名和密码不为空",Toast.LENGTH_SHORT).show();
                   loadingProgressBar.setVisibility(View.GONE);
               }
               else{
                   //开启验证线程
                    //开启子线程判断
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String  result="";
                          //  result= HttpLogin.LoginByPost(usernameEditText.getText().toString(),passwordEditText.getText().toString());
                            //获取返回信息
                            try {
                                JSONObject result_json = new JSONObject(result);
                                String message = result_json.getString("status");
                                if(message.equals("0"))
                                {
                                    Looper.prepare();
                                    Toast.makeText(MainActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                                    Looper.loop();

                                }
                                else
                                {
                                    Intent itnt_rgst_rgst = new Intent(MainActivity.this,MainActivity.class);
                                    startActivity(itnt_rgst_rgst);
                                    finish();
                                }
                                //根据信息判断是否注册成功
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }).start();
                }
            }});


        register.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v)
            {
                Intent intent1 = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent1);
                showtoast(v);
            }
        });

    }

    public void showtoast(View view){
        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
    }
}
