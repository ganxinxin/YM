package cn.cbapay.ympay.mvp.presenter.impl;

import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.Date;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import cn.cbapay.ympay.R;
import cn.cbapay.ympay.bean.ForgetPasswordCodeRequsetBean;
import cn.cbapay.ympay.mvp.model.DataModel;
import cn.cbapay.ympay.mvp.model.RegistModel;
import cn.cbapay.ympay.mvp.model.impl.RegistModelImpl;
import cn.cbapay.ympay.mvp.model.impl.SharedPreferencesModel;
import cn.cbapay.ympay.mvp.presenter.RegisterPresenter;
import cn.cbapay.ympay.utils.PassWordUtils;
import cn.cbapay.ympay.utils.PhoneUtils;

/**
 * Created by Administrator on 2016/9/14.
 */
public class RegisterPresenterImpl implements RegisterPresenter,RegistModel.RegistModelListener{
    private RegisterView registerView;
    private boolean open = true;
    private int mNumber;
    private int mPassword;
    private RegistModel registModel;
    public RegisterPresenterImpl(RegisterView registerView) {
        this.registerView = registerView;
        this.registModel = new RegistModelImpl(this);
    }

    @Override
    public void showOrHidePassword() {
        if(open){
            registerView.showPassword();
        }else {
            registerView.hidePassword();
        }
        open = !open;
    }



    @Override
    public Boolean checkInputBeforeCode(String number, String loginPwd , Boolean b) {
        if(TextUtils.isEmpty(number)){
            registerView.showErrorMsg("请输入手机号");
           return false;
        }else{
            boolean mobileNO = PhoneUtils.isMobileNO(number);
            if(mobileNO == false){
                registerView.showErrorMsg("请输入正确手机号");
                return false;
            }
        }
        if(TextUtils.isEmpty(loginPwd)){
            registerView.showErrorMsg("请输入密码");
             return false;
        }else {
            boolean passWordNO = PassWordUtils.isPassWordNO(loginPwd);
            if(passWordNO == false){
                registerView.showErrorMsg("登录密码需组合字母与数字");
                return false;
            }
        }
        if(!b){
            registerView.showErrorMsg("勾选同意协议才可注册");
              return false;
        }
        return  true;

    }

    @Override
    public void register(String loginName, String loginPwd, String code) {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void numberLength(int number) {
        mNumber = number;
    }

    @Override
    public void passwordLenth(int password) {
        mPassword = password;
    }

    @Override
    public void showRegisterState() {
        if(mNumber ==11 && mPassword >=6){
            registerView.yesRegist();
        }else {
            registerView.noRegist();
        }
    }

    @Override
    public void getCode(ForgetPasswordCodeRequsetBean bean) {
        registModel.getCode(bean);
    }

  /*  @Override
    public void showPop(View view) {
       registerView.showPop(view);
    }*/




    @Override
    public void onGetCodeError(String msg) {
        registerView.showErrorMsg(msg);
    }

    @Override
    public void onGetCodeNextFailure(String msg) {

    }

    @Override
    public void onGetCodeSuccess() {
        registerView.toActivity();
    }

    @Override
    public void checkCodeError() {

    }

    @Override
    public void checkCodeSuccess() {

    }

    @Override
    public void alreadyRegist() {
        registerView.alreadyRegist();
    }

    @Override
    public void showMsg(String msg) {
        registerView.showErrorMsg(msg);
    }
}
