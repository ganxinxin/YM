package cn.cbapay.ympay.mvp.presenter.impl;

import cn.cbapay.ympay.bean.SubPhotoBean;
import cn.cbapay.ympay.mvp.model.SubMitPhotoInfoModel;
import cn.cbapay.ympay.mvp.model.impl.SubMitPhotoInfoModelImpl;
import cn.cbapay.ympay.mvp.presenter.SubMitPhotoPresenter;

/**
 * Created by Administrator on 2016/10/27.
 */

public class SubMitPhotoPresenterImpl implements SubMitPhotoPresenter ,SubMitPhotoInfoModel.SubMitListener{
    private SubMitPhotoInfoModel mSubMitPhotoInfoModel;
    private SubView mSubView;

    public SubMitPhotoPresenterImpl(SubView subView) {
        mSubView = subView;
        mSubMitPhotoInfoModel = new SubMitPhotoInfoModelImpl(this);

    }

    @Override
    public void subMitPhoto(SubPhotoBean bean) {

        mSubMitPhotoInfoModel.subMitPhoto(bean);
        mSubView.showProgress();
    }

    @Override
    public void Destroy() {
        mSubMitPhotoInfoModel.Destroy();
    }

    @Override
    public void subMitSucceed(String message) {

        mSubView.hideProgress();
        mSubView.subMitSucceed(message);
    }

    @Override
    public void subMitFiled(String message) {
        mSubView.hideProgress();
        mSubView.subMitFiled(message);
    }
}
