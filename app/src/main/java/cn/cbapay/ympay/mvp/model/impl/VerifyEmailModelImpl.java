package cn.cbapay.ympay.mvp.model.impl;

import cn.cbapay.ympay.bean.EmailVerifyRequestBean;
import cn.cbapay.ympay.bean.ResultBean;
import cn.cbapay.ympay.mvp.model.VerifyEmailModel;
import cn.cbapay.ympay.network.HttpUtils;
import cn.cbapay.ympay.utils.ErrorUtil;
import cn.cbapay.ympay.utils.LogUtil;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by YangHongfei on 2016/10/26.
 */
public class VerifyEmailModelImpl implements VerifyEmailModel {
    private static final String TAG = VerifyEmailModelImpl.class.getSimpleName();
    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();
    private VerifyEmailModel.VerifyEmailListener mListener;

    public VerifyEmailModelImpl(VerifyEmailListener listener) {
        this.mListener = listener;
    }


    @Override
    public void verifyCode(EmailVerifyRequestBean bean) {
        mCompositeSubscription.add(HttpUtils.verificationEmailCode(bean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "---onError--->>" + e.getMessage());
                        mListener.OnVerifyEmailFail("获取验证码失败，请重试。");
                    }

                    @Override
                    public void onNext(ResultBean bean) {
                        LogUtil.e(TAG, "--onNext----->>" + bean);
                        if ("1".equals(bean.getResCode())) {
                            mListener.OnVerifyEmailSuccess();
                        } else {
                            mListener.OnVerifyEmailFail(ErrorUtil.getErrorMsg(bean.getResCode()));
                        }
                    }
                }));

    }

    @Override
    public void onDestroy() {
        mCompositeSubscription.clear();
    }
}
