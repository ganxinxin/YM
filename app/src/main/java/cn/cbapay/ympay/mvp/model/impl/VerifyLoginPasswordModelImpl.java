package cn.cbapay.ympay.mvp.model.impl;

import com.google.gson.Gson;

import cn.cbapay.ympay.bean.LoginPasswordVerifyRequestBean;
import cn.cbapay.ympay.bean.LoginPasswordVerifyResponseBean;
import cn.cbapay.ympay.bean.ResultBean;
import cn.cbapay.ympay.mvp.model.VerifyLoginPasswordModel;
import cn.cbapay.ympay.network.HttpUtils;
import cn.cbapay.ympay.utils.ErrorUtil;
import cn.cbapay.ympay.utils.LogUtil;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 未实名时校验登录密码
 * Created by icewater on 2016/10/26.
 */

public class VerifyLoginPasswordModelImpl implements VerifyLoginPasswordModel {
    private static final String TAG = "VerifyLoginPasswordModelImpl";

    private VerifyPasswordListener mVerifyPasswordListener;
    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public VerifyLoginPasswordModelImpl(VerifyPasswordListener l) {
        this.mVerifyPasswordListener = l;
    }

    @Override
    public void verifyPassword(LoginPasswordVerifyRequestBean bean) {
        Subscription o = HttpUtils.findPwd(bean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "---onError--->>" + e.getMessage());
                        mVerifyPasswordListener.onVerifyPasswordFailed("-100", "提交登录密码失败");
                    }

                    @Override
                    public void onNext(ResultBean resultBean) {
                        LogUtil.e(TAG, "--onNext---->>" + resultBean);
                        if ("1".equals(resultBean.getResCode())) {
                            LoginPasswordVerifyResponseBean b = new Gson().fromJson(resultBean.getJsonData(), LoginPasswordVerifyResponseBean.class);
                            mVerifyPasswordListener.onVerifyPasswordSuccess(b);
                        } else {
                            mVerifyPasswordListener.onVerifyPasswordFailed(resultBean.getResCode(), ErrorUtil.getErrorMsg(resultBean.getResCode()));
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
