package cn.cbapay.ympay.mvp.model;

import cn.cbapay.ympay.bean.CardBinRequestBean;
import cn.cbapay.ympay.bean.CardBinResponseBean;

/**
 *
 * Created by icewater on 2016/10/26.
 */

public interface FindBankBinModel {
    void findBankBin(CardBinRequestBean bean);

    void onDestroy();

    interface FindBankBinListener {
        void onFindBankBinSuccess(CardBinResponseBean bean);

        void onFindBankBinFailed(String msg);
    }
}
