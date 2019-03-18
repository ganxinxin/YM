package cn.cbapay.ympay.mvp.model;

import cn.cbapay.ympay.bean.ForgetPasswordCodeRequsetBean;
import cn.cbapay.ympay.bean.ForgetPasswordNextBean;
import cn.cbapay.ympay.bean.ForgetPasswordResultBean;

/**
 * Created by Administrator on 2016/10/20.
 */
public interface ForgetPasswordModel {

    void getCode(ForgetPasswordCodeRequsetBean bean);

    void next(ForgetPasswordNextBean bean);

    void onDestroy();

    interface ForgetPasswordModelListener {
        void onGetCodeError(String msg);

        void onGetCodeNextFailure(String msg);

        void onGetCodeSuccess(String msg);

        void checkCodeError();

        void checkCodeSuccess(ForgetPasswordResultBean bean);

    }

}
