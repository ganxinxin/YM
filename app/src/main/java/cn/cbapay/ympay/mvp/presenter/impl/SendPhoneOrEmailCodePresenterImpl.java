package cn.cbapay.ympay.mvp.presenter.impl;

import cn.cbapay.ympay.bean.GetNewPhoneVerifyCodeRequestBean;
import cn.cbapay.ympay.bean.GetNewPhoneVerifyCodeResponseBean;
import cn.cbapay.ympay.mvp.model.SendPhoneEmailCodeModel;
import cn.cbapay.ympay.mvp.model.impl.SendPhoneEmailCodeModelImpl;
import cn.cbapay.ympay.mvp.presenter.SendPhoneOrEmailCodePresenter;

/**
 * Created by YangHongfei on 2016/10/26.
 */
public class SendPhoneOrEmailCodePresenterImpl implements SendPhoneOrEmailCodePresenter, SendPhoneEmailCodeModel.SendCodeListener {
    private static final String TAG = SendPhoneOrEmailCodePresenterImpl.class.getSimpleName();
    private SendPhoneEmailCodeModel sendPhoneEmailCodeModel;
    private SendPhoneOrEmailView sendPhoneOrEmailView;

    public SendPhoneOrEmailCodePresenterImpl(SendPhoneOrEmailView sendPhoneOrEmailView) {
        this.sendPhoneOrEmailView = sendPhoneOrEmailView;
        sendPhoneEmailCodeModel = new SendPhoneEmailCodeModelImpl(this);
    }

    @Override
    public void sendPhoneOrEmailCode(GetNewPhoneVerifyCodeRequestBean bean) {
        sendPhoneEmailCodeModel.sendCode(bean);
    }

    @Override
    public void onDestroy() {
        sendPhoneEmailCodeModel.onDestroy();
    }

    @Override
    public void onSendCodeSuccess(GetNewPhoneVerifyCodeResponseBean bean) {
        sendPhoneOrEmailView.sendCodeSuccess(bean);
    }

    @Override
    public void onSendCodeFailed(String msg) {
        sendPhoneOrEmailView.sendCodeFail(msg);
    }
}
