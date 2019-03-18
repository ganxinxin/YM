package cn.cbapay.ympay.mvp.model;

import cn.cbapay.ympay.bean.ForgetPasswordCodeRequsetBean;
import cn.cbapay.ympay.bean.ForgetPasswordNextBean;
import cn.cbapay.ympay.bean.ForgetPasswordResultBean;

/**
 * Created by Administrator on 2016/10/27.
 */
public interface ChangeLoginPassWordModel {
    void getCode(ForgetPasswordCodeRequsetBean bean);

    void next(ForgetPasswordNextBean bean);

    void onDestroy();

    interface ChangeLoginPassWordListener {

        void onGetCodeError(String msg);

        void onGetCodeNextFailure(String msg);

        void onGetCodeSuccess(String msg);

        void checkCodeError();

        void checkCodeSuccess(ForgetPasswordResultBean bean);

    }

}
