package cn.cbapay.ympay.mvp.presenter.impl;

import cn.cbapay.ympay.bean.ChangePayPswIdBean;
import cn.cbapay.ympay.mvp.model.ChangePayCheckIdModel;
import cn.cbapay.ympay.mvp.model.impl.ChangePayCheckIdModelImpl;
import cn.cbapay.ympay.mvp.presenter.ChangePaypasswordSecondPresenter;
import cn.cbapay.ympay.utils.IDCardUtils;

/**
 * Created by Administrator on 2016/9/23.
 */
public class ChangePaySecondPresenterImpl implements ChangePaypasswordSecondPresenter,ChangePayCheckIdModel.ChangePayCheckIdModelListener{
    private ChangePayPasswordSecondView mChangePayPasswordSecondView;
    private ChangePayCheckIdModel changePayCheckIdModel;
    public ChangePaySecondPresenterImpl(ChangePayPasswordSecondView changePayPasswordSecondView) {
        this.mChangePayPasswordSecondView = changePayPasswordSecondView;
        changePayCheckIdModel = new ChangePayCheckIdModelImpl(this);
    }

    @Override
    public void showCardLength(int code) {
        if(code == 18){
            mChangePayPasswordSecondView.yesNext();
        }else {
            mChangePayPasswordSecondView.noNext();
        }
    }

    @Override
    public Boolean chenckPersonCardCode(String cardcode) {
        boolean b = IDCardUtils.IDCardValidate(cardcode);
        if(b){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void checkIdNumber(ChangePayPswIdBean bean) {
        changePayCheckIdModel.checkIdNumber(bean);
    }

    @Override
    public void checkSuccess(String randomCode) {
        mChangePayPasswordSecondView.checkSucess(randomCode);
    }

    @Override
    public void chenckError() {

    }

    @Override
    public void showMsg(String msg) {
        mChangePayPasswordSecondView.showMsg(msg);
    }


}
