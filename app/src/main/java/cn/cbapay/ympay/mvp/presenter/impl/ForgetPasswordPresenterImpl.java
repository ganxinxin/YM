package cn.cbapay.ympay.mvp.presenter.impl;

import android.os.Handler;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import cn.cbapay.ympay.bean.ForgetPasswordCodeRequsetBean;
import cn.cbapay.ympay.bean.ForgetPasswordNextBean;
import cn.cbapay.ympay.bean.ForgetPasswordResultBean;
import cn.cbapay.ympay.mvp.model.DataModel;
import cn.cbapay.ympay.mvp.model.ForgetPasswordModel;
import cn.cbapay.ympay.mvp.model.impl.ForgetPasswordModeImpl;
import cn.cbapay.ympay.mvp.model.impl.SharedPreferencesModel;
import cn.cbapay.ympay.mvp.presenter.ForgetPasswordPresenter;
import cn.cbapay.ympay.utils.PhoneUtils;
import cn.cbapay.ympay.utils.ShareUtil;

/**
 * Created by Administrator on 2016/9/20.
 */
public class ForgetPasswordPresenterImpl implements ForgetPasswordPresenter,ForgetPasswordModel.ForgetPasswordModelListener {
    private ForgetPasswordView forgetPasswordView;
    private int mNumber;
    private int mCode;
    private Timer mTimer;
    private long interval; // 时间间隔;
    private ForgetPasswordModel forgetPasswordModel;
    private static final int CODE_TIME = 60;
    private Handler mHandler = new Handler();
    private DataModel mDataModel = new SharedPreferencesModel();

    public ForgetPasswordPresenterImpl(ForgetPasswordView  forgetPasswordView) {
        this.forgetPasswordView = forgetPasswordView;
        this.forgetPasswordModel = new ForgetPasswordModeImpl(this);
    }

    @Override
    public void showButtonState() {
        if(mNumber==11 && mCode ==6){
            forgetPasswordView.yesButton();
        }else {
            forgetPasswordView.noButton();
        }

    }

    @Override
    public void showNumberLength(int number) {
        mNumber = number;

    }

    @Override
    public void showCodeLength(int code) {
        mCode = code;

    }

    @Override
    public void checkNumberCode(String number, String code) {
        boolean mobileNO = PhoneUtils.isMobileNO(number);
        if(mobileNO == false){
            forgetPasswordView.showErrorMsg("请输入正确手机号");
            return;
        }
    }

    @Override
    public void getCode(ForgetPasswordCodeRequsetBean bean) {
        forgetPasswordModel.getCode(bean);

    }

    @Override
    public void onDestory() {
        forgetPasswordModel.onDestroy();
    }

    @Override
    public void onGetCodeError(String msg) {
        forgetPasswordView.showErrorMsg(msg);
    }

    @Override
    public void onGetCodeNextFailure(String msg) {

    }

    @Override
    public void onGetCodeSuccess(String msg) {
        //发送验证码成功
        forgetPasswordView.showErrorMsg(msg);

        // 设置验证码发送时间，初始化按钮
        mDataModel.setValue("mSendCodeTime", new Date().getTime());

        initCodeButton();

    }

    @Override
    public void checkCodeError() {
        forgetPasswordView.showErrorMsg("验证码校验失败");
    }

    @Override
    public void checkCodeSuccess(ForgetPasswordResultBean bean) {
        forgetPasswordView.toActivity(bean);
    }

    @Override
    public void initCodeButton() {
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                long codeTime = (long) mDataModel.getValue("mSendCodeTime", 0L);
                interval = new Date().getTime() - codeTime;
                if (interval > CODE_TIME * 1000) {
                    mHandler.post(new Runnable() {

                        @Override
                        public void run() {
                            forgetPasswordView.hideSendCodeProgress();
                            mTimer.cancel();
                        }
                    });
                } else {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            forgetPasswordView.showSendStatus((CODE_TIME - interval / 1000) + "秒后重发");
                        }
                    });
                }
            }
        }, 0, 1000);
    }

    @Override
    public void onNext(ForgetPasswordNextBean bean) {
        forgetPasswordModel.next(bean);
    }
}
