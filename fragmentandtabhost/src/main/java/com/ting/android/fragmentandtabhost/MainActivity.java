package com.ting.android.fragmentandtabhost;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    FragmentTabHost mFragmentTabHost;   //盛装那个切换卡的容器
    String[] titles = {"中国", "美国", "小日本", "美国佬"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragmentTabHost();
    }

    private void initFragmentTabHost() {
        //初始化容器
        mFragmentTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        //初始化选项卡
        for (int i = 0; i < titles.length; i++) {
            // newTabSpec后面的内容是给当前的切换卡一个tag，setIndicator才是设置那个切换卡内容的地方
            TabHost.TabSpec tabSpec = mFragmentTabHost.newTabSpec(titles[i] + i).setIndicator(titles[i]);
            Bundle bundle = new Bundle();
            bundle.putString("content", titles[i]);
            /**
             * 第一个参数:切换卡
             * 第二个参数:内容的fragment的class文件
             * 第三个参数:就是那个Fragment使用的时候需要传递进去的参数
             *
             */
            mFragmentTabHost.addTab(tabSpec,ContentFragment.class,bundle);
        }
    }

    private void initView() {
        mFragmentTabHost = findViewById(R.id.mFragmentTabHost);
    }
}
