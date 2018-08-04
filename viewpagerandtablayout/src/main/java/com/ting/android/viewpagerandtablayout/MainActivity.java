package com.ting.android.viewpagerandtablayout;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TabLayout mTabLayout;   //布局：容器   装的是切换卡
    ViewPager mViewPager;   //内容的显示区域
    List<Fragment> fragments = new ArrayList<>();    //初始化那个Fragment的容器
    String[] titles = {"美国", "小日本", "德国", "新加坡", "美国", "小日本", "德国", "新加坡"};  //标题的数组用于在那个TabLayout里面去使用
    int screenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = (TabLayout) findViewById(R.id.mTabLayout);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        //DisplayMetrics displayMetrics = new DisplayMetrics();
        //getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        //screenWidth=displayMetrics.widthPixels;
        initData();
        setAdapter();
        viewPagerWithTabLayout();

    }

    /**
     * 将那个TabLayout和ViewPager建立一个关系
     */
    private void viewPagerWithTabLayout() {
        mTabLayout.setupWithViewPager(mViewPager);//这里一句话就把他们建立连接了
    }

    /**
     * 设置那个适配器
     */
    private void setAdapter() {
        MyAdapter mMyAdapter = new MyAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mMyAdapter);
    }

    private class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        /*
         * 用来返回那个页面当前的标题是什么？方便那个Tablayout去使用
         * */
        @Override
        public CharSequence getPageTitle(int position) {//这个方法不会自动实现，要我们自己去写出来
            return titles[position];//得到是标签的内容，就是让我们的标题显示内容
        }
    }

    /**
     * 初始化数据
     */
    private void initData() {
        for (int i = 0; i < titles.length; i++) {
            ContentFragment mContentFragment = new ContentFragment();
            Bundle bundle = new Bundle();
            bundle.putString("content", titles[i]);
            mContentFragment.setArguments(bundle);//利用这个方法，我们进行消息的传送
            fragments.add(mContentFragment);
        }
    }
}
