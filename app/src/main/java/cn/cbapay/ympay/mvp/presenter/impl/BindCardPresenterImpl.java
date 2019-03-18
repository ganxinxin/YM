package cn.cbapay.ympay.mvp.presenter.impl;

import android.os.Handler;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import cn.cbapay.ympay.app.MyApplication;
import cn.cbapay.ympay.bean.BankVerifyRequestBean;
import cn.cbapay.ympay.bean.BindBankCardRequestBean;
import cn.cbapay.ympay.bean.SetPayPasswordRequestBean;
import cn.cbapay.ympay.mvp.model.BindBankCardModel;
import cn.cbapay.ympay.mvp.model.DataModel;
import cn.cbapay.ympay.mvp.model.SendBankCodeModel;
import cn.cbapay.ympay.mvp.model.SetPayPasswordModel;
import cn.cbapay.ympay.mvp.model.impl.BindBankCardModelImpl;
import cn.cbapay.ympay.mvp.model.impl.SendBankCodeModelImpl;
import cn.cbapay.ympay.mvp.model.impl.SetPayPasswordModelImpl;
import cn.cbapay.ympay.mvp.model.impl.SharedPreferencesModel;
import cn.cbapay.ympay.mvp.presenter.BindCardPresenter;
import cn.cbapay.ympay.utils.ShareUtil;

/**
 * 绑定银行卡
 * Created by icewater on 2016/11/1.
 */

public class BindCardPresenterImpl implements BindCardPresenter,
        SendBankCodeModel.SendCodeListener,
        SetPayPasswordModel.SetPasswordListener,
        BindBankCardModel.BindBankCardListener {
    private static final String TAG = "BindCardPresenterImpl";

    private SendBankCodeModel mSendBankCodeModel;
    private SetPayPasswordModel mSetPayPasswordModel;
    private BindBankCardModel mBindBankCardModel;
    private BindCardView mAccountInfoView;

    private Timer mTimer;
    private long interval; // 时间间隔;
    private static final int CODE_TIME = 60;
    private Handler mHandler = new Handler();
    private DataModel mDataModel = new SharedPreferencesModel();

    BindBankCardRequestBean bindInfo;

    public BindCardPresenterImpl(BindCardView v) {
        this.mAccountInfoView = v;
        this.mSendBankCodeModel = new SendBankCodeModelImpl(this);
        this.mSetPayPasswordModel = new SetPayPasswordModelImpl(this);
        this.mBindBankCardModel = new BindBankCardModelImpl(this);
    }

    /**
     * 设置卡信息
     *
     * @param bean 卡信息
     */
    @Override
    public void setCardInfo(BindBankCardRequestBean bean) {
        bindInfo = bean;
    }

    /**
     * 设置支付密码
     *
     * @param password 密码
     */
    @Override
    public void setPayPassword(String password) {
        SetPayPasswordRequestBean bean = new SetPayPasswordRequestBean();
        bean.setToken(ShareUtil.getValue("token", MyApplication.getContext()));
        bean.setNewUserPWD(password);
        bean.setUserPWD(password);
        mSetPayPasswordModel.setPassword(bean);
    }

    /**
     * 重新发送验证码
     */
    @Override
    public void sendVerifyCode() {
        BankVerifyRequestBean bean = new BankVerifyRequestBean();
        bean.setToken(ShareUtil.getValue("token", MyApplication.getContext()));
        bean.setBankPreMobile(bindInfo.getBankPreMobile());
        mSendBankCodeModel.sendCode(bean);
        startCountdown();
    }

    /**
     * 绑定银行卡
     *
     * @param code 验证码
     */
    @Override
    public void bind(String code) {
        bindInfo.setValidateCode(code);
        mBindBankCardModel.bindBankCard(bindInfo);
    }

    /**
     * 初始化验证码按钮
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
                            mAccountInfoView.hideSendCodeProgress();
                            mTimer.cancel();
                        }
                    });
                } else {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mAccountInfoView.showSendStatus((CODE_TIME - interval / 1000) + "秒后重发");
                        }
                    });
                }
            }
        }, 0, 1000);
    }

    /**
     * 验证码开始倒计时
     */
    @Override
    public void startCountdown() {
        // 设置验证码发送时间，初始化按钮
        mDataModel.setValue("mSendCodeTime", new Date().getTime());
        initCodeButton();
    }

    @Override
    public void onDestroy() {
        mBindBankCardModel.onDestroy();
        mSendBankCodeModel.onDestroy();
        mSetPayPasswordModel.onDestroy();
    }

    /**
     * 发送验证码成功
     */
    @Override
    public void onSendCodeSuccess() {
        mAccountInfoView.sendVerifyCodeSucc();
    }

    /**
     * 发送验证码失败
     *
     * @param code 错误码
     * @param msg  错误信息
     */
    @Override
    public void onSendCodeFailed(String code, String msg) {
        mDataModel.setValue("mSendCodeTime", 0);
        if ("48".equals(code)) {
            // 未设置支付密码
            mAccountInfoView.setPayPasswordFail();
        }
        mAccountInfoView.sendVerifyCodeFail(msg);
    }

    /**
     * 设置支付密码成功  发送验证码
     */
    @Override
    public void onSetPasswordSuccess() {
        mAccountInfoView.setPayPasswordSucc();
        sendVerifyCode();
    }

    /**
     * 设置支付密码失败
     *
     * @param msg 失败信息
     */
    @Override
    public void onSetPasswordFailed(String msg) {
        mAccountInfoView.showMessage(msg);
        mAccountInfoView.setPayPasswordFail();
    }

    /**
     * 绑卡成功
     */
    @Override
    public void onBindBankCardSuccess() {
        mAccountInfoView.bindSuccess();
    }

    /**
     * 绑卡失败
     *
     * @param msg 失败信息
     */
    @Override
    public void onBindBankCardFailed(String msg) {
        mAccountInfoView.bindFail(msg);
    }
}
