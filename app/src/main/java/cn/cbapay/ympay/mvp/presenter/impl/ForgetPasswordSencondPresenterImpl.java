package cn.cbapay.ympay.mvp.presenter.impl;

import cn.cbapay.ympay.bean.FrogetPasswordSenRequestBean;
import cn.cbapay.ympay.mvp.model.ForgetPasswordSencondModel;
import cn.cbapay.ympay.mvp.model.impl.ForgetPasswordSencondModelImpl;
import cn.cbapay.ympay.mvp.presenter.ForgetPasswordSencondPresenter;
import cn.cbapay.ympay.utils.PassWordUtils;

/**
 * Created by Administrator on 2016/10/26.
 */
public class ForgetPasswordSencondPresenterImpl implements ForgetPasswordSencondPresenter,ForgetPasswordSencondModel.ForgetPasswordSencondListener {
    private ForgetPasswordSencondPresenter.ForgetPasswordSencondView  forgetPasswordSencondView;
    private boolean open = true;
    private int mPassword;
    private ForgetPasswordSencondModel forgetPasswordSencondModel;
    public ForgetPasswordSencondPresenterImpl(ForgetPasswordSencondPresenter.ForgetPasswordSencondView forgetPasswordSencondView) {
        this.forgetPasswordSencondView = forgetPasswordSencondView;
        this.forgetPasswordSencondModel = new ForgetPasswordSencondModelImpl(this);
    }

    @Override
    public void showOrHidePassword() {
        if(open){
            forgetPasswordSencondView.showPassword();
        }else {
            forgetPasswordSencondView.hidePassword();
        }
        open = !open;
    }

    @Override
    public void numberLength(int number) {

    }

    @Override
    public void passwordLenth(int password) {
        mPassword = password;
    }

    @Override
    public void showConfirmState() {
        if(mPassword >=6){
            forgetPasswordSencondView.yesConfirm();
        }else {
            forgetPasswordSencondView.noConfirm();
        }
    }

    @Override
    public Boolean checkInputBeforeCode(String password) {
        boolean passWordNO = PassWordUtils.isPassWordNO(password);
        if(passWordNO == false){
            forgetPasswordSencondView.showMsg("登录密码需组合字母与数字");
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void requestPassword(FrogetPasswordSenRequestBean bean) {
        forgetPasswordSencondModel.requsetPassword(bean);
    }

    @Override
    public void changeSuccess() {
        forgetPasswordSencondView.changeSuccess();
    }

    @Override
    public void chanangeError() {

    }

    @Override
    public void showMsg(String msg) {
        forgetPasswordSencondView.showMsg(msg);
    }
}
