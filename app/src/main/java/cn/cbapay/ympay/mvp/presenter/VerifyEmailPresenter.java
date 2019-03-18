package cn.cbapay.ympay.mvp.presenter;

import cn.cbapay.ympay.bean.EmailVerifyRequestBean;

/**
 * Created by YangHongfei on 2016/10/26.
 */
public interface VerifyEmailPresenter {
    void verifyEmail(EmailVerifyRequestBean bean);
    void onDestroy();

    interface VerifyEmailView {
        void onVerifyEmailSuccess();

        void onVerifyEmailFail(String msg);
    }
}
