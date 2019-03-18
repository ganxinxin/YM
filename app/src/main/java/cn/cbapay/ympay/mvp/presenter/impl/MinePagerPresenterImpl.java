package cn.cbapay.ympay.mvp.presenter.impl;

import cn.cbapay.ympay.bean.CustomerInfoResponseBean;
import cn.cbapay.ympay.bean.MinePagerRequestBean;
import cn.cbapay.ympay.bean.RequestBean;
import cn.cbapay.ympay.bean.StpusrinfBean;
import cn.cbapay.ympay.mvp.model.MinePagerModel;
import cn.cbapay.ympay.mvp.model.impl.MinePagerModelImpl;
import cn.cbapay.ympay.mvp.presenter.MinePagerPresenter;

/**
 * Created by Administrator on 2016/9/19.
 */
public class MinePagerPresenterImpl implements MinePagerPresenter,MinePagerModel.MinePagerModellListener{
    private MinePagerView minePagerView;
    private  Boolean open = true;
    private MinePagerModel minePagerModel;
    public MinePagerPresenterImpl(MinePagerView minePagerView) {
            this.minePagerView = minePagerView;
        this.minePagerModel = new MinePagerModelImpl(this);
    }

    @Override
    public void showOrHideMoeny() {
        if(open){
            minePagerView.showMoney();
        }else {
            minePagerView.hideMoney();
        }
        open = !open;
    }

    @Override
    public void getData(RequestBean bean) {
        minePagerModel.getData(bean);
    }

    @Override
    public void getDataSuccess(CustomerInfoResponseBean bean) {
        minePagerView.getDataSuccess(bean);
    }

    @Override
    public void getDataFail(String s) {
        minePagerView.getDataFail(s);
    }


}
