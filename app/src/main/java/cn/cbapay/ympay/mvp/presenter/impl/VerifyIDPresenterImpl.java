package cn.cbapay.ympay.mvp.presenter.impl;

import cn.cbapay.ympay.bean.IDVerifyRequestBean;
import cn.cbapay.ympay.bean.IDVerifyRespondBean;
import cn.cbapay.ympay.mvp.model.VerifyIDModel;
import cn.cbapay.ympay.mvp.model.impl.VerifyIDModelImpl;
import cn.cbapay.ympay.mvp.presenter.VerifyIDPresenter;

/**
 * Created by YangHongfei on 2016/10/31.
 */

public class VerifyIDPresenterImpl implements VerifyIDPresenter, VerifyIDModel.VerifyIDListener {
    private static final String TAG = VerifyIDPresenterImpl.class.getSimpleName();
    private VerifyIDView verifyIDView;
    private VerifyIDModel verifyIDModel;

    public VerifyIDPresenterImpl(VerifyIDView verifyIDView) {
        this.verifyIDView = verifyIDView;
        verifyIDModel = new VerifyIDModelImpl(this);
    }

    @Override
    public void verifyID(IDVerifyRequestBean bean) {
        verifyIDModel.verifyID(bean);
    }

    @Override
    public void onDestroy() {
        verifyIDModel.onDestroy();
    }

    @Override
    public void onVerifyIDSuccess(IDVerifyRespondBean bean) {
        verifyIDView.onVerifyIDSuccess(bean);
    }

    @Override
    public void onVerifyIDFailed(String msg) {
        verifyIDView.onVerifyIDFail(msg);
    }
}
