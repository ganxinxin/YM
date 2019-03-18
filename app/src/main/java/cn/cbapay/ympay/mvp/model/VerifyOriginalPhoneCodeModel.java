package cn.cbapay.ympay.mvp.model;

import cn.cbapay.ympay.bean.OriginalPhoneVerifyRequestBean;
import cn.cbapay.ympay.bean.OriginalPhoneVerifyResponseBean;

/**
 *
 * Created by icewater on 2016/10/26.
 */

public interface VerifyOriginalPhoneCodeModel {
    void verifyCode(OriginalPhoneVerifyRequestBean bean);

    void onDestroy();

    interface VerifyCodeListener {
        void onVerifyCodeSuccess(OriginalPhoneVerifyResponseBean bean);

        void onVerifyCodeFailed(String msg);
    }
}
