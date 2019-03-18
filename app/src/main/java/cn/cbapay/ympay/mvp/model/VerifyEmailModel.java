package cn.cbapay.ympay.mvp.model;

import cn.cbapay.ympay.bean.EmailVerifyRequestBean;

/**
 * Created by YangHongfei on 2016/10/26.
 */
public interface VerifyEmailModel {
    void verifyCode(EmailVerifyRequestBean bean);

    void onDestroy();

    interface VerifyEmailListener {
        void OnVerifyEmailSuccess();

        void OnVerifyEmailFail(String msg);
    }
}
