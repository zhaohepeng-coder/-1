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

public class StaggerAdapter extends RecyclerView.Adapter<StaggerAdapter.StaggerViewHolder>{
    private Context mContext;
    private LinearAdapter.OnItemClickListener mlistener;
    private ArrayList<ImageBean> im_bean;
    public StaggerAdapter(Context context, LinearAdapter.OnItemClickListener listener, ArrayList<ImageBean> im_bean1){
        this.mContext = context;
        this.mlistener=listener;
        this.im_bean =im_bean1;
    }
    @NonNull
    @Override
    public StaggerAdapter.StaggerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new StaggerAdapter.StaggerViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_stagger,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull StaggerViewHolder holder, final int position) {
        holder.textview.setText(im_bean.get(position%im_bean.size()).getName());
        Log.e("dd","dd");
        Glide.with(mContext)
                .load(im_bean.get(position%im_bean.size()).getImageUrl())
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
        return 40;
    }

    public interface OnItemClickListener {
        void onClick(int pos);
    }

    class StaggerViewHolder extends  RecyclerView.ViewHolder{
        private TextView textview;
        private ImageView imagvi;
        public StaggerViewHolder(@NonNull View itemView) {
            super(itemView);
            textview =(TextView) itemView.findViewById(R.id.titles);
            imagvi =(ImageView) itemView.findViewById(R.id.images);
        }
    }
}
