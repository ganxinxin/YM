package cn.cbapay.ympay.mvp.presenter;

import cn.cbapay.ympay.bean.ForgetPasswordCodeRequsetBean;
import cn.cbapay.ympay.bean.ForgetPasswordNextBean;
import cn.cbapay.ympay.bean.ForgetPasswordResultBean;

/**
 * Created by Administrator on 2016/9/20.
 */
public interface ForgetPasswordPresenter {

    void showButtonState();

    void showNumberLength(int number);

    void showCodeLength(int code);

    void checkNumberCode(String number, String code);

    void getCode(ForgetPasswordCodeRequsetBean bean);

    void onDestory();

    void initCodeButton();

    void onNext(ForgetPasswordNextBean bean);

    interface ForgetPasswordView {
        void noButton();

        void yesButton();

        void showErrorMsg(String msg);

        void toActivity(ForgetPasswordResultBean bean);

        void hideSendCodeProgress();

        void showSendStatus(String msg);
    }
}
