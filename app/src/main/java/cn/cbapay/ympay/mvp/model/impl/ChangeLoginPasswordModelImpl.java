package cn.cbapay.ympay.mvp.model.impl;

import com.google.gson.Gson;

import cn.cbapay.ympay.bean.ForgetPasswordCodeRequsetBean;
import cn.cbapay.ympay.bean.ForgetPasswordNextBean;
import cn.cbapay.ympay.bean.ForgetPasswordResultBean;
import cn.cbapay.ympay.bean.ResultBean;
import cn.cbapay.ympay.mvp.model.ChangeLoginPassWordModel;
import cn.cbapay.ympay.network.HttpUtils;
import cn.cbapay.ympay.utils.ErrorUtil;
import cn.cbapay.ympay.utils.LogUtil;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/10/27.
 */
public class ChangeLoginPasswordModelImpl implements ChangeLoginPassWordModel {
    private static final String TAG = "ChangeLoginPasswordModelImpl";

    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();
    private ChangeLoginPassWordModel.ChangeLoginPassWordListener listener;

    public ChangeLoginPasswordModelImpl(ChangeLoginPassWordModel.ChangeLoginPassWordListener listener) {
        this.listener = listener;
    }

    @Override
    public void getCode(ForgetPasswordCodeRequsetBean bean) {
        mCompositeSubscription.add(HttpUtils.changeLoginPasswordCode(bean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "--onError------->>" + e.getMessage());
                        listener.onGetCodeError("发送验证码失败");
                    }

                    @Override
                    public void onNext(ResultBean resultBean) {
                        LogUtil.e(TAG, "--onNext---" + resultBean);
                        System.out.println(resultBean.getResCode());
                        if (resultBean.getResCode().equals("1")) {
                            listener.onGetCodeSuccess(resultBean.getResMsg());
                        } else {
                            listener.onGetCodeError(resultBean.getResMsg());
                        }

                    }
                }));
    }

    @Override
    public void next(ForgetPasswordNextBean bean) {
        mCompositeSubscription.add(HttpUtils.changeLoginPasswordNext(bean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.e(TAG, "--onCompleted---");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "--onError------->>" + e.getMessage());
                        listener.onGetCodeError("验证码校验失败，请重试");
                    }

                    @Override
                    public void onNext(ResultBean resultBean) {

                        LogUtil.e(TAG, "--onNext---" + resultBean);
                        System.out.println(resultBean.getResCode() + "gaowei");
                        if (resultBean.getResCode().equals("1")) {
                            ForgetPasswordResultBean forgetPasswordResultBean = new Gson().fromJson(resultBean.getJsonData(), ForgetPasswordResultBean.class);
                            listener.checkCodeSuccess(forgetPasswordResultBean);
                        } else {
                            listener.onGetCodeError(ErrorUtil.getErrorMsg(resultBean.getResCode()));
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
