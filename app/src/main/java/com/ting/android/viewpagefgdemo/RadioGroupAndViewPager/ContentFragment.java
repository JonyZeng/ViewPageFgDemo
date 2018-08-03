package com.ting.android.viewpagefgdemo.RadioGroupAndViewPager;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ting.android.viewpagefgdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends Fragment {


    private View view;
    private TextView mTextView;
    private String content;
    public ContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_content, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTextView = view.findViewById(R.id.mTextView);
        content = getArguments().getString("content");
        mTextView.setText(content);
    }

    /**
     * 判断当前的页面是可见
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e("--------------","我执行了......");
        if(isVisibleToUser){   //说明用户可见
            Log.e(content,"页面是可见的....");
        }else{   //用户不可见
            Log.e(content,"页面是不可见的....");
        }
    }
}
