package cn.cbapay.ympay.mvp.presenter;

import cn.cbapay.ympay.bean.FrogetPasswordSenRequestBean;

/**
 * Created by Administrator on 2016/10/26.
 */
public interface ForgetPasswordSencondPresenter {
    void showOrHidePassword();

    void numberLength(int number);

    void passwordLenth(int password);

    void showConfirmState();

    Boolean checkInputBeforeCode(String password);

    void requestPassword(FrogetPasswordSenRequestBean bean);

    interface ForgetPasswordSencondView {

        void showPassword();

        void hidePassword();

        void noConfirm();

        void yesConfirm();

        void showMsg(String msg);

        void changeSuccess();

    }
}
