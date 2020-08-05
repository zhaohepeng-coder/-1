package com.example.myshuangchuang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import network_package.HttpLogin;

public class Main2Activity extends AppCompatActivity {

   Button  button_register;
   ImageView test;
   private HashMap<String,String>  register_hash = new HashMap<>();
   EditText account,username,password,password1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main2);
        init();
        Glide.with(this).load("http://i1.mopimg.cn/img/dzh/2017-11/665/2017112716155333.jpg").into(test);

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().toString().equals("")||username.getText().toString().equals(""))
                {
                    Toast.makeText(Main2Activity.this,"注册失败",Toast.LENGTH_SHORT).show();
                    return ;
                }
                if(! password.getText().toString().equals(password1.getText().toString()))
                {
                    Toast.makeText(Main2Activity.this,"两次密码不一致",Toast.LENGTH_SHORT).show();
                    password1.setText("");
                    return;
                }
                else
                {
                    //开启子线程判断
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //开始注册信息
                            String  result="";
                          //  result= HttpLogin.RegisterByPost(password.getText().toString(),username.getText().toString());
                            //获取返回信息
                            try {
                                JSONObject result_json = new JSONObject(result);
                                 String message = result_json.getString("status");
                            if(message.equals("0"))
                           {
                               Looper.prepare();
                               Toast.makeText(Main2Activity.this,"注册失败",Toast.LENGTH_SHORT).show();
                               Looper.loop();

                           }
                            else
                            {
                                Intent itnt_rgst_rgst = new Intent(Main2Activity.this,MainActivity.class);
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
              Toast.makeText(Main2Activity.this,"注册成功",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void init()
    {
        password1 =  (EditText)findViewById(R.id.re_assurepassword);
        button_register=(Button)findViewById(R.id.button_register);
        username = (EditText)findViewById(R.id.et_1);
        password = (EditText)findViewById(R.id.re_password);
        test =(ImageView)findViewById(R.id.Test);


    }
}
