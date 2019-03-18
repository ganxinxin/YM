package cn.cbapay.ympay.mvp.presenter;

import cn.cbapay.ympay.bean.BindBankCardRequestBean;
import cn.cbapay.ympay.bean.CardBinResponseBean;

/**
 * 添加银行卡
 * Created by icewater on 2016/11/1.
 */

public interface AddCardPresenter {
    void setCardBin(CardBinResponseBean bean);

    void verifyCreditCard(String name, String id, String expire, String cvn2, String phone, boolean agree);

    void verifyDebitCard(String name, String id, String phone, boolean agree);

    void onDestroy();

    interface AddCardView {
        void showMessage(String msg);
        void startPayPassword(BindBankCardRequestBean bean, boolean setPayPassword);
    }
}
