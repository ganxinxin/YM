package cn.cbapay.ympay.mvp.model.impl;

import cn.cbapay.ympay.bean.NewPhoneVerifyRequestBean;
import cn.cbapay.ympay.bean.ResultBean;
import cn.cbapay.ympay.mvp.model.VerifyNewPhoneCodeModel;
import cn.cbapay.ympay.network.HttpUtils;
import cn.cbapay.ympay.utils.ErrorUtil;
import cn.cbapay.ympay.utils.LogUtil;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 未实名时,校验新手机号验证码
 * Created by icewater on 2016/10/26.
 */

public class VerifyNewPhoneCode2ModelImpl implements VerifyNewPhoneCodeModel {
    private static final String TAG = "VerifyNewPhoneCode2ModelImpl";

    private VerifyCodeListener mVerifyCodeListener;
    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public VerifyNewPhoneCode2ModelImpl(VerifyCodeListener l) {
        this.mVerifyCodeListener = l;
    }

    @Override
    public void verifyCode(NewPhoneVerifyRequestBean bean) {
        Subscription o = HttpUtils.updateM(bean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "---onError--->>" + e.getMessage());
                        mVerifyCodeListener.onVerifyCodeFailed("提交验证码失败");
                    }

                    @Override
                    public void onNext(ResultBean resultBean) {
                        LogUtil.e(TAG, "--onNext---->>" + resultBean);
                        if ("1".equals(resultBean.getResCode())) {
                            mVerifyCodeListener.onVerifyCodeSuccess();
                        } else {
                            mVerifyCodeListener.onVerifyCodeFailed(ErrorUtil.getErrorMsg(resultBean.getResCode()));
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
