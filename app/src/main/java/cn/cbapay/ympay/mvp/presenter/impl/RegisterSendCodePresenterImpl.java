package cn.cbapay.ympay.mvp.presenter.impl;

import android.os.Handler;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import cn.cbapay.ympay.bean.ForgetPasswordCodeRequsetBean;
import cn.cbapay.ympay.bean.RegistRequestbean;
import cn.cbapay.ympay.bean.RegisterResultBean;
import cn.cbapay.ympay.mvp.model.DataModel;

import cn.cbapay.ympay.mvp.model.ForgetPasswordModel;
import cn.cbapay.ympay.mvp.model.RegisterSendCodeModel;
import cn.cbapay.ympay.mvp.model.impl.ForgetPasswordModeImpl;
import cn.cbapay.ympay.mvp.model.impl.RegisterSendCodeModelImpl;
import cn.cbapay.ympay.mvp.model.impl.SharedPreferencesModel;
import cn.cbapay.ympay.mvp.presenter.RegisterSendCodePresenter;

/**
 * Created by Administrator on 2016/9/18.
 */
public class RegisterSendCodePresenterImpl implements RegisterSendCodePresenter,RegisterSendCodeModel.RegisterSendCodeModellListener{
    private Timer mTimer;
    private long interval; // 时间间隔;
    private static final int CODE_TIME = 60;
    private Handler mHandler = new Handler();
    private DataModel mDataModel = new SharedPreferencesModel();
    private RegisterSendCodeView registerSendCodeView;
    private RegisterSendCodeModel registerSendCodeModel;

    public RegisterSendCodePresenterImpl(RegisterSendCodeView registerSendCodeView) {
        this.registerSendCodeView = registerSendCodeView;
        this.registerSendCodeModel = new RegisterSendCodeModelImpl(this);
    }

    @Override
    public void submit(RegistRequestbean requestbean) {
        registerSendCodeModel.submit(requestbean);
    }

    @Override
    public void showSubmitState(int length) {
        if(length == 6){
        registerSendCodeView.yesSubmit();
        }else {
            registerSendCodeView.noSubmit();
        }
    }

    @Override
    public void initCodeButton() {
        mTimer = new Timer();
        // 设置验证码发送时间，初始化按钮
        mDataModel.setValue("mSendCodeTime", new Date().getTime());
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                long codeTime = (long) mDataModel.getValue("mSendCodeTime", 0L);
                interval = new Date().getTime() - codeTime;
                if (interval > CODE_TIME * 1000) {
                    mHandler.post(new Runnable() {

                        @Override
                        public void run() {
                            registerSendCodeView.hideSendCodeProgress();
                            mTimer.cancel();
                        }
                    });
                } else {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            registerSendCodeView.showSendStatus((CODE_TIME - interval / 1000) + "秒后重发");
                        }
                    });
                }
            }
        }, 0, 1000);
    }

    @Override
    public void getCode(ForgetPasswordCodeRequsetBean bean) {
        registerSendCodeModel.getCode(bean);

    }


    @Override
    public void onGetCodeError(String msg) {
        registerSendCodeView.showMsg(msg);
    }

    @Override
    public void onGetCodeNextFailure(String msg) {

    }

    @Override
    public void onGetCodeSuccess() {
        initCodeButton();
    }

    @Override
    public void checkCodeError() {

    }

    @Override
    public void checkCodeSuccess() {

    }

    @Override
    public void showMsg(String msg) {
        registerSendCodeView.showMsg(msg);
    }

    @Override
    public void registSuccess(RegisterResultBean bean) {
        registerSendCodeView.to(bean);
    }



    @Override
    public void registFail() {

    }
}
