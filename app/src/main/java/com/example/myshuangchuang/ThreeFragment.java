package com.example.myshuangchuang;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ThreeFragment extends Fragment {
    private TextView  mypage_textview;
    private TextView  mypage_textview_upload;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        initView(view);
    return view;

    }



    public class TextViewListener implements View.OnClickListener{
        public  void onClick(View view)
        {
            if (view.getId() == R.id.MytextView3) {
               // Intent intent = new Intent(getActivity(), mypage.class);
               // startActivity(intent);
            }
            if (view.getId() == R.id.mytextView7) {
             //   Intent intent = new Intent(getActivity(), my_uploadActivity.class);
              //  startActivity(intent);
            }
        }
    }
    private void initView( View view){
        //为我的主页textview设置监听
        mypage_textview = view.findViewById(R.id.MytextView3);
        mypage_textview.getBackground().setAlpha(0);
        mypage_textview.setOnClickListener(new TextViewListener());
        //为我的发布textview设置监听
        mypage_textview = view.findViewById(R.id.mytextView7);
        mypage_textview.getBackground().setAlpha(0);
        mypage_textview.setOnClickListener(new TextViewListener());
    }
}

