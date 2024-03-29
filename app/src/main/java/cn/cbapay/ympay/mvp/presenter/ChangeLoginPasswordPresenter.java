package cn.cbapay.ympay.mvp.presenter;

import cn.cbapay.ympay.bean.ForgetPasswordCodeRequsetBean;
import cn.cbapay.ympay.bean.ForgetPasswordNextBean;
import cn.cbapay.ympay.bean.ForgetPasswordResultBean;

/**
 * Created by Administrator on 2016/9/22.
 */
public interface ChangeLoginPasswordPresenter {
    void showCodeLength(int code);

    void getCode(ForgetPasswordCodeRequsetBean bean);

    void onDestory();

    void initCodeButton();

    void onNext(ForgetPasswordNextBean bean);

    interface ChangeLoginPasswordView {

        void yesNext();

        void noNext();

        void showErrorMsg(String msg);

        void toActivity(ForgetPasswordResultBean bean);

        void hideSendCodeProgress();

        void showSendStatus(String msg);

    }
}
