package cn.cbapay.ympay.mvp.model;

import cn.cbapay.ympay.bean.GetNewPhoneVerifyCodeRequestBean;
import cn.cbapay.ympay.bean.GetNewPhoneVerifyCodeResponseBean;

/**
 * Created by icewater on 2016/10/26.
 */

public interface SendPhoneEmailCodeModel {
    void sendCode(GetNewPhoneVerifyCodeRequestBean bean);

    void onDestroy();

    interface SendCodeListener {
        void onSendCodeSuccess(GetNewPhoneVerifyCodeResponseBean bean);

        void onSendCodeFailed(String msg);
    }
}
