package cn.cbapay.ympay.mvp.presenter.impl;

import android.os.Handler;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import cn.cbapay.ympay.bean.ForgetPasswordCodeRequsetBean;
import cn.cbapay.ympay.bean.ForgetPasswordNextBean;
import cn.cbapay.ympay.bean.ForgetPasswordResultBean;
import cn.cbapay.ympay.mvp.model.ChangePayPasswordModel;
import cn.cbapay.ympay.mvp.model.DataModel;
import cn.cbapay.ympay.mvp.model.ForgetPasswordModel;
import cn.cbapay.ympay.mvp.model.impl.ChangePayPasswordModelImpl;
import cn.cbapay.ympay.mvp.model.impl.SharedPreferencesModel;
import cn.cbapay.ympay.mvp.presenter.ChangeLoginPasswordPresenter;
import cn.cbapay.ympay.mvp.presenter.ChangePayPasswordPresenter;

/**
 * Created by Administrator on 2016/9/23.
 */
public class ChangePayPasswordPresenterImpl implements ChangePayPasswordPresenter,ChangePayPasswordModel.ChangePayPasswordModelListener {
    private ChangePayPasswordView mChangePayPasswordView;
    private ChangePayPasswordModel changePayPasswordModel;

    private Timer mTimer;
    private long interval; // 时间间隔;
    private static final int CODE_TIME = 60;
    private Handler mHandler = new Handler();
    private DataModel mDataModel = new SharedPreferencesModel();
    public ChangePayPasswordPresenterImpl(ChangePayPasswordView changePayPasswordView)  {
        this.mChangePayPasswordView = changePayPasswordView;
        changePayPasswordModel = new ChangePayPasswordModelImpl(this);
    }

    @Override
    public void showCodeLength(int code) {
        if(code == 6){
            mChangePayPasswordView.yesNext();
        }else {
            mChangePayPasswordView.noNext();
        }
    }

    @Override
    public void getCode(ForgetPasswordCodeRequsetBean bean) {
        changePayPasswordModel.getCode(bean);
    }

    @Override
    public void onDestory() {
        changePayPasswordModel.onDestroy();
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
                            mChangePayPasswordView.hideSendCodeProgress();
                            mTimer.cancel();
                        }
                    });
                } else {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mChangePayPasswordView.showSendStatus((CODE_TIME - interval / 1000) + "秒后重发");
                        }
                    });
                }
            }
        }, 0, 1000);
    }

    @Override
    public void onNext(ForgetPasswordNextBean bean) {
        changePayPasswordModel.next(bean);
    }

    @Override
    public void onGetCodeError(String msg) {
        mChangePayPasswordView.showErrorMsg(msg);
    }

    @Override
    public void onGetCodeNextFailure(String msg) {

    }

    @Override
    public void onGetCodeSuccess(String msg) {
        //发送验证码成功
        mChangePayPasswordView.showErrorMsg(msg);

        // 设置验证码发送时间，初始化按钮
        mDataModel.setValue("mSendCodeTime", new Date().getTime());

        initCodeButton();
    }

    @Override
    public void checkCodeError() {
        mChangePayPasswordView.showErrorMsg("验证码校验失败");
    }

    @Override
    public void checkCodeSuccess(ForgetPasswordResultBean bean) {
        mChangePayPasswordView.toActivity(bean);
    }
}
