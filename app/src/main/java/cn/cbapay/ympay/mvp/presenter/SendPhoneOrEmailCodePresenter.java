package cn.cbapay.ympay.mvp.presenter;

import cn.cbapay.ympay.bean.GetNewPhoneVerifyCodeRequestBean;
import cn.cbapay.ympay.bean.GetNewPhoneVerifyCodeResponseBean;

/**
 * Created by YangHongfei on 2016/10/26.
 */
public interface SendPhoneOrEmailCodePresenter {
    void sendPhoneOrEmailCode(GetNewPhoneVerifyCodeRequestBean bean);

    void onDestroy();

    interface SendPhoneOrEmailView {
        void sendCodeSuccess(GetNewPhoneVerifyCodeResponseBean bean);

        void sendCodeFail(String msg);
    }
}
