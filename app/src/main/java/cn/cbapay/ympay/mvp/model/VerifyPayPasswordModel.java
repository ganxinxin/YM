package cn.cbapay.ympay.mvp.model;

import cn.cbapay.ympay.bean.PayPasswordVerifyRequestBean;
import cn.cbapay.ympay.bean.PayPasswordVerifyResponseBean;

/**
 *
 * Created by icewater on 2016/10/26.
 */

public interface VerifyPayPasswordModel {
    void verifyPassword(PayPasswordVerifyRequestBean bean);

    void onDestroy();

    interface VerifyPasswordListener {
        void onVerifyPasswordSuccess(PayPasswordVerifyResponseBean bean);

        void onVerifyPasswordFailed(String code, String msg);
    }
}
