package cn.cbapay.ympay.mvp.presenter;

import android.text.BoringLayout;

import cn.cbapay.ympay.bean.ChangePayPswIdBean;

/**
 * Created by Administrator on 2016/9/23.
 */
public interface ChangePaypasswordSecondPresenter {
    void showCardLength(int code);

    Boolean chenckPersonCardCode(String cardcode);

    void checkIdNumber(ChangePayPswIdBean bean);
    interface ChangePayPasswordSecondView {

        void yesNext();
        void noNext();

        void checkSucess(String randomCode);
        void checkError();

        void showMsg(String msg);

        void tokenFail();

        void toLogin();

    }
}
