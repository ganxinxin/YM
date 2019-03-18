package cn.cbapay.ympay.mvp.model;

import cn.cbapay.ympay.bean.ChangePayPswIdBean;

/**
 * Created by Administrator on 2016/10/28.
 */
public interface ChangePayCheckIdModel {

    void checkIdNumber(ChangePayPswIdBean bean);

    interface ChangePayCheckIdModelListener{
        void checkSuccess(String randomCode);
        void chenckError();
        void showMsg(String msg);

    }
}
