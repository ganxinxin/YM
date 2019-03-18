package cn.cbapay.ympay.mvp.presenter;

import cn.cbapay.ympay.bean.CardBinResponseBean;

/**
 * Created by icewater on 2016/10/31.
 */

public interface InputCardNumberPresenter {
    void verify(String cardNumber);

    void onDestroy();

    interface InputCardNumberView {
        void onVerifyCardSucc(CardBinResponseBean bean);

        void onVerifyCardFail(String msg);

        void showMessage(String msg);
    }
}
