package cn.cbapay.ympay.mvp.model.impl;

import cn.cbapay.ympay.bean.ResultBean;
import cn.cbapay.ympay.bean.SetPayPasswordRequestBean;
import cn.cbapay.ympay.mvp.model.SetPayPasswordModel;
import cn.cbapay.ympay.network.HttpUtils;
import cn.cbapay.ympay.utils.ErrorUtil;
import cn.cbapay.ympay.utils.LogUtil;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 设置支付密码
 * Created by icewater on 2016/10/26.
 */

public class SetPayPasswordModelImpl implements SetPayPasswordModel {
    private static final String TAG = "SetPayPasswordModelImpl";

    private SetPasswordListener mSetPasswordListener;
    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public SetPayPasswordModelImpl(SetPasswordListener l) {
        this.mSetPasswordListener = l;
    }

    @Override
    public void setPassword(SetPayPasswordRequestBean bean) {
        Subscription o = HttpUtils.updateArpAcCustRel(bean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "---onError--->>" + e.getMessage());
                        mSetPasswordListener.onSetPasswordFailed("设置支付密码失败");
                    }

                    @Override
                    public void onNext(ResultBean resultBean) {
                        LogUtil.e(TAG, "--onNext---->>" + resultBean);
                        if ("1".equals(resultBean.getResCode())) {
                            mSetPasswordListener.onSetPasswordSuccess();
                        } else {
                            mSetPasswordListener.onSetPasswordFailed(ErrorUtil.getErrorMsg(resultBean.getResCode()));
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
