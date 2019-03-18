package cn.cbapay.ympay.mvp.model.impl;

import com.google.gson.Gson;

import cn.cbapay.ympay.bean.ForgetPasswordCodeRequsetBean;
import cn.cbapay.ympay.bean.ForgetPasswordNextBean;
import cn.cbapay.ympay.bean.ForgetPasswordResultBean;
import cn.cbapay.ympay.bean.LoginResultBean;
import cn.cbapay.ympay.bean.ResultBean;
import cn.cbapay.ympay.mvp.model.ForgetPasswordModel;
import cn.cbapay.ympay.network.HttpUtils;
import cn.cbapay.ympay.utils.ErrorUtil;
import cn.cbapay.ympay.utils.LogUtil;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/10/20.
 */
public class ForgetPasswordModeImpl implements ForgetPasswordModel {

    private static final String TAG = "ForgetPasswordModeImpl";

    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    private ForgetPasswordModel.ForgetPasswordModelListener mListener;

    public ForgetPasswordModeImpl(ForgetPasswordModelListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void getCode(ForgetPasswordCodeRequsetBean bean) {
        mCompositeSubscription.add(HttpUtils.getForgetPasswordCode(bean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "--onError------->>" + e.getMessage());
                        mListener.onGetCodeError("发送验证码失败");
                    }

                    @Override
                    public void onNext(ResultBean resultBean) {
                        LogUtil.e(TAG, "--onNext---" + resultBean);
                        System.out.println(resultBean.getResCode());
                        if (resultBean.getResCode().equals("1")) {
                            mListener.onGetCodeSuccess(resultBean.getResMsg());
                        } else {
                            mListener.onGetCodeError(resultBean.getResMsg());
                        }

                    }
                }));
    }

    @Override
    public void next(ForgetPasswordNextBean bean) {
        mCompositeSubscription.add(HttpUtils.forgetPasswordNext(bean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.e(TAG, "--onCompleted---");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "--onError------->>" + e.getMessage());
                        mListener.onGetCodeError("验证码校验失败，请重试");
                    }

                    @Override
                    public void onNext(ResultBean resultBean) {

                        LogUtil.e(TAG, "--onNext---" + resultBean);
                        System.out.println(resultBean.getResCode() + "gaowei");
                        if (resultBean.getResCode().equals("1")) {
                            ForgetPasswordResultBean forgetPasswordResultBean = new Gson().fromJson(resultBean.getJsonData(), ForgetPasswordResultBean.class);
                            mListener.checkCodeSuccess(forgetPasswordResultBean);
                        } else {
                            mListener.onGetCodeError(ErrorUtil.getErrorMsg(resultBean.getResCode()));
                            ErrorUtil.checkError(resultBean.getResCode());
                        }

                    }
                }));

    }

    @Override
    public void onDestroy() {
        mCompositeSubscription.clear();
    }
}
