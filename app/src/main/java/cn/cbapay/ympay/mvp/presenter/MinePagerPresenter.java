package cn.cbapay.ympay.mvp.presenter;

import cn.cbapay.ympay.bean.CustomerInfoResponseBean;
import cn.cbapay.ympay.bean.MinePagerRequestBean;
import cn.cbapay.ympay.bean.RequestBean;
import cn.cbapay.ympay.bean.StpusrinfBean;

/**
 * Created by Administrator on 2016/9/19.
 */
public interface MinePagerPresenter {

    void showOrHideMoeny();

    void getData(RequestBean bean);

    interface MinePagerView {
        void showMoney();

        void hideMoney();

        void getDataSuccess(CustomerInfoResponseBean bean);

        void getDataFail(String s);

        void showMsg(String s);

        void toLogin();
    }
}
