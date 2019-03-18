package cn.cbapay.ympay.mvp.model;


import cn.cbapay.ympay.bean.CustomerInfoResponseBean;

import cn.cbapay.ympay.bean.RequestBean;


/**
 * Created by Administrator on 2016/10/25.
 */
public interface MinePagerModel {

    void getData(RequestBean bean);

    interface MinePagerModellListener {
        void getDataSuccess(CustomerInfoResponseBean bean);

        void getDataFail(String s);


    }
}
