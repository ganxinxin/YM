package cn.cbapay.ympay.mvp.model;

import cn.cbapay.ympay.bean.SetPayPasswordRequestBean;

/**
 *
 * Created by icewater on 2016/10/26.
 */

public interface SetPayPasswordModel {
    void setPassword(SetPayPasswordRequestBean bean);

    void onDestroy();

    interface SetPasswordListener {
        void onSetPasswordSuccess();

        void onSetPasswordFailed(String msg);
    }
}
