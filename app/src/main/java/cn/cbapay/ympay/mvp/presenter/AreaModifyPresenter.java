package cn.cbapay.ympay.mvp.presenter;

import cn.cbapay.ympay.bean.AreaModifyRequestBean;

/**
 * Created by YangHongfei on 2016/10/28.
 */
public interface AreaModifyPresenter {
    void updatePhoneAddress(AreaModifyRequestBean bean);

    void onDestroy();

    interface AreaModifyView {
        void onAreaModifySuccess();

        void onAreaModifyFail(String msg);
    }
}
