package cn.cbapay.ympay.mvp.presenter;

import cn.cbapay.ympay.bean.ForgetPasswordCodeRequsetBean;
import cn.cbapay.ympay.bean.RegistRequestbean;
import cn.cbapay.ympay.bean.RegisterResultBean;

/**
 * Created by Administrator on 2016/9/18.
 */
public interface RegisterSendCodePresenter {


    void submit(RegistRequestbean requestbean);

    void showSubmitState(int length);

    void initCodeButton();

    void getCode(ForgetPasswordCodeRequsetBean bean);


    interface RegisterSendCodeView {

        void to(RegisterResultBean bean);

        void yesSubmit();

        void noSubmit();

        void hideSendCodeProgress();

        void showSendStatus(String msg);

        void showMsg(String msg);
    }
}
