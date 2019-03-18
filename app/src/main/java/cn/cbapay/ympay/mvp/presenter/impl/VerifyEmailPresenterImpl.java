package cn.cbapay.ympay.mvp.presenter.impl;

import cn.cbapay.ympay.bean.EmailVerifyRequestBean;
import cn.cbapay.ympay.mvp.model.VerifyEmailModel;
import cn.cbapay.ympay.mvp.model.impl.VerifyEmailModelImpl;
import cn.cbapay.ympay.mvp.presenter.VerifyEmailPresenter;

/**
 * Created by YangHongfei on 2016/10/26.
 */
public class VerifyEmailPresenterImpl implements VerifyEmailPresenter, VerifyEmailModel.VerifyEmailListener {
    private static final String TAG = VerifyEmailPresenterImpl.class.getSimpleName();
    private VerifyEmailModel verifyEmailModel;
    private VerifyEmailView verifyEmailView;

    public VerifyEmailPresenterImpl(VerifyEmailView verifyEmailView) {
        this.verifyEmailView = verifyEmailView;
        verifyEmailModel = new VerifyEmailModelImpl(this);
    }

    @Override
    public void verifyEmail(EmailVerifyRequestBean bean) {
        verifyEmailModel.verifyCode(bean);
    }

    @Override
    public void onDestroy() {
        verifyEmailModel.onDestroy();
    }

    @Override
    public void OnVerifyEmailSuccess() {
        verifyEmailView.onVerifyEmailSuccess();
    }

    @Override
    public void OnVerifyEmailFail(String msg) {
        verifyEmailView.onVerifyEmailFail(msg);
    }
}
