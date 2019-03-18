package cn.cbapay.ympay.mvp.presenter;

import cn.cbapay.ympay.bean.LoginPasswordVerifyRequestBean;
import cn.cbapay.ympay.bean.LoginPasswordVerifyResponseBean;

/**
 * Created by YangHongfei on 2016/10/27.
 */
public interface VerifyLoginPasswordPresenter {
    void verifyPassword(LoginPasswordVerifyRequestBean bean);

    void onDestroy();

    interface VerifyPasswordView {
        void onVerifyPasswordSuccess(LoginPasswordVerifyResponseBean bean);

        void onVerifyPasswordFail(String code, String msg);
    }
}
