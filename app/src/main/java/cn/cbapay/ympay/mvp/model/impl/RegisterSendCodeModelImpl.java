package cn.cbapay.ympay.mvp.model.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import cn.cbapay.ympay.bean.ForgetPasswordCodeRequsetBean;
import cn.cbapay.ympay.bean.LoginResultBean;
import cn.cbapay.ympay.bean.RegistRequestbean;
import cn.cbapay.ympay.bean.RegisterResultBean;
import cn.cbapay.ympay.bean.ResultBean;
import cn.cbapay.ympay.mvp.model.RegistModel;
import cn.cbapay.ympay.mvp.model.RegisterSendCodeModel;
import cn.cbapay.ympay.network.HttpUtils;
import cn.cbapay.ympay.utils.ErrorUtil;
import cn.cbapay.ympay.utils.LogUtil;
import cn.cbapay.ympay.utils.ShareUtil;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/10/21.
 */
public class RegisterSendCodeModelImpl implements RegisterSendCodeModel {
    private static final String TAG = "RegistModelImpl";

    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    private RegisterSendCodeModel.RegisterSendCodeModellListener mListener;

    public RegisterSendCodeModelImpl(RegisterSendCodeModel.RegisterSendCodeModellListener listener) {
        this.mListener = listener;
    }

    @Override
    public void getCode(ForgetPasswordCodeRequsetBean bean) {
        mCompositeSubscription.add(HttpUtils.registCode(bean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.e(TAG, "--onCompleted---");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "--onError------->>" + e.getMessage());
                        mListener.onGetCodeError("获取验证码失败");
                    }

                    @Override
                    public void onNext(ResultBean resultBean) {
                        LogUtil.e(TAG, "--onNext---" + resultBean);
                        if (resultBean.getResCode().equals("1")) {

                            String resMsg = resultBean.getResMsg();
                            mListener.showMsg(resMsg);
                            mListener.onGetCodeSuccess();
                        }else {
                            mListener.showMsg(ErrorUtil.getErrorMsg(resultBean.getResCode()));
                            ErrorUtil.checkError(resultBean.getResCode());
                        }

                    }
                }));

    }

    @Override
    public void onDestroy() {
        mCompositeSubscription.clear();
    }

    @Override
    public void submit(final RegistRequestbean requestbean) {

        mCompositeSubscription.add(HttpUtils.registSubmit(requestbean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.e(TAG, "--onCompleted---");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "--onError------->>" + e.getMessage());
                        mListener.onGetCodeError("注册失败");
                    }

                    @Override
                    public void onNext(ResultBean resultBean) {
                        LogUtil.e(TAG, "--onNext---" + resultBean);
                        System.out.println(resultBean.getResCode() + "gaowei");
                        if (resultBean.getResCode().equals("1")) {
                            String resMsg = resultBean.getResMsg();
                            mListener.showMsg(resMsg);
                            RegisterResultBean registerResultBean = new Gson().fromJson(resultBean.getJsonData(), RegisterResultBean.class);
                            mListener.registSuccess(registerResultBean);

                        } else {
                            mListener.showMsg(resultBean.getResMsg());
                        }

                    }
                }));

    }

}
