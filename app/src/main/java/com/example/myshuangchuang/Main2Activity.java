package com.example.myshuangchuang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import network_package.HttpLogin;

public class Main2Activity extends AppCompatActivity {

   Button  button_register;
   private HashMap<String,String>  register_hash = new HashMap<>();
   EditText account,username,password,password1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main2);
        init();


        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().toString().equals("")||account.getText().toString().equals("")||username.getText().toString().equals(""))
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
                          //  result= HttpLogin.RegisterByPost(account.getText().toString(),password.getText().toString(),username.getText().toString());
                            //获取返回信息
                           if(result.equals(""))
                           {
                               Looper.prepare();
                               Toast.makeText(Main2Activity.this,"两次密码致",Toast.LENGTH_SHORT).show();
                               Looper.loop();

                           }
                            //根据信息判断是否注册成功

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
        account =(EditText)findViewById(R.id.et_account);
        username = (EditText)findViewById(R.id.et_1);
        password = (EditText)findViewById(R.id.re_password);

    }
}
