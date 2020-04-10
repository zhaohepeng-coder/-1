package com.example.myshuangchuang;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;

public class textActivity extends AppCompatActivity {

   private TextView textView ;
   private TextView textvv;
   private  TextView  textdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        textView = (TextView)findViewById(R.id.text_1);
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        textView.setSelected(true);
        textvv = (TextView)findViewById(R.id.text_2);
        textdd = (TextView)findViewById(R.id.you ) ;
        textdd.setSelected(true);
        textvv.setSelected(true);

    }
}
