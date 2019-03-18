package cn.cbapay.ympay.mvp.presenter;

import cn.cbapay.ympay.bean.IDVerifyRequestBean;
import cn.cbapay.ympay.bean.IDVerifyRespondBean;

/**
 * Created by YangHongfei on 2016/10/31.
 */

public interface VerifyIDPresenter {
    void verifyID(IDVerifyRequestBean bean);

    void onDestroy();

    interface VerifyIDView {
        void onVerifyIDSuccess(IDVerifyRespondBean bean);

        void onVerifyIDFail(String msg);
    }
}
