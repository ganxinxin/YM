package cn.cbapay.ympay.mvp.model;

import cn.cbapay.ympay.bean.NewPhoneVerifyRequestBean;

/**
 *
 * Created by icewater on 2016/10/26.
 */

public interface VerifyNewPhoneCodeModel {
    void verifyCode(NewPhoneVerifyRequestBean bean);

    void onDestroy();

    interface VerifyCodeListener {
        void onVerifyCodeSuccess();

        void onVerifyCodeFailed(String msg);
    }
}
