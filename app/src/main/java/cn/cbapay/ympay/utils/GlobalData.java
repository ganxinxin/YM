package cn.cbapay.ympay.utils;

import android.app.Application;

import cn.cbapay.ympay.bean.ArpAcProfileBean;
import cn.cbapay.ympay.bean.StpcusinfBean;
import cn.cbapay.ympay.bean.StpusrinfBean;

/**
 * Created by Administrator on 2016/10/26.
 */
public class GlobalData  {

    private static ArpAcProfileBean mArpAcProfile; // 账户主档案表数据
    private static StpusrinfBean mStpusrinf; // 用户表数据
    private static StpcusinfBean mStpcusinf; // 实名表数据

    private static String mPayPwdStatus;//设置支付密码的状态

    public static ArpAcProfileBean getArpAcProfile() {
        return mArpAcProfile;
    }

    public static void setArpAcProfile(ArpAcProfileBean arpAcProfile) {
        mArpAcProfile = arpAcProfile;
    }

    public static StpusrinfBean getStpusrinf() {
        return mStpusrinf;
    }

    public static void setStpusrinf(StpusrinfBean stpusrinf) {
        mStpusrinf = stpusrinf;
    }

    public static StpcusinfBean getStpcusinf() {
        return mStpcusinf;
    }

    public static void setStpcusinf(StpcusinfBean stpcusinf) {
        mStpcusinf = stpcusinf;
    }

    public static String getmPayPwdStatus() {
        return mPayPwdStatus;
    }

    public static void setmPayPwdStatus(String payPwdStatus) {
        mPayPwdStatus = payPwdStatus;
    }

}
