package com.ting.android.viewpagefgdemo.RadioGroupAndViewPager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ting.android.viewpagefgdemo.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Fragment> fragments = new ArrayList<>();   //fragment的初始化
    RadioGroup mRadioGroup;
    ViewPager mViewPager;
    RadioButton mRadiBtnHome;
    RadioButton mRadiBtnInfor;
    RadioButton mRadiBtnContacts;
    RadioButton mRadiBtnDynamic;
    String[] titles = {"首页", "消息", "动态", "联系人"};
    List<RadioButton> radioBtns = new ArrayList<>();    //把咋们的那个RadioButton给放进来

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        mRadioGroup.setOnCheckedChangeListener(new OnCheckedListener());
        mViewPager.addOnPageChangeListener(new OnPageListener());
        setAdapter();
    }

    private void setAdapter() {
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(myAdapter);
        mViewPager.setCurrentItem(1);
        mRadiBtnInfor.setChecked(true);
    }

    private void initData() {
        for (int i = 0; i < 4; i++) {   //Fragment内容单一，可以复用才这样写。一般业务中，不会有这么单一的业务需求，所以需要创建多个Fragment
            ContentFragment contentFragment = new ContentFragment();
            Bundle bundle = new Bundle();
            bundle.putString("content", titles[i]);
            contentFragment.setArguments(bundle);
            fragments.add(contentFragment);
        }
    }

    /**
     * 通过控件id找到控件，并将radiobtn添加进radiogroup
     */
    private void initView() {
        mRadioGroup = findViewById(R.id.mRadioGroup);
        mViewPager = findViewById(R.id.mViewPager);
        mRadiBtnHome = findViewById(R.id.mRadioBtnHome);
        mRadiBtnInfor = findViewById(R.id.mRadioBtnInfor);
        mRadiBtnContacts = findViewById(R.id.mRadioBtnContacts);
        mRadiBtnDynamic = findViewById(R.id.mRadioBtnDynamic);
        //按照顺序放进
        radioBtns.add(mRadiBtnHome);
        radioBtns.add(mRadiBtnInfor);
        radioBtns.add(mRadiBtnDynamic);
        radioBtns.add(mRadiBtnContacts);
    }


    private class MyAdapter extends FragmentPagerAdapter {
        MyAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    /**
     * RadioGroup的点击事件，显示的Fragment随着button改变
     */
    private class OnCheckedListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (group.getCheckedRadioButtonId()){
                case R.id.mRadioBtnHome:
                    mViewPager.setCurrentItem(0);
                    break;
                case R.id.mRadioBtnInfor:
                    mViewPager.setCurrentItem(1);
                    break;
                case R.id.mRadioBtnDynamic:
                    mViewPager.setCurrentItem(2);
                    break;
                case R.id.mRadioBtnContacts:
                    mViewPager.setCurrentItem(3);
                    break;
            }
        }
    }

    private class OnPageListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }
        //就是我们在改变页面的时候，我们对radiobutton进行改变.
        public void onPageSelected(int position) {//当页面被选中的时候
            Log.e("------------------","发生改变的顺序是:"+position);
            radioBtns.get(position).setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
