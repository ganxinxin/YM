package cn.cbapay.ympay.mvp.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import cn.cbapay.ympay.R;
import cn.cbapay.ympay.mvp.ui.base.BaseActivity;
import cn.cbapay.ympay.mvp.ui.fragment.ContentFragement;
import cn.cbapay.ympay.utils.GlobalData;

public class MainActivity extends BaseActivity {
    private static final String TAG_MAIN = "TAG_MAIN";

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        initFragment();
    }

    /**
     * 初始化 frament
     */
    private void initFragment(){
        //得到管理器
        FragmentManager fm = getSupportFragmentManager();
        //开始事务
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_main, new ContentFragement(), TAG_MAIN);
        transaction.commit();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        //得到管理器
        FragmentManager fm = getSupportFragmentManager();
        //开始事务
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_main, new ContentFragement(), TAG_MAIN);
        transaction.commitAllowingStateLoss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //退出应用清空数据
        GlobalData.setArpAcProfile(null);
        GlobalData.setStpusrinf(null);
        GlobalData.setmPayPwdStatus("");
        GlobalData.setStpcusinf(null);
    }
}
