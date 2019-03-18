package cn.cbapay.ympay.mvp.presenter;

/**
 * Created by icewater on 2016/10/31.
 */

public interface AccountInfoPresenter {

    void getAccountInfo();

    void updateAccountInfo();

    void clickAuthState();

    void clickPhone();

    void clickEmail();

    void clickCareer();

    void clickArea();

    void onDestroy();

    interface AccountInfoView {
        void setAccountName(String name);

        void setAuthState(String state);

        void setPhone(String phone);

        void setEmail(String email);

        void setCareer(String career);

        void setArea(String area);

        void startAuthentication();

        void startIdVerification();

        void startInputPassword();

        void startChangePhone();

        void startEmail();

        void startCareer();

        void startArea();

        void showMessage(String msg);
    }
}
