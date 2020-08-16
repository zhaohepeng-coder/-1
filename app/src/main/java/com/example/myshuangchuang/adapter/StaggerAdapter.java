package com.example.myshuangchuang.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.myshuangchuang.JavaBean.ImageBean;
import com.example.myshuangchuang.R;

import java.util.ArrayList;

public class StaggerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private LinearAdapter.OnItemClickListener mlistener;
    private ArrayList<ImageBean> im_bean;
    public static final int TYPE_NOMAL = 0;
    public static final int TYPE_LOADER_MORE = 1;
    private OnRefreshListener mUpPullRefreshListener;

    public StaggerAdapter(Context context, LinearAdapter.OnItemClickListener listener, ArrayList<ImageBean> im_bean1){
        this.mContext = context;
        this.mlistener=listener;
        this.im_bean =im_bean1;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        Log.e("we", String.valueOf(viewType));
              if(viewType==TYPE_NOMAL)
              {
                  return new StaggerAdapter.StaggerViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_stagger,parent,false));
              }
              else
              {
                  View view =LayoutInflater.from(mContext).inflate(R.layout.activity_pul_loading,parent,false);

                  StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
                  layoutParams.setFullSpan(true);
                  view.setLayoutParams(layoutParams);
                  return new LoadViewHolder(view);


              }

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position)==TYPE_NOMAL&&holder instanceof StaggerViewHolder)
        {
            onBindViewHolder1((StaggerViewHolder)holder,position);
        }
        else if(getItemViewType(position)==TYPE_LOADER_MORE&&holder instanceof LoadViewHolder)
        {

            ((LoadViewHolder) holder).update(LoadViewHolder.LOADER_STATE_RELOAD);


            Log.e("554","jijij");
        }
    }




    public void onBindViewHolder1(@NonNull StaggerViewHolder holder, final int position) {
        holder.textview.setText(im_bean.get(position%im_bean.size()).getName());

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
        return 20;
    }
     /**
      * 设置上拉监听事件
      */
    public void setOnRefreshListener(OnRefreshListener listener) {
        this.mUpPullRefreshListener =  listener;
    }
    public interface OnRefreshListener
    {
        void onUpPullRefresh(LoadViewHolder loadViewHolder);
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

   public class LoadViewHolder extends  RecyclerView.ViewHolder{
        public static final  int LOADER_STATE_LOADING =0;
        public static final  int LOADER_STATE_RELOAD =1;
        public static final  int LOADER_STATE_NOMAL =2;

        private TextView mReload;
        private LinearLayout loading;
        public LoadViewHolder(@NonNull View itemView) {
            super(itemView);
            
            loading =(LinearLayout) itemView.findViewById(R.id.loading);
            mReload =(TextView) itemView.findViewById(R.id.load_failure);
            mReload.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //触发加载事件

                 startLoadMore();
                }
            });

        }

        public void  update(int state)
        {
            loading.setVisibility(View.GONE);
            mReload.setVisibility(View.GONE);
            switch (state){
            case LOADER_STATE_LOADING:
                    loading.setVisibility(View.VISIBLE);

                    break;
                case  LOADER_STATE_RELOAD:
                    mReload.setVisibility(View.VISIBLE);

                    break;
                case LOADER_STATE_NOMAL:
                    mReload.setVisibility(View.GONE);
                    loading.setVisibility(View.GONE);
                    break;



        }
        }

        private void startLoadMore() {
            if(mUpPullRefreshListener!=null)
            {
                update(LOADER_STATE_LOADING);
                mUpPullRefreshListener.onUpPullRefresh(this);
            }
        }
    }



    public int getItemViewType(int position){

    if(position == getItemCount()-1)
        {
            return  TYPE_LOADER_MORE;
        }
        else
            return TYPE_NOMAL;
    }
}
