package cn.cbapay.ympay.mvp.model.impl;

import cn.cbapay.ympay.bean.ForgetPasswordCodeRequsetBean;
import cn.cbapay.ympay.bean.ResultBean;
import cn.cbapay.ympay.mvp.model.ForgetPasswordModel;
import cn.cbapay.ympay.mvp.model.RegistModel;
import cn.cbapay.ympay.network.HttpUtils;
import cn.cbapay.ympay.utils.ErrorUtil;
import cn.cbapay.ympay.utils.LogUtil;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/10/21.
 */
public class RegistModelImpl implements RegistModel {

    private static final String TAG = "RegistModelImpl";

    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    private RegistModel.RegistModelListener mListener;

    public RegistModelImpl(RegistModelListener listener) {
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
                        LogUtil.e(TAG, "--onNext---");
                        System.out.println(resultBean.getResCode() + "gaowei");
                        if (resultBean.getResCode().equals("1") || resultBean.getResCode().equals("36")) {
                            mListener.onGetCodeSuccess();
                            String resMsg = resultBean.getResMsg();
                            mListener.showMsg(resMsg);
                        } else if (resultBean.getResCode().equals("13")) {
                            mListener.alreadyRegist();
                        } else {
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

}
