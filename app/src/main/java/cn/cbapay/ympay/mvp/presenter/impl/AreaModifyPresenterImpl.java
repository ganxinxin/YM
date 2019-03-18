package cn.cbapay.ympay.mvp.presenter.impl;

import cn.cbapay.ympay.bean.AreaModifyRequestBean;
import cn.cbapay.ympay.mvp.model.AreaModifyModel;
import cn.cbapay.ympay.mvp.model.impl.AreaModifyModelImpl;
import cn.cbapay.ympay.mvp.presenter.AreaModifyPresenter;

/**
 * Created by YangHongfei on 2016/10/28.
 */
public class AreaModifyPresenterImpl implements AreaModifyPresenter, AreaModifyModel.AreaModifyListener {
    private static final String TAG = AreaModifyPresenterImpl.class.getSimpleName();
    private AreaModifyModel areaModifyModel;
    private AreaModifyView areaModifyView;

    public AreaModifyPresenterImpl(AreaModifyPresenter.AreaModifyView areaModifyView) {
        this.areaModifyView = areaModifyView;
        areaModifyModel = new AreaModifyModelImpl(this);
    }

    @Override
    public void updatePhoneAddress(AreaModifyRequestBean bean) {
        areaModifyModel.updatePhoneAddress(bean);
    }

    @Override
    public void onDestroy() {
        areaModifyModel.onDestroy();
    }

    @Override
    public void onAreaModifySuccess() {
        areaModifyView.onAreaModifySuccess();
    }

    @Override
    public void onAreaModifyFail(String msg) {
        areaModifyView.onAreaModifyFail(msg);
    }
}
