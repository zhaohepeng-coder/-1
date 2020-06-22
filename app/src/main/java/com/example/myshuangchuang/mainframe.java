package com.example.myshuangchuang;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class mainframe  extends AppCompatActivity{
    BottomNavigationView bnView;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainframe);

        bnView = findViewById(R.id.nav_view);
        viewPager = findViewById(R.id.viewpager);


        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new OneFragment1());
        fragments.add(new TwoFragment());
        fragments.add(new Fragment());
        // fragments.add(new FourFragment());

        FragmentAdapter adapter = new FragmentAdapter(fragments, getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        //BottomNavigationView 点击事件监听
        bnView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int menuId = menuItem.getItemId();

                // 跳转指定页面：Fragment
                switch (menuId) {
                    case R.id.navigation_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.navigation_dashboard:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.navigation_notifications:
                        viewPager.setCurrentItem(2);
                        break;
                    //case R.id.tab_four:
                    //   viewPager.setCurrentItem(3);
                    //    break;
                }
                return false;
            }
        });

        // ViewPager 滑动事件监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                //将滑动到的页面对应的 menu 设置为选中状态
                bnView.getMenu().getItem(i).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }
}
