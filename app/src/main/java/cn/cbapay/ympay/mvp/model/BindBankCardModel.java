package cn.cbapay.ympay.mvp.model;

import cn.cbapay.ympay.bean.BindBankCardRequestBean;

/**
 *
 * Created by icewater on 2016/10/26.
 */

public interface BindBankCardModel {
    void bindBankCard(BindBankCardRequestBean bean);

    void onDestroy();

    interface BindBankCardListener {
        void onBindBankCardSuccess();

        void onBindBankCardFailed(String msg);
    }
}
