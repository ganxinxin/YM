package cn.cbapay.ympay.mvp.model;

import cn.cbapay.ympay.bean.IDVerifyRequestBean;
import cn.cbapay.ympay.bean.IDVerifyRespondBean;

/**
 *
 * Created by icewater on 2016/10/26.
 */

public interface VerifyIDModel {
    void verifyID(IDVerifyRequestBean bean);

    void onDestroy();

    interface VerifyIDListener {
        void onVerifyIDSuccess(IDVerifyRespondBean bean);

        void onVerifyIDFailed(String msg);
    }
}
