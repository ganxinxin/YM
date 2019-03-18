package cn.cbapay.ympay.mvp.model.impl;

import com.google.gson.Gson;

import cn.cbapay.ympay.bean.GetNewPhoneVerifyCodeRequestBean;
import cn.cbapay.ympay.bean.GetNewPhoneVerifyCodeResponseBean;
import cn.cbapay.ympay.bean.ResultBean;
import cn.cbapay.ympay.mvp.model.SendPhoneEmailCodeModel;
import cn.cbapay.ympay.network.HttpUtils;
import cn.cbapay.ympay.utils.ErrorUtil;
import cn.cbapay.ympay.utils.LogUtil;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 向新手机号或者邮箱发送验证码
 * Created by icewater on 2016/10/26.
 */

public class SendPhoneEmailCodeModelImpl implements SendPhoneEmailCodeModel {
    private static final String TAG = "SendPhoneEmailCodeModelImpl";

    private SendCodeListener mSendCodeListener;
    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public SendPhoneEmailCodeModelImpl(SendCodeListener l){
        this.mSendCodeListener = l;
    }

    @Override
    public void sendCode(GetNewPhoneVerifyCodeRequestBean bean) {
        Subscription o = HttpUtils.sendCode(bean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "---onError--->>" + e.getMessage());
                        mSendCodeListener.onSendCodeFailed("发送验证码失败");
                    }

                    @Override
                    public void onNext(ResultBean resultBean) {
                        LogUtil.e(TAG, "--onNext---->>" + resultBean);
                        if ("1".equals(resultBean.getResCode())) {
                            GetNewPhoneVerifyCodeResponseBean b = new Gson().fromJson(resultBean.getJsonData(), GetNewPhoneVerifyCodeResponseBean.class);
                            mSendCodeListener.onSendCodeSuccess(b);
                        }else{
                            mSendCodeListener.onSendCodeFailed(ErrorUtil.getErrorMsg(resultBean.getResCode()));
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
