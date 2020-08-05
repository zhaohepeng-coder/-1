package com.example.myshuangchuang;


import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myshuangchuang.JavaBean.ImageBean;
import com.example.myshuangchuang.adapter.LinearAdapter;
import com.example.myshuangchuang.adapter.StaggerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * Created by Administrator on 2016/7/13.
 */
@SuppressWarnings("deprecation")
public class OneFragment1 extends Fragment {

  private ArrayList<ImageBean> birdList;
  private View mView;
  private  StaggerAdapter mAdapter;
  private SwipeRefreshLayout refreshLayout;
    private ViewPager mViewPaper;
    private List<ImageView> images;
    private List<View> dots;
    private int currentItem;
    //记录上一次点的位置
    private int oldPosition = 0;
    //存放图片的id
    private int[] imageIds = new int[]{
            R.drawable.ic_notifications_black_24dp,
            R.drawable.camera,
            R.drawable.account1,
            R.drawable.envelopeicon,
            R.drawable.key_lock_open_password_unlock_48px_1225503_easyiconnet
    };
    //存放图片的标题
    private String[] titles = new String[]{
            "轮播1",
            "轮播2",
            "轮播3",
            "轮播4",
            "轮播5"
    };
    private TextView title;
  private ScheduledExecutorService scheduledExecutorService;
  private RecyclerView mRvMain;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         mView = inflater.inflate(R.layout.fragment_one, container, false);
        setView();
        setView_spring();
       //setView_recy();
         handlerDownPullUpdate();
        return mView;
    }

    private void  handlerDownPullUpdate()
    {
        refreshLayout.setEnabled(true);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                ImageBean data =new ImageBean();
                data.setName("我是新加载的数据项");
                data.setImageUrl("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2306125995,3157076504&fm=26&gp=0.jpg");
                birdList.add(0,data);
                //更新ui
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                        mAdapter.notifyDataSetChanged();

                    }
                },3000 );
            }
        });
    }
    private void setView(){

             mViewPaper = mView.findViewById(R.id.vp);
             refreshLayout =(SwipeRefreshLayout) mView.findViewById(R.id.swiper);

    //显示的图片
    images = new ArrayList<>();
    for (int imageId : imageIds) {
      ImageView imageView = new ImageView(getActivity());
      imageView.setBackgroundResource(imageId);
      images.add(imageView);
    }
    //显示的小点
    dots = new ArrayList<>();
    dots.add(mView.findViewById(R.id.dot_0));
    dots.add(mView.findViewById(R.id.dot_1));
    dots.add(mView.findViewById(R.id.dot_2));
    dots.add(mView.findViewById(R.id.dot_3));
    dots.add(mView.findViewById(R.id.dot_4));

    title = mView.findViewById(R.id.title);
    title.setText(titles[0]);

    ViewPagerAdapter adapter = new ViewPagerAdapter();
    mViewPaper.setAdapter(adapter);

    mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


      @Override
      public void onPageSelected(int position) {
        title.setText(titles[position]);
        dots.get(position).setBackgroundResource(R.drawable.dot_yes);
        dots.get(oldPosition).setBackgroundResource(R.drawable.dot_no);

        oldPosition = position;
        currentItem = position;
      }

      @Override
      public void onPageScrolled(int arg0, float arg1, int arg2) {

      }

      @Override
      public void onPageScrollStateChanged(int arg0) {

      }
    });
  }

  /*

  设置瀑布流形式的recycleview
  * */
   private void setView_spring()
   {
       mRvMain =(RecyclerView)mView.findViewById(R.id.re_view);
       StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
       mRvMain.setLayoutManager(layoutManager);
       birdList = initData();
       mAdapter = new StaggerAdapter(getActivity(),new LinearAdapter.OnItemClickListener()
       {
           @Override
           public void onClick(int pos) {
               Toast.makeText(getActivity(),"click"+pos,Toast.LENGTH_SHORT).show();
           }
       },birdList);
       mRvMain.setAdapter(mAdapter);

   }


  //设置recycleview形成gridview 的形式
  private void setView_recy(){
      mRvMain =(RecyclerView)mView.findViewById(R.id.re_view);
      mRvMain.setLayoutManager(new GridLayoutManager(getActivity(),3));
      mRvMain.addItemDecoration(new MyDecoration());
      birdList = initData();

      mRvMain.setAdapter(new LinearAdapter(getActivity(),new LinearAdapter.OnItemClickListener()
      {
          @Override
          public void onClick(int pos) {
              Toast.makeText(getActivity(),"click"+pos,Toast.LENGTH_SHORT).show();
          }
      },birdList));


  }

  //为recycleview添加装饰
  class MyDecoration extends ItemDecoration{
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
      super.getItemOffsets(outRect, view, parent, state);
      outRect.set(0,0,4,getResources().getDimensionPixelOffset(R.dimen.dividerHeight));
    }

  }
  /*recycle内容 静态数据 */
  private final String names[] = {
          "Eclair",
          "Froyo",
          "Gingerbread",
          "Honeycomb",
          "Ice Cream Sandwich",
          "Jelly Bean",
          "KitKat",
          "Lollipop",
          "Marshmallow"
  };
  private final String imageUrls[] = {
          "https://5b0988e595225.cdn.sohucs.com/images/20190320/b2c8031c820845d9b43ec3393f6b16c5.jpeg",
         "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1538034970,1848814821&fm=26&gp=0.jpg",
          "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3434003644,2515875709&fm=26&gp=0.jpg",
          "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3719365745,1190196538&fm=26&gp=0.jpg",
          "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2035430989,537432855&fm=26&gp=0.jpg",
          "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3642790688,3617506744&fm=26&gp=0.jpg",
          "https://5b0988e595225.cdn.sohucs.com/images/20190320/b2c8031c820845d9b43ec3393f6b16c5.jpeg",
         "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=370912824,2946133142&fm=26&gp=0.jpg",
          "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1740242775,4159852702&fm=26&gp=0.jpg",
  };
