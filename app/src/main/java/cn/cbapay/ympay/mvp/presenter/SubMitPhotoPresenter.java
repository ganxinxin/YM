package cn.cbapay.ympay.mvp.presenter;

import cn.cbapay.ympay.bean.SubPhotoBean;

/**
 * Created by Administrator on 2016/10/27.
 */

public interface SubMitPhotoPresenter {
    void subMitPhoto(SubPhotoBean bean);
    void Destroy();
    interface SubView{
        void subMitSucceed(String message);
        void subMitFiled(String message);
        void showProgress();
        void hideProgress();
    }
}
