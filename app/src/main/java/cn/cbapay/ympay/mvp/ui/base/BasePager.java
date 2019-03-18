package cn.cbapay.ympay.mvp.ui.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


import cn.cbapay.ympay.R;


/**
 * Created by gaowei on 2016/4/6.
 */
public class BasePager {

    public Activity mActivity;
    public ArrayList<BasePager> mPagers;

    public View mRootview;

    public FrameLayout fl_contaner;


    public BasePager(Activity activity) {
        mActivity = activity;
        mRootview = initView();//当前布局对象

    }


    /**
     * 初始化布局
     *
     * @return
     */
    public View initView() {
        View view = View.inflate(mActivity, R.layout.base_pager_layout, null);
        fl_contaner = (FrameLayout) view.findViewById(R.id.fl_base_pager);
        return view;
    }

    /**
     * 初始化数据
     */
    public void initData() {

    }

}
