package cn.cbapay.ympay.mvp.presenter;

import cn.cbapay.ympay.bean.RequestBean;

/**
 * Created by YangHongfei on 2016/10/31.
 */

public interface SendOriginalPhoneCodePresenter {
    void sendMCode(RequestBean bean);

    void onDestroy();

    interface SendOriginalPhoneView {
        void onSendOriginalPhoneSuccess();

        void onSendOriginalPhoneFail(String msg);
    }
}
