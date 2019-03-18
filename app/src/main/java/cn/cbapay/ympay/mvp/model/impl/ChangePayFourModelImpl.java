package cn.cbapay.ympay.mvp.model.impl;

import cn.cbapay.ympay.bean.ChangePayPswRequestBean;
import cn.cbapay.ympay.bean.ResultBean;
import cn.cbapay.ympay.mvp.model.ChangePayFourModel;
import cn.cbapay.ympay.network.HttpUtils;
import cn.cbapay.ympay.utils.ErrorUtil;
import cn.cbapay.ympay.utils.LogUtil;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/10/31.
 */
public class ChangePayFourModelImpl implements ChangePayFourModel {
    private static final String TAG = "ChangePayFourModelImpl";

    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    private ChangePayFourModel.ChangePayFourListener listener;
    public ChangePayFourModelImpl(ChangePayFourModel.ChangePayFourListener listener) {
        this.listener = listener;
    }

    @Override
    public void changePsw(ChangePayPswRequestBean bean) {
        mCompositeSubscription.add(HttpUtils.changePayPsw(bean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "--onError------->>" + e.getMessage());
                        listener.showMessg("修改登录密码失败");
                    }

                    @Override
                    public void onNext(ResultBean resultBean) {
                        LogUtil.e(TAG, "--onNext---" + resultBean);
                        System.out.println(resultBean.getResCode());
                        if (resultBean.getResCode().equals("1")) {
                            listener.showMessg(resultBean.getResMsg());
                            listener.changeSuccess();
                        } else {
                            listener.showMessg(ErrorUtil.getErrorMsg(resultBean.getResCode()));
                            ErrorUtil.checkError(resultBean.getResCode());
                        }
                    }
                }));
    }

    @Override
    public void onDestroy() {

    }
}
