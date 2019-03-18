package cn.cbapay.ympay.mvp.model;

import cn.cbapay.ympay.bean.ArpAcProfileBean;
import cn.cbapay.ympay.bean.RequestBean;
import cn.cbapay.ympay.bean.StpcusinfBean;
import cn.cbapay.ympay.bean.StpusrinfBean;

/**
 *
 * Created by icewater on 2016/10/25.
 */

public interface CustomerInfoModel {
    void findCustomerInfo(RequestBean bean);

    void onDestroy();

    interface FindCustomerInfoListener {
        void onFindCustomerInfoSuccess(ArpAcProfileBean arpAcProfile, StpusrinfBean stpusrinf, StpcusinfBean stpcusinf, String payPwdStatus);

        void onFindCustomerInfoFailed(String msg);
    }
}
