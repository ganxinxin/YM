package cn.cbapay.ympay.mvp.model;

import cn.cbapay.ympay.bean.ForgetPasswordCodeRequsetBean;
import cn.cbapay.ympay.bean.RegistRequestbean;
import cn.cbapay.ympay.bean.RegisterResultBean;

/**
 * Created by Administrator on 2016/10/21.
 */
public interface RegisterSendCodeModel {

    void getCode(ForgetPasswordCodeRequsetBean bean);

    void onDestroy();

    void submit(RegistRequestbean requestbean);

    interface RegisterSendCodeModellListener {

        void onGetCodeError(String msg);

        void onGetCodeNextFailure(String msg);

        void onGetCodeSuccess();

        void checkCodeError();

        void checkCodeSuccess();

        void showMsg(String msg);

        void registSuccess(RegisterResultBean bean);

        void registFail();

    }
}
