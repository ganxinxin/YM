package cn.cbapay.ympay.mvp.model;

import cn.cbapay.ympay.bean.LoginPasswordVerifyRequestBean;
import cn.cbapay.ympay.bean.LoginPasswordVerifyResponseBean;

/**
 *
 * Created by icewater on 2016/10/26.
 */

public interface VerifyLoginPasswordModel {
    void verifyPassword(LoginPasswordVerifyRequestBean bean);

    void onDestroy();

    interface VerifyPasswordListener {
        void onVerifyPasswordSuccess(LoginPasswordVerifyResponseBean bean);

        void onVerifyPasswordFailed(String code, String msg);
    }
}
