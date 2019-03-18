package cn.cbapay.ympay.mvp.model.impl;

import cn.cbapay.ympay.bean.RequestBean;
import cn.cbapay.ympay.bean.ResultBean;
import cn.cbapay.ympay.mvp.model.SendOriginalPhoneCodeModel;
import cn.cbapay.ympay.network.HttpUtils;
import cn.cbapay.ympay.utils.ErrorUtil;
import cn.cbapay.ympay.utils.LogUtil;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 向原手机号发送验证码
 * Created by icewater on 2016/10/26.
 */

public class SendOriginalPhoneCodeModelImpl implements SendOriginalPhoneCodeModel {
    private static final String TAG = "SendOriginalPhoneCodeModelImpl";

    private SendCodeListener mSendCodeListener;
    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public SendOriginalPhoneCodeModelImpl(SendCodeListener l) {
        this.mSendCodeListener = l;
    }

    @Override
    public void sendCode(RequestBean bean) {
        Subscription o = HttpUtils.sendMCode(bean)
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
