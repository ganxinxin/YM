package cn.cbapay.ympay.mvp.presenter;

import cn.cbapay.ympay.bean.BindBankCardRequestBean;

/**
 * 绑定银行卡
 * Created by icewater on 2016/11/1.
 */

public interface BindCardPresenter {
    void setCardInfo(BindBankCardRequestBean bean);

    void setPayPassword(String password);

    void sendVerifyCode();

    void bind(String code);

    void initCodeButton();

    void startCountdown();

    void onDestroy();

    interface BindCardView {
        void showMessage(String msg);

        void setPayPasswordFail();

        void setPayPasswordSucc();

        void sendVerifyCodeSucc();

        void sendVerifyCodeFail(String msg);

        void bindSuccess();

        void bindFail(String msg);

        void showSendCodeProgress(String msg);

        void hideSendCodeProgress();

        void showSendStatus(String text);
    }
}
