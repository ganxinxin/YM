package cn.cbapay.ympay.mvp.model.impl;

import com.google.gson.Gson;

import cn.cbapay.ympay.bean.OriginalPhoneVerifyRequestBean;
import cn.cbapay.ympay.bean.OriginalPhoneVerifyResponseBean;
import cn.cbapay.ympay.bean.ResultBean;
import cn.cbapay.ympay.mvp.model.VerifyOriginalPhoneCodeModel;
import cn.cbapay.ympay.network.HttpUtils;
import cn.cbapay.ympay.utils.ErrorUtil;
import cn.cbapay.ympay.utils.LogUtil;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 校验原手机号的验证码
 * Created by icewater on 2016/10/26.
 */

public class VerifyOriginalPhoneCodeModelImpl implements VerifyOriginalPhoneCodeModel {
    private static final String TAG = "VerifyOriginalPhoneCodeModelImpl";

    private VerifyCodeListener mVerifyCodeListener;
    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public VerifyOriginalPhoneCodeModelImpl(VerifyCodeListener l) {
        this.mVerifyCodeListener = l;
    }

    @Override
    public void verifyCode(OriginalPhoneVerifyRequestBean bean) {
        Subscription o = HttpUtils.matching(bean)
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
                            OriginalPhoneVerifyResponseBean b = new Gson().fromJson(resultBean.getJsonData(), OriginalPhoneVerifyResponseBean.class);
                            mVerifyCodeListener.onVerifyCodeSuccess(b);
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
