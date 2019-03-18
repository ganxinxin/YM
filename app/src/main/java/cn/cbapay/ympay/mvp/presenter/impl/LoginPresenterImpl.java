package cn.cbapay.ympay.mvp.presenter.impl;

import android.text.TextUtils;

import cn.cbapay.ympay.bean.LoginRequestBean;
import cn.cbapay.ympay.bean.LoginResultBean;
import cn.cbapay.ympay.mvp.model.LoginModel;
import cn.cbapay.ympay.mvp.model.impl.LoginModelImpl;
import cn.cbapay.ympay.mvp.presenter.LoginPresenter;

/**
 * Created by Administrator on 2016/9/18.
 */
public class LoginPresenterImpl implements LoginPresenter,LoginModel.LoginListener {
    private LoginView loginView;
    private CharSequence userLength;
    private CharSequence passWordLength;
    private boolean open = true ;

    private LoginModel loginModel;
    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginModel = new LoginModelImpl(this);
    }

    @Override
    public void callPhone() {
        loginView.showCall();
    }

    @Override
    public void showUserLength(CharSequence user) {
        userLength = user;
    }

    @Override
    public void showPasswordLength(CharSequence password) {
        passWordLength = password;
    }

    @Override
    public void showLoginState() {
        if(userLength!=null && passWordLength!= null){
            loginView.yesLogin();
        }if(TextUtils.isEmpty(userLength)){
            loginView.noLogin();
        }
        if(TextUtils.isEmpty(passWordLength)){
            loginView.noLogin();
        }
    }

    @Override
    public void login(String username, String password, LoginRequestBean bean) {
        if(TextUtils.isEmpty(username)){
            loginView.showErrorMsg("用户名不能为空");
            return;
        }if(TextUtils.isEmpty(password)){
            loginView.showErrorMsg("密码不能为空");
            return;
        }
        loginModel.login(bean);

    }

    @Override
    public void showOrHidePassword() {
        if(open){
            loginView.showPassword();
        }else {
            loginView.hidePassword();
        }
        open = !open;
    }


    @Override
    public void loginSuccess(LoginResultBean bean) {
        loginView.toActivity(bean);
    }

    @Override
    public void loginError(String s) {
            loginView.showErrorMsg(s);
    }

    @Override
    public void showMsg(String msg) {
        loginView.showErrorMsg(msg);
    }
}
