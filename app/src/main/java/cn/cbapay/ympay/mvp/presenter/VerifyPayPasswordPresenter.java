package cn.cbapay.ympay.mvp.presenter;

import cn.cbapay.ympay.bean.PayPasswordVerifyRequestBean;
import cn.cbapay.ympay.bean.PayPasswordVerifyResponseBean;

/**
 * Created by YangHongfei on 2016/10/31.
 */

public interface VerifyPayPasswordPresenter {
    void verifyPassword(PayPasswordVerifyRequestBean bean);

    void onDestroy();

    interface VerifyPasswordView {
        void onVerifyPasswordSuccess(PayPasswordVerifyResponseBean bean);

        void onVerifyPasswordFail(String msg);
    }
}
