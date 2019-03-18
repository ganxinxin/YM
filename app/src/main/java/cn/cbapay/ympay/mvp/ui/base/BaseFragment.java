package cn.cbapay.ympay.mvp.ui.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class BaseFragment extends Fragment {

   /* protected BaseActivity mActivity;

    //获取布局文件ID
    protected abstract int getLayoutId();

    protected abstract void initViews(View view);
    protected abstract void initData(Bundle savedInstanceState);

    //获取宿主Activity
    protected BaseActivity getHoldingActivity() {
        return mActivity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (BaseActivity) context;
    }

//    //添加fragment
//    protected void addFragment(BaseFragment fragment) {
//        if (null != fragment) {
//            getHoldingActivity().addFragment(fragment);
//        }
//    }
//
//    //移除fragment
//    protected void removeFragment() {
//        getHoldingActivity().removeFragment();
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        initViews(view);
        initData(savedInstanceState);
        return view;
    }
*/
   public Activity mActivity;
    //创建

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }
    //初始化布局

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initView();
        return view;
    }

    //初始化数据

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }
    //初始化布局 必须由子类实现

    public abstract View initView();
    //初始化数据

    public abstract void initData();
}