package com.example.myshuangchuang.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myshuangchuang.JavaBean.ImageBean;
import com.example.myshuangchuang.R;

import java.util.ArrayList;

public class LinearAdapter extends RecyclerView.Adapter<LinearAdapter.LinearViewHolder> {
    private Context mContext;
    private OnItemClickListener mlistener;
    private ArrayList<ImageBean> im_bean;
    public LinearAdapter(Context context, OnItemClickListener listener, ArrayList<ImageBean> im_bean1){
        this.mContext = context;
        this.mlistener=listener;
        this.im_bean =im_bean1;
    }
    @NonNull
    @Override
    public LinearAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_linear_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LinearViewHolder holder, final int position) {
        holder.textview.setText(im_bean.get(position).getName());
        Log.e("dd","dd");
      Glide.with(mContext)
                .load(im_bean.get(position).getImageUrl())
                .into(holder.imagvi);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    mlistener.onClick(position);
            }

        });
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    public interface OnItemClickListener {
        void onClick(int pos);
    }

    class LinearViewHolder extends  RecyclerView.ViewHolder{
              private TextView textview;
              private ImageView imagvi;
        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            textview =(TextView) itemView.findViewById(R.id.tv_title);
            imagvi =(ImageView) itemView.findViewById(R.id.image_vi);
        }
    }
}
