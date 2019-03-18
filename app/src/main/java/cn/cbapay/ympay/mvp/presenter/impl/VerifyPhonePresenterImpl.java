package cn.cbapay.ympay.mvp.presenter.impl;

import cn.cbapay.ympay.bean.NewPhoneVerifyRequestBean;
import cn.cbapay.ympay.mvp.model.VerifyNewPhoneCodeModel;
import cn.cbapay.ympay.mvp.model.impl.VerifyNewPhoneCode2ModelImpl;
import cn.cbapay.ympay.mvp.presenter.VerifyPhonePresenter;

/**
 * Created by YangHongfei on 2016/10/27.
 */
public class VerifyPhonePresenterImpl implements VerifyPhonePresenter, VerifyNewPhoneCodeModel.VerifyCodeListener {
    private static final String TAG = VerifyPhonePresenterImpl.class.getSimpleName();
    private VerifyNewPhoneCodeModel verifyNewPhoneCodeModel;
    private VerifyPhonePresenter.VerifyPhoneView verifyPhoneView;

    public VerifyPhonePresenterImpl(VerifyPhoneView verifyPhoneView) {
        this.verifyPhoneView = verifyPhoneView;
        verifyNewPhoneCodeModel = new VerifyNewPhoneCode2ModelImpl(this);
    }

    @Override
    public void verifyPhone(NewPhoneVerifyRequestBean bean) {
        verifyNewPhoneCodeModel.verifyCode(bean);
    }

    @Override
    public void onDestroy() {
        verifyNewPhoneCodeModel.onDestroy();
    }

    @Override
    public void onVerifyCodeSuccess() {
        verifyPhoneView.verifyPhoneSuccess();
    }

    @Override
    public void onVerifyCodeFailed(String msg) {
        verifyPhoneView.verifyPhoneFail(msg);
    }
}
