package cn.cbapay.ympay.mvp.presenter.impl;

import android.os.Handler;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import cn.cbapay.ympay.bean.CodeRequestBean;
import cn.cbapay.ympay.mvp.model.DataModel;
import cn.cbapay.ympay.mvp.model.ValidateCodeModel;
import cn.cbapay.ympay.mvp.model.impl.SharedPreferencesModel;
import cn.cbapay.ympay.mvp.model.impl.ValidateCodeModelImpl;
import cn.cbapay.ympay.mvp.presenter.ValidateCodePresenter;

public class ValidateCodePresenterImpl implements ValidateCodePresenter, ValidateCodeModel.OnGetCodeListener {

    private static final String TAG = "ValidateCodePresenterImpl";

    private ValidateCodeView mValidateCodeView;
    private ValidateCodeModel mValidateCodeModel;

    private Timer mTimer;
    private long interval; // 时间间隔;
    private static final int CODE_TIME = 60;
    private Handler mHandler = new Handler();
    private DataModel mDataModel = new SharedPreferencesModel();

    public ValidateCodePresenterImpl(ValidateCodeView ValidateCodeView) {
        this.mValidateCodeView = ValidateCodeView;
        this.mValidateCodeModel = new ValidateCodeModelImpl(this);
    }

    @Override
    public void getValidateCode(CodeRequestBean bean) {
        mValidateCodeView.showSendCodeProgress("正在发送验证码");

        // 设置验证码发送时间，初始化按钮
        mDataModel.setValue("mSendCodeTime", new Date().getTime());
        initCodeButton();
        mValidateCodeModel.getValidateCode(bean);
    }

    @Override
    public void startCountdown() {
        // 设置验证码发送时间，初始化按钮
        mDataModel.setValue("mSendCodeTime", new Date().getTime());
        initCodeButton();
    }

    @Override
    public void onGetCodeError(String msg) {
        mValidateCodeView.showGetCodeErrorMsg(msg);
        mDataModel.setValue("mSendCodeTime", 0);
    }

    @Override
    public void onGetCodeNextFailure(String msg) {
        mValidateCodeView.showGetCodeErrorMsg(msg);
    }

    @Override
    public void onGetCodeSuccess() {
        mValidateCodeView.showSendCodeProgress("验证码发送成功");
    }

    /**
     * 初始化发送验证码按钮，共用同一个发送时间
     */
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
                            mValidateCodeView.hideSendCodeProgress();
                            mTimer.cancel();
                        }
                    });
                } else {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mValidateCodeView.showSendStatus((CODE_TIME - interval / 1000) + "秒后重发");
                        }
                    });
                }
            }
        }, 0, 1000);
    }

    @Override
    public void onDestroy() {
        mValidateCodeModel.onDestroy();
    }
}
