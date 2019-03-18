package cn.cbapay.ympay.mvp.ui.fragment;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import android.widget.RadioGroup;

import java.util.ArrayList;


import cn.cbapay.ympay.R;
import cn.cbapay.ympay.mvp.ui.activity.pager.FinancPager;
import cn.cbapay.ympay.mvp.ui.activity.pager.FindPager;
import cn.cbapay.ympay.mvp.ui.activity.pager.HomePager;
import cn.cbapay.ympay.mvp.ui.activity.pager.MinePager;
import cn.cbapay.ympay.mvp.ui.base.BaseFragment;
import cn.cbapay.ympay.mvp.ui.base.BasePager;
import cn.cbapay.ympay.view.NoScrollViewPager;

/**
 * Created by Administrator on 2016/8/31.
 */
public class ContentFragement extends BaseFragment {

    private ArrayList<BasePager> mPagers;
    private RadioGroup rgGroup;
    private NoScrollViewPager vpContent;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.frament_main_menu, null);
        rgGroup = (RadioGroup) view.findViewById(R.id.rg_group);
        vpContent = (NoScrollViewPager) view.findViewById(R.id.vp_content);
        return view;
    }

    @Override
    public void initData() {


        //添加四个标签页
        mPagers = new ArrayList<BasePager>();
        mPagers.add(new HomePager(mActivity));
        mPagers.add(new FinancPager(mActivity));
        mPagers.add(new FindPager(mActivity));
        mPagers.add(new MinePager(mActivity));


        vpContent.setAdapter(new MyAdapter());

        vpContent.setCurrentItem(3,false);
        //底边烂切换的监听
        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.home:
                        vpContent.setCurrentItem(0,false);
                        break;
                    case R.id.financ:
                        vpContent.setCurrentItem(1,false);
                        break;
                    case R.id.find:
                        vpContent.setCurrentItem(2,false);
                        break;
                    case R.id.mine:
                        vpContent.setCurrentItem(3,false);
                        break;
                }
            }
        });
        //页面切换的监听
        vpContent.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                BasePager pager = mPagers.get(position);
                //初始化数据
                pager.initData();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mPagers.get(3).initData();//手动加载第3页数据
    }
    private class MyAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return mPagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager basePager = mPagers.get(position);
            View mRootview = basePager.mRootview;
            container.addView(mRootview);
            // basePager.initData();//初始化数据， 不要再这初始化，viewpager绘默认加载下一个界面 浪费资源
            return mRootview;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


}
