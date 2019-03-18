package cn.cbapay.ympay.mvp.model;

import cn.cbapay.ympay.bean.AreaModifyRequestBean;

/**
 * Created by YangHongfei on 2016/10/28.
 */
public interface AreaModifyModel {
    void updatePhoneAddress(AreaModifyRequestBean bean);

    void onDestroy();

    interface AreaModifyListener {
        void onAreaModifySuccess();

        void onAreaModifyFail(String msg);
    }
}