//初始化数据
  private ArrayList<ImageBean> initData() {
    ArrayList<ImageBean> birds = new ArrayList<>();

    for (int i = 0; i < names.length; ++i) {
      ImageBean bird = new ImageBean();
      bird.setImageUrl(imageUrls[i]);
      bird.setName(names[i]);
      birds.add(bird);
    }
    return birds;
  }

  /*/

/*定义的适配器*/
public class ViewPagerAdapter extends PagerAdapter {

  @Override
  public int getCount() {
    return images.size();
  }

  @Override
  public boolean isViewFromObject(View arg0, Object arg1) {
    return arg0 == arg1;
  }

  @Override
  public void destroyItem(ViewGroup view, int position, Object object) {
    /*
TODO Auto-generated method stub
super.destroyItem(container, position, object);
view.removeView(view.getChildAt(position));
view.removeViewAt(position);
*/
    view.removeView(images.get(position));
  }

  @Override
  public Object instantiateItem(ViewGroup view, int position) {
    /* TODO Auto-generated method stub */
    view.addView(images.get(position));
    return images.get(position);
  }

  public void setArg1(Object arg1) {
  }
}

  /**
   * 利用线程池定时执行动画轮播
   */
  @Override
  public void onStart() {
    // TODO Auto-generated method stub
    super.onStart();
    scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutorService.scheduleWithFixedDelay(
            new ViewPageTask(),
            2,
            6,
            TimeUnit.SECONDS);
  }
/**
 * 图片轮播
 *
 */
private class ViewPageTask implements Runnable{

  @Override
  public void run(){
    currentItem = (currentItem+1)%imageIds.length;
    mHandler.sendEmptyMessage(0);

  }
}

  @SuppressLint("HandlerLeak")
  private Handler mHandler;

  {
    mHandler = new Handler() {
      @Override
      public void handleMessage(Message msg) {
        mViewPaper.setCurrentItem(currentItem);
      }
    };
  }

  @Override
  public void onStop() {
    super.onStop();
    if (scheduledExecutorService != null) {
      scheduledExecutorService.shutdown();
      scheduledExecutorService = null;
    }
  }
}
