package cn.cbapay.ympay.mvp.model;

import cn.cbapay.ympay.bean.SubPhotoBean;

/**
 * Created by wangkezheng on 2016/10/26.
 */

public interface SubMitPhotoInfoModel {
    void subMitPhoto(SubPhotoBean bean);
    void Destroy();

    interface SubMitListener{
        void subMitSucceed(String message);
        void subMitFiled(String message);
    }

}
