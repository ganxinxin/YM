package cn.cbapay.ympay.mvp.model;

import cn.cbapay.ympay.bean.ForgetPasswordCodeRequsetBean;
import cn.cbapay.ympay.bean.ForgetPasswordNextBean;

/**
 * Created by Administrator on 2016/10/21.
 */
public interface RegistModel {

    void getCode(ForgetPasswordCodeRequsetBean bean);

    void onDestroy();

    interface RegistModelListener {

        void onGetCodeError(String msg);

        void onGetCodeNextFailure(String msg);

        void onGetCodeSuccess();

        void checkCodeError();

        void checkCodeSuccess();

        void alreadyRegist();

        void showMsg(String msg);

    }
}
