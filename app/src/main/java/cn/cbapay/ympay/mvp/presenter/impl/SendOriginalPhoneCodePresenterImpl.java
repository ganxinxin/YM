package cn.cbapay.ympay.mvp.presenter.impl;

import cn.cbapay.ympay.bean.RequestBean;
import cn.cbapay.ympay.mvp.model.SendOriginalPhoneCodeModel;
import cn.cbapay.ympay.mvp.model.impl.SendOriginalPhoneCodeModelImpl;
import cn.cbapay.ympay.mvp.presenter.SendOriginalPhoneCodePresenter;

/**
 * Created by YangHongfei on 2016/10/31.
 */

public class SendOriginalPhoneCodePresenterImpl implements SendOriginalPhoneCodePresenter, SendOriginalPhoneCodeModel.SendCodeListener {
    private static final String TAG = SendOriginalPhoneCodePresenterImpl.class.getSimpleName();
    private SendOriginalPhoneCodeModel sendOriginalPhoneCodeModel;
    private SendOriginalPhoneView sendOriginalPhoneView;

    public SendOriginalPhoneCodePresenterImpl(SendOriginalPhoneCodePresenter.SendOriginalPhoneView sendOriginalPhoneView) {
        this.sendOriginalPhoneView = sendOriginalPhoneView;
        sendOriginalPhoneCodeModel = new SendOriginalPhoneCodeModelImpl(this);
    }

    @Override
    public void sendMCode(RequestBean bean) {
        sendOriginalPhoneCodeModel.sendCode(bean);
    }

    @Override
    public void onDestroy() {
        sendOriginalPhoneCodeModel.onDestroy();
    }

    @Override
    public void onSendCodeSuccess() {
        sendOriginalPhoneView.onSendOriginalPhoneSuccess();
    }

    @Override
    public void onSendCodeFailed(String code, String msg) {
        sendOriginalPhoneView.onSendOriginalPhoneFail(msg);
    }
}
