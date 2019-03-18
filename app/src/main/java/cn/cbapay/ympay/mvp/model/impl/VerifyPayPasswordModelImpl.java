package cn.cbapay.ympay.mvp.model.impl;

import com.google.gson.Gson;

import cn.cbapay.ympay.bean.PayPasswordVerifyRequestBean;
import cn.cbapay.ympay.bean.PayPasswordVerifyResponseBean;
import cn.cbapay.ympay.bean.ResultBean;
import cn.cbapay.ympay.mvp.model.VerifyPayPasswordModel;
import cn.cbapay.ympay.network.HttpUtils;
import cn.cbapay.ympay.utils.LogUtil;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 校验支付密码
 * Created by icewater on 2016/10/26.
 */

public class VerifyPayPasswordModelImpl implements VerifyPayPasswordModel {
    private static final String TAG = "VerifyPayPasswordModelImpl";

    private VerifyPasswordListener mVerifyPasswordListener;
    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public VerifyPayPasswordModelImpl(VerifyPasswordListener l) {
        this.mVerifyPasswordListener = l;
    }

    @Override
    public void verifyPassword(PayPasswordVerifyRequestBean bean) {
        Subscription o = HttpUtils.payPwd(bean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "---onError--->>" + e.getMessage());
                        mVerifyPasswordListener.onVerifyPasswordFailed("-100", "提交支付密码失败");
                    }

                    @Override
                    public void onNext(ResultBean resultBean) {
                        LogUtil.e(TAG, "--onNext---->>" + resultBean);
                        if ("1".equals(resultBean.getResCode())) {
                            PayPasswordVerifyResponseBean b = new Gson().fromJson(resultBean.getJsonData(), PayPasswordVerifyResponseBean.class);
                            mVerifyPasswordListener.onVerifyPasswordSuccess(b);
                        } else {
                            mVerifyPasswordListener.onVerifyPasswordFailed(resultBean.getResCode(), resultBean.getResMsg());
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
