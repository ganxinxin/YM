package cn.cbapay.ympay.mvp.presenter.impl;

import cn.cbapay.ympay.bean.OriginalPhoneVerifyRequestBean;
import cn.cbapay.ympay.bean.OriginalPhoneVerifyResponseBean;
import cn.cbapay.ympay.mvp.model.VerifyOriginalPhoneCodeModel;
import cn.cbapay.ympay.mvp.model.impl.VerifyOriginalPhoneCodeModelImpl;
import cn.cbapay.ympay.mvp.presenter.VerifyOriginalPhoneCodePresenter;

/**
 * Created by YangHongfei on 2016/10/31.
 */

public class VerifyOriginalPhoneCodePresenterImpl implements VerifyOriginalPhoneCodePresenter, VerifyOriginalPhoneCodeModel.VerifyCodeListener {
    private static final String TAG = VerifyOriginalPhoneCodePresenterImpl.class.getSimpleName();
    private VerifyOriginalPhoneCodeModel verifyOriginalPhoneCodeModel;
    private VerifyOriginalPhoneCodeView verifyOriginalPhoneCodeView;

    public VerifyOriginalPhoneCodePresenterImpl(VerifyOriginalPhoneCodeView verifyOriginalPhoneCodeView) {
        this.verifyOriginalPhoneCodeView = verifyOriginalPhoneCodeView;
        verifyOriginalPhoneCodeModel = new VerifyOriginalPhoneCodeModelImpl(this);
    }

    @Override
    public void verifyCode(OriginalPhoneVerifyRequestBean bean) {
        verifyOriginalPhoneCodeModel.verifyCode(bean);
    }

    @Override
    public void onDestroy() {
        verifyOriginalPhoneCodeModel.onDestroy();
    }

    @Override
    public void onVerifyCodeSuccess(OriginalPhoneVerifyResponseBean bean) {
        verifyOriginalPhoneCodeView.onVerifyOriginalPhoneCodeSuccess(bean);
    }

    @Override
    public void onVerifyCodeFailed(String msg) {
        verifyOriginalPhoneCodeView.onVerifyOriginalPhoneCodeFail(msg);
    }
}
