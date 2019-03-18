package cn.cbapay.ympay.mvp.model;

import cn.cbapay.ympay.bean.RequestBean;
import cn.cbapay.ympay.bean.StpusrinfBean;

/**
 *
 * Created by icewater on 2016/10/26.
 */

public interface AccountInfoModel {
    void getAccountInfo(RequestBean bean);

    void onDestroy();

    interface GetAccountInfoListener {
        void onGetAccountInfoSuccess(StpusrinfBean stpusrinf);

        void onGetAccountInfoFailed(String msg);
    }
}
