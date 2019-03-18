package cn.cbapay.ympay.mvp.presenter.impl;

import cn.cbapay.ympay.bean.LoginPasswordVerifyRequestBean;
import cn.cbapay.ympay.bean.LoginPasswordVerifyResponseBean;
import cn.cbapay.ympay.mvp.model.VerifyLoginPasswordModel;
import cn.cbapay.ympay.mvp.model.impl.VerifyLoginPasswordModelImpl;
import cn.cbapay.ympay.mvp.presenter.VerifyLoginPasswordPresenter;

/**
 * Created by YangHongfei on 2016/10/27.
 */
public class VerifyLoginPasswordPresenterImpl implements VerifyLoginPasswordPresenter, VerifyLoginPasswordModel.VerifyPasswordListener {
    private static final String TAG = VerifyLoginPasswordPresenterImpl.class.getSimpleName();
    private VerifyLoginPasswordModel verifyLoginPasswordModel;
    private VerifyLoginPasswordPresenter.VerifyPasswordView verifyPasswordView;

    public VerifyLoginPasswordPresenterImpl(VerifyLoginPasswordPresenter.VerifyPasswordView verifyPasswordView) {
        this.verifyPasswordView = verifyPasswordView;
        verifyLoginPasswordModel = new VerifyLoginPasswordModelImpl(this);
    }


    @Override
    public void verifyPassword(LoginPasswordVerifyRequestBean bean) {
        verifyLoginPasswordModel.verifyPassword(bean);
    }

    @Override
    public void onDestroy() {
        verifyLoginPasswordModel.onDestroy();
    }

    @Override
    public void onVerifyPasswordSuccess(LoginPasswordVerifyResponseBean bean) {
        verifyPasswordView.onVerifyPasswordSuccess(bean);
    }

    @Override
    public void onVerifyPasswordFailed(String code, String msg) {
        verifyPasswordView.onVerifyPasswordFail(code, msg);
    }
}
