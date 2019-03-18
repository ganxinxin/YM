package cn.cbapay.ympay.mvp.model;

import cn.cbapay.ympay.bean.AreaBean;

/**
 * Created by Administrator on 2016/9/21.
 */
public interface AreaModel {

    void getArea();
    void onDestroy();


    interface AreaListener {

        void getAreaSuccess(AreaBean bean);

        void getAreaFail(String msg);

    }
}
