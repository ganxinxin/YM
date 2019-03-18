package cn.cbapay.ympay.mvp.model;

import cn.cbapay.ympay.bean.ChangeLoginPassWordBean;

/**
 * Created by Administrator on 2016/10/27.
 */
public interface ChangeLoginSenPassWordModel {

    void requsetPassword(ChangeLoginPassWordBean bean);

    interface ChangeLoginSenPassWordListener {

        void changeSuccess();

        void chanangeError();

        void showMsg(String msg);

    }
}
