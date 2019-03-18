package cn.cbapay.ympay.mvp.model;

import cn.cbapay.ympay.bean.LoginRequestBean;
import cn.cbapay.ympay.bean.LoginResultBean;

/**
 * Created by Administrator on 2016/10/24.
 */
public interface LoginModel {

    void login(LoginRequestBean bean);

    interface LoginListener {
        void loginSuccess(LoginResultBean bean);

        void loginError(String s);

        void showMsg(String msg);
    }
}
