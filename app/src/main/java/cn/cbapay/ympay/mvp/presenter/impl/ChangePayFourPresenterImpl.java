package cn.cbapay.ympay.mvp.presenter.impl;

import cn.cbapay.ympay.bean.ChangePayPswRequestBean;
import cn.cbapay.ympay.mvp.model.ChangePayFourModel;
import cn.cbapay.ympay.mvp.model.impl.ChangePayFourModelImpl;
import cn.cbapay.ympay.mvp.presenter.ChangePayFourPresenter;

/**
 * Created by Administrator on 2016/10/31.
 */
public class ChangePayFourPresenterImpl implements ChangePayFourPresenter,ChangePayFourModel.ChangePayFourListener {
    private ChangePayFourPresenter.ChangePayFourView changePayFourView;
    private ChangePayFourModel changePayFourModel;
    public ChangePayFourPresenterImpl(ChangePayFourPresenter.ChangePayFourView changePayFourView) {
        this.changePayFourView = changePayFourView;
        changePayFourModel = new ChangePayFourModelImpl(this);
    }

    @Override
    public void changePsw(ChangePayPswRequestBean bean) {
        changePayFourModel.changePsw(bean);
    }

    @Override
    public void changeSuccess() {
        changePayFourView.changeSuccess();
    }

    @Override
    public void changeError() {

    }

    @Override
    public void showMessg(String msg) {
        changePayFourView.showMsg(msg);
    }
}
