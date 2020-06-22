package com.example.myshuangchuang;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myshuangchuang.adapter.LinearAdapter;

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

  private View mView;
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
        setView_recy();
        return mView;
    }

  private void setView(){
    mViewPaper = mView.findViewById(R.id.vp);

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
  private void setView_recy(){
      mRvMain =(RecyclerView)mView.findViewById(R.id.re_view);
      mRvMain.setLayoutManager(new LinearLayoutManager(getActivity()));
      mRvMain.setAdapter(new LinearAdapter(getActivity()));


  }

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
