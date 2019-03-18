package cn.cbapay.ympay.mvp.model.impl;

import cn.cbapay.ympay.bean.BankVerifyRequestBean;
import cn.cbapay.ympay.bean.ResultBean;
import cn.cbapay.ympay.mvp.model.SendBankCodeModel;
import cn.cbapay.ympay.network.HttpUtils;
import cn.cbapay.ympay.utils.ErrorUtil;
import cn.cbapay.ympay.utils.LogUtil;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 判断是否设置支付密码，向银行预留手机发送验证码，重新发送
 * Created by icewater on 2016/10/26.
 */
public class SendBankCodeModelImpl implements SendBankCodeModel {
    private static final String TAG = "SendBankCodeModelImpl";

    private SendCodeListener mSendCodeListener;
    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public SendBankCodeModelImpl(SendCodeListener l) {
        this.mSendCodeListener = l;
    }

    @Override
    public void sendCode(BankVerifyRequestBean bean) {
        Subscription o = HttpUtils.getBankVerifyCode(bean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "---onError--->>" + e.getMessage());
                        mSendCodeListener.onSendCodeFailed("-100", "发送验证码失败");
                    }

                    @Override
                    public void onNext(ResultBean resultBean) {
                        LogUtil.e(TAG, "--onNext---->>" + resultBean);
                        if ("1".equals(resultBean.getResCode())) {
                            mSendCodeListener.onSendCodeSuccess();
                        } else {
                            mSendCodeListener.onSendCodeFailed(resultBean.getResCode(), ErrorUtil.getErrorMsg(resultBean.getResCode()));
                        }
                    }
                });
        mCompositeSubscription.add(o);
    }

    @Override
    public void onDestroy() {
        mCompositeSubscription.clear();
    }
}
