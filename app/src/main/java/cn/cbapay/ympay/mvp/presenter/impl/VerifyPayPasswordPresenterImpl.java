package cn.cbapay.ympay.mvp.presenter.impl;

import cn.cbapay.ympay.bean.PayPasswordVerifyRequestBean;
import cn.cbapay.ympay.bean.PayPasswordVerifyResponseBean;
import cn.cbapay.ympay.mvp.model.VerifyPayPasswordModel;
import cn.cbapay.ympay.mvp.model.impl.VerifyPayPasswordModelImpl;
import cn.cbapay.ympay.mvp.presenter.VerifyPayPasswordPresenter;

/**
 * Created by YangHongfei on 2016/10/31.
 */

public class VerifyPayPasswordPresenterImpl implements VerifyPayPasswordPresenter, VerifyPayPasswordModel.VerifyPasswordListener {
    private static final String TAG = VerifyPayPasswordPresenterImpl.class.getSimpleName();
    private VerifyPayPasswordModel verifyPayPasswordModel;
    private VerifyPasswordView verifyPasswordView;

    public VerifyPayPasswordPresenterImpl(VerifyPayPasswordPresenter.VerifyPasswordView verifyPasswordView) {
        this.verifyPasswordView = verifyPasswordView;
        verifyPayPasswordModel = new VerifyPayPasswordModelImpl(this);

    }

    @Override
    public void verifyPassword(PayPasswordVerifyRequestBean bean) {
        verifyPayPasswordModel.verifyPassword(bean);
    }

    @Override
    public void onDestroy() {
        verifyPayPasswordModel.onDestroy();
    }

    @Override
    public void onVerifyPasswordSuccess(PayPasswordVerifyResponseBean bean) {
        verifyPasswordView.onVerifyPasswordSuccess(bean);
    }

    @Override
    public void onVerifyPasswordFailed(String code, String msg) {
        verifyPasswordView.onVerifyPasswordFail(msg);
    }
}
