package cn.cbapay.ympay.mvp.presenter;

import cn.cbapay.ympay.bean.NewPhoneVerifyRequestBean;

/**
 * Created by YangHongfei on 2016/10/27.
 */
public interface VerifyPhonePresenter {
    void verifyPhone(NewPhoneVerifyRequestBean bean);

    void onDestroy();

    interface VerifyPhoneView {
        void verifyPhoneSuccess();

        void verifyPhoneFail(String msg);
    }

}
