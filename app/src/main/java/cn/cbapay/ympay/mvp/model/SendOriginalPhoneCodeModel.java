package cn.cbapay.ympay.mvp.model;

import cn.cbapay.ympay.bean.RequestBean;

/**
 *
 * Created by icewater on 2016/10/26.
 */

public interface SendOriginalPhoneCodeModel {
    void sendCode(RequestBean bean);

    void onDestroy();

    interface SendCodeListener {
        void onSendCodeSuccess();

        void onSendCodeFailed(String code, String msg);
    }
}
