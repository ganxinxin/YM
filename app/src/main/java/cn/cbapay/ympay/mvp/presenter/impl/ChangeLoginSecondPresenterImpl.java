package cn.cbapay.ympay.mvp.presenter.impl;

import cn.cbapay.ympay.bean.ChangeLoginPassWordBean;
import cn.cbapay.ympay.mvp.model.ChangeLoginSenPassWordModel;
import cn.cbapay.ympay.mvp.model.impl.ChangeLoginSenPassWordModelImpl;
import cn.cbapay.ympay.mvp.presenter.ChangeLoginPasswordPresenter;
import cn.cbapay.ympay.mvp.presenter.ChangeLoginSecondPresenter;
import cn.cbapay.ympay.mvp.ui.activity.SettingActivity;
import cn.cbapay.ympay.utils.PassWordUtils;

/**
 * Created by Administrator on 2016/9/22.
 */
public class ChangeLoginSecondPresenterImpl implements ChangeLoginSecondPresenter,ChangeLoginSenPassWordModel.ChangeLoginSenPassWordListener {

    private ChangeLoginSecondPresenter.ChangeLoginSecondView mChangeLoginSecondView;

    private Boolean open = true;

    private ChangeLoginSenPassWordModel changeLoginSenPassWordModel;

    public ChangeLoginSecondPresenterImpl(ChangeLoginSecondPresenter.ChangeLoginSecondView changeLoginSecondView)  {
        this.mChangeLoginSecondView = changeLoginSecondView;
        changeLoginSenPassWordModel = new ChangeLoginSenPassWordModelImpl(this);
    }

    @Override
    public void checkPassword(String password) {
        boolean passWordNO = PassWordUtils.isPassWordNO(password);
        if(passWordNO == false){
            mChangeLoginSecondView.showErrorMsg("密码位字母和数组组合");
            return;
        }
    }

    @Override
    public void showPasswordLength(int length) {
        if(length >= 6){
            mChangeLoginSecondView.yesSure();
        }else {
            mChangeLoginSecondView.noSure();
        }
    }

    @Override
    public void showOrHidePassword() {
        if(open){
            mChangeLoginSecondView.showPassword();
        }else {
            mChangeLoginSecondView.hidePassword();
        }
        open = !open;
    }

    @Override
    public void requestPassword(ChangeLoginPassWordBean bean) {
        changeLoginSenPassWordModel.requsetPassword(bean);
    }

    @Override
    public void changeSuccess() {
        mChangeLoginSecondView.changeSuccess();
    }

    @Override
    public void chanangeError() {

    }

    @Override
    public void showMsg(String msg) {
        mChangeLoginSecondView.showErrorMsg(msg);
    }
}
