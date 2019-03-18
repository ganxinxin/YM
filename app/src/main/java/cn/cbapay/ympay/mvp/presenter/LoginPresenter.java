package cn.cbapay.ympay.mvp.presenter;

import cn.cbapay.ympay.bean.LoginRequestBean;
import cn.cbapay.ympay.bean.LoginResultBean;

/**
 * Created by Administrator on 2016/9/18.
 */
public interface LoginPresenter {
    void callPhone();

    void showUserLength(CharSequence user);

    void showPasswordLength(CharSequence password);

    void showLoginState();

    void login(String username, String password, LoginRequestBean bean);

    void showOrHidePassword();

    interface LoginView {
        void showCall();

        void yesLogin();

        void noLogin();

        void showErrorMsg(String msg);

        void showPassword();

        void hidePassword();

        void toActivity(LoginResultBean bean);
    }
}
