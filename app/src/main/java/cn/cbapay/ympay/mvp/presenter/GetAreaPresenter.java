package cn.cbapay.ympay.mvp.presenter;

import cn.cbapay.ympay.bean.AreaBean;

/**
 * Created by Administrator on 2016/9/21.
 */
public interface GetAreaPresenter {

    void getArea();

    void onDestroy();

    interface AreaView {

        void onGetAreaSuccess(AreaBean bean);

        void onGetAreaFail(String msg);


    }
}
