package cn.cbapay.ympay.mvp.presenter;

import cn.cbapay.ympay.bean.CodeRequestBean;

public interface ValidateCodePresenter {

    void initCodeButton();

    void getValidateCode(CodeRequestBean bean);

    void startCountdown();

    void onDestroy();

    interface ValidateCodeView {

        void showSendCodeProgress(String msg);

        void hideSendCodeProgress();

        void showSendStatus(String text);

        void showGetCodeErrorMsg(String msg);
    }
}
