package cn.cbapay.ympay.mvp.presenter;

import android.view.View;

import cn.cbapay.ympay.bean.ForgetPasswordCodeRequsetBean;

/**
 * Created by Administrator on 2016/9/14.
 */
public interface RegisterPresenter {
    void showOrHidePassword();

    Boolean checkInputBeforeCode(String loginName, String loginPwd, Boolean b);

    void register(String loginName, String loginPwd, String code);

    void onDestroy();

    void numberLength(int number);

    void passwordLenth(int password);

    void showRegisterState();

    void getCode(ForgetPasswordCodeRequsetBean bean);


   /* void showPop(View view);*/

    interface RegisterView {

        void showPassword();

        void hidePassword();

        void showRegisterProgress();

        void hideRegisterProgress();

        void onRegisterSuccess();

        void showErrorMsg(String msg);

        void onCheckCodeSuccess();

        void enabledUI();

        void disableUI();

        void noRegist();

        void yesRegist();

        void toActivity();

        void alreadyRegist();

        /*void showPop(View view);*/

    }
}
