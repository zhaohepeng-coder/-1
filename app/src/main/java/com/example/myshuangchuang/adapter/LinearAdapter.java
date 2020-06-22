package com.example.myshuangchuang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myshuangchuang.R;

public class LinearAdapter extends RecyclerView.Adapter<LinearAdapter.LinearViewHolder> {
    private Context mContext;
    public LinearAdapter(Context context){
        this.mContext = context;
    }
    @NonNull
    @Override
    public LinearAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_linear_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LinearViewHolder holder, int position) {
        holder.textview.setText("hojkjkjjkj");

    }

    @Override
    public int getItemCount() {
        return 30;
    }
    class LinearViewHolder extends  RecyclerView.ViewHolder{
              private TextView textview;
        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            textview =(TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
