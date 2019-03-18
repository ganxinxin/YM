package cn.cbapay.ympay.mvp.presenter.impl;

import android.os.Handler;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import cn.cbapay.ympay.bean.ForgetPasswordCodeRequsetBean;
import cn.cbapay.ympay.bean.ForgetPasswordNextBean;
import cn.cbapay.ympay.bean.ForgetPasswordResultBean;
import cn.cbapay.ympay.mvp.model.ChangeLoginPassWordModel;
import cn.cbapay.ympay.mvp.model.DataModel;
import cn.cbapay.ympay.mvp.model.impl.ChangeLoginPasswordModelImpl;
import cn.cbapay.ympay.mvp.model.impl.SharedPreferencesModel;
import cn.cbapay.ympay.mvp.presenter.ChangeLoginPasswordPresenter;

/**
 * Created by Administrator on 2016/9/22.
 */
public class ChangeLoginPasswordPresenterImpl implements ChangeLoginPasswordPresenter, ChangeLoginPassWordModel.ChangeLoginPassWordListener {
    private ChangeLoginPasswordView mChangeLoginPasswordView;
    private ChangeLoginPassWordModel changeLoginPassWordModel;
    private int mNumber;
    private int mCode;
    private Timer mTimer;
    private long interval; // 时间间隔;
    private static final int CODE_TIME = 60;
    private Handler mHandler = new Handler();
    private DataModel mDataModel = new SharedPreferencesModel();

    public ChangeLoginPasswordPresenterImpl(ChangeLoginPasswordView changeLoginPasswordView) {
        this.mChangeLoginPasswordView = changeLoginPasswordView;
        this.changeLoginPassWordModel = new ChangeLoginPasswordModelImpl(this);
    }


    @Override
    public void showCodeLength(int code) {
        if (code == 6) {
            mChangeLoginPasswordView.yesNext();
        } else {
            mChangeLoginPasswordView.noNext();
        }
    }

    @Override
    public void getCode(ForgetPasswordCodeRequsetBean bean) {
        changeLoginPassWordModel.getCode(bean);
    }

    @Override
    public void onDestory() {
        changeLoginPassWordModel.onDestroy();
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
                            mChangeLoginPasswordView.hideSendCodeProgress();
                            mTimer.cancel();
                        }
                    });
                } else {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mChangeLoginPasswordView.showSendStatus((CODE_TIME - interval / 1000) + "秒后重发");
                        }
                    });
                }
            }
        }, 0, 1000);
    }

    @Override
    public void onNext(ForgetPasswordNextBean bean) {
        changeLoginPassWordModel.next(bean);

    }


    @Override
    public void onGetCodeError(String msg) {
        mChangeLoginPasswordView.showErrorMsg(msg);
    }

    @Override
    public void onGetCodeNextFailure(String msg) {

    }

    @Override
    public void onGetCodeSuccess(String msg) {
        //发送验证码成功
        mChangeLoginPasswordView.showErrorMsg(msg);

        // 设置验证码发送时间，初始化按钮
        mDataModel.setValue("mSendCodeTime", new Date().getTime());

        initCodeButton();
    }

    @Override
    public void checkCodeError() {
        mChangeLoginPasswordView.showErrorMsg("验证码校验失败");
    }

    @Override
    public void checkCodeSuccess(ForgetPasswordResultBean bean) {
        mChangeLoginPasswordView.toActivity(bean);
    }
}
