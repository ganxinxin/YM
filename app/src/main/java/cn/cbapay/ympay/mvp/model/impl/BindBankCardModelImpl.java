package cn.cbapay.ympay.mvp.model.impl;

import cn.cbapay.ympay.bean.BindBankCardRequestBean;
import cn.cbapay.ympay.bean.ResultBean;
import cn.cbapay.ympay.mvp.model.BindBankCardModel;
import cn.cbapay.ympay.network.HttpUtils;
import cn.cbapay.ympay.utils.ErrorUtil;
import cn.cbapay.ympay.utils.LogUtil;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 绑定银行卡
 * Created by icewater on 2016/10/26.
 */

public class BindBankCardModelImpl implements BindBankCardModel {
    private static final String TAG = "BindBankCardModelImpl";

    private BindBankCardListener mBindBankCardListener;
    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public BindBankCardModelImpl(BindBankCardListener l) {
        this.mBindBankCardListener = l;
    }

    @Override
    public void bindBankCard(BindBankCardRequestBean bean) {
        Subscription o = HttpUtils.toBindBankCard(bean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "---onError--->>" + e.getMessage());
                        mBindBankCardListener.onBindBankCardFailed("绑定银行卡失败");
                    }

                    @Override
                    public void onNext(ResultBean resultBean) {
                        LogUtil.e(TAG, "--onNext---->>" + resultBean);
                        if ("1".equals(resultBean.getResCode())) {
                            mBindBankCardListener.onBindBankCardSuccess();
                        } else {
                            mBindBankCardListener.onBindBankCardFailed(ErrorUtil.getErrorMsg(resultBean.getResCode()));
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
