package cn.cbapay.ympay.mvp.presenter;

import cn.cbapay.ympay.bean.OriginalPhoneVerifyRequestBean;
import cn.cbapay.ympay.bean.OriginalPhoneVerifyResponseBean;

/**
 * Created by YangHongfei on 2016/10/31.
 */

public interface VerifyOriginalPhoneCodePresenter {
    void verifyCode(OriginalPhoneVerifyRequestBean bean);

    void onDestroy();

    interface VerifyOriginalPhoneCodeView {
        void onVerifyOriginalPhoneCodeSuccess(OriginalPhoneVerifyResponseBean bean);

        void onVerifyOriginalPhoneCodeFail(String msg);
    }
}
