package cn.cbapay.ympay.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import cn.cbapay.ympay.bean.AreaBean;
import cn.cbapay.ympay.mvp.ui.activity.LoginActivity;
import cn.cbapay.ympay.utils.GlobalData;
import cn.cbapay.ympay.utils.ShareUtil;

/**
 * Created by xuetao on 16/3/2.
 */
public class MyApplication extends Application {

    private static Context mContext;
    private AreaBean areaBean;
    public static final String RANDOM_CODE = "RandomCode";
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        instance = this;
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public void setAreaBean(AreaBean bean) {
        areaBean = bean;
    }

    public AreaBean getAreaBean() {
        return areaBean;
    }

    public static Context getContext() {
        return mContext;
    }

    /**
     * token 失效重新登录
     */
    public static void StartLogin(){
        GlobalData.setArpAcProfile(null);
        GlobalData.setmPayPwdStatus("");
        GlobalData.setStpcusinf(null);
        GlobalData.setStpusrinf(null);
        ShareUtil.setValue("token","",mContext);
        AppManager.getAppManager().finishAllActivities();
        Intent intent = new Intent();
        intent.setClass(mContext, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        mContext.startActivity(intent);
    }
}
