package cn.cbapay.ympay.mvp.presenter;

import cn.cbapay.ympay.bean.ChangeLoginPassWordBean;

/**
 * Created by Administrator on 2016/9/22.
 */
public interface ChangeLoginSecondPresenter {
    void checkPassword(String Password);

    void showPasswordLength(int length);

    void showOrHidePassword();

    void requestPassword(ChangeLoginPassWordBean bean);

    interface ChangeLoginSecondView {

        void yesSure();

        void noSure();

        void showErrorMsg(String msg);

        void showPassword();

        void hidePassword();

        void changeSuccess();

    }
}
