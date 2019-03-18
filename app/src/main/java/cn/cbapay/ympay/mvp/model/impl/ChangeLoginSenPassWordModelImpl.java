package cn.cbapay.ympay.mvp.model.impl;

import cn.cbapay.ympay.bean.ChangeLoginPassWordBean;
import cn.cbapay.ympay.bean.ResultBean;
import cn.cbapay.ympay.mvp.model.ChangeLoginSenPassWordModel;
import cn.cbapay.ympay.network.HttpUtils;
import cn.cbapay.ympay.utils.ErrorUtil;
import cn.cbapay.ympay.utils.LogUtil;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/10/27.
 */
public class ChangeLoginSenPassWordModelImpl implements ChangeLoginSenPassWordModel {

    private ChangeLoginSenPassWordModel.ChangeLoginSenPassWordListener listener;
    private static final String TAG = "ChangeLoginSenPassWordModelImpl";

    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public ChangeLoginSenPassWordModelImpl(ChangeLoginSenPassWordModel.ChangeLoginSenPassWordListener listener) {
        this.listener = listener;
    }

    @Override
    public void requsetPassword(ChangeLoginPassWordBean bean) {
        mCompositeSubscription.add(HttpUtils.changeLoginPassword(bean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "--onError------->>" + e.getMessage());
                        listener.showMsg("修改登陆密码失败");
                    }

                    @Override
                    public void onNext(ResultBean resultBean) {
                        LogUtil.e(TAG, "--onNext---" + resultBean);
                        System.out.println(resultBean.getResCode());
                        if (resultBean.getResCode().equals("1")) {
                            listener.showMsg(resultBean.getResMsg());
                            listener.changeSuccess();
                        } else {
                            listener.showMsg(ErrorUtil.getErrorMsg(resultBean.getResCode()));
                            ErrorUtil.checkError(resultBean.getResCode());
                        }

                    }
                }));
    }
}
