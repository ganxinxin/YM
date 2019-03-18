package cn.cbapay.ympay.mvp.model;

import cn.cbapay.ympay.bean.BankVerifyRequestBean;

/**
 *
 * Created by icewater on 2016/10/26.
 */

public interface SendBankCodeModel {
    void sendCode(BankVerifyRequestBean bean);

    void onDestroy();

    interface SendCodeListener {
        void onSendCodeSuccess();

        void onSendCodeFailed(String code, String msg);
    }
}
