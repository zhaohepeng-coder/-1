package com.example.myshuangchuang;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import network_package.HttpLogin;

public class textActivity extends AppCompatActivity {

   private TextView textView ;
   private TextView textvv;
   private  TextView  textdd;
  //rivate WebView   webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
     // webview =(WebView)findViewById(R.id.web_view);
        textView = (TextView)findViewById(R.id.text_1);
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        textView.setSelected(true);
        textvv = (TextView)findViewById(R.id.text_2);
        textdd = (TextView)findViewById(R.id.you ) ;
        textdd.setSelected(true);
        textvv.setSelected(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = HttpLogin.checkuser("iugyug","iugui");
                System.out.println(result);
                Looper.prepare();
                Toast.makeText(textActivity.this,result,Toast.LENGTH_SHORT).show();

                Looper.loop();
            }
        }).start();

/*
   webview.getSettings().setJavaScriptEnabled(true);//有的页面有js代码  让这个控件装备识别他的武器
        webview.setWebViewClient(new MywebViewClient());//让自己装配成为一个客户端，new MywebViewClient()就是装配
       webview.loadUrl("https://m.baidu.com");//他要根据网址找代码再放到自己页面 白嫖*/
       // webview.loadUrl("file:///android_asset/love3/index.html");
 /*
   class  MywebViewClient extends WebViewClient{

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
           try {
              String  url= request.getUrl().toString();
               if(!url.startsWith("http")||!url.startsWith("https")) {
                  url =url+"https://";
               }
           }catch (Exception e)
           {
               return false;
           }
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
             Log.d("webview","onpagestart....");
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.d("webview","onpagefinished....");
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode ==KeyEvent.KEYCODE_BACK&&webview.canGoBack())
        {
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
*/
}
}