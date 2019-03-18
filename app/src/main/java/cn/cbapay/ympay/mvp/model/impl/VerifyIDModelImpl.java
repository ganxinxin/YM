package cn.cbapay.ympay.mvp.model.impl;

import com.google.gson.Gson;

import cn.cbapay.ympay.bean.IDVerifyRequestBean;
import cn.cbapay.ympay.bean.IDVerifyRespondBean;
import cn.cbapay.ympay.bean.ResultBean;
import cn.cbapay.ympay.mvp.model.VerifyIDModel;
import cn.cbapay.ympay.network.HttpUtils;
import cn.cbapay.ympay.utils.ErrorUtil;
import cn.cbapay.ympay.utils.LogUtil;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 校验身份证
 * Created by icewater on 2016/10/26.
 */

public class VerifyIDModelImpl implements VerifyIDModel {
    private static final String TAG = "VerifyIDModelImpl";

    private VerifyIDListener mVerifyIDListener;
    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public VerifyIDModelImpl(VerifyIDListener l) {
        this.mVerifyIDListener = l;
    }

    @Override
    public void verifyID(IDVerifyRequestBean bean) {
        Subscription o = HttpUtils.isIdRight(bean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "---onError--->>" + e.getMessage());
                        mVerifyIDListener.onVerifyIDFailed("提交身份证失败");
                    }

                    @Override
                    public void onNext(ResultBean resultBean) {
                        LogUtil.e(TAG, "--onNext---->>" + resultBean);
                        if ("1".equals(resultBean.getResCode())) {
                            String jsonData = resultBean.getJsonData();
                            IDVerifyRespondBean bean = new Gson().fromJson(jsonData, IDVerifyRespondBean.class);
                            mVerifyIDListener.onVerifyIDSuccess(bean);
                        } else {
                            mVerifyIDListener.onVerifyIDFailed(ErrorUtil.getErrorMsg(resultBean.getResCode()));
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
