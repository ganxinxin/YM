package cn.cbapay.ympay.mvp.presenter.impl;

import cn.cbapay.ympay.bean.AreaBean;
import cn.cbapay.ympay.mvp.model.AreaModel;
import cn.cbapay.ympay.mvp.model.impl.AreaModelImpl;
import cn.cbapay.ympay.mvp.presenter.GetAreaPresenter;

/**
 * Created by Administrator on 2016/9/21.
 */
public class GetAreaPresenterImpl implements GetAreaPresenter, AreaModel.AreaListener {
    private AreaView areaView;
    private AreaModelImpl areaModel;


    public GetAreaPresenterImpl(AreaView areaView) {
        this.areaView = areaView;
        areaModel = new AreaModelImpl(this);
    }


    @Override
    public void getArea() {
        areaModel.getArea();
    }

    @Override
    public void onDestroy() {
        areaModel.onDestroy();
    }

    @Override
    public void getAreaSuccess(AreaBean bean) {
        areaView.onGetAreaSuccess(bean);
    }

    @Override
    public void getAreaFail(String msg) {
        areaView.onGetAreaFail(msg);
    }
}
