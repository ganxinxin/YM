package cn.cbapay.ympay.mvp.model.impl;

import cn.cbapay.ympay.bean.AreaModifyRequestBean;
import cn.cbapay.ympay.bean.ResultBean;
import cn.cbapay.ympay.mvp.model.AreaModifyModel;
import cn.cbapay.ympay.network.HttpUtils;
import cn.cbapay.ympay.utils.ErrorUtil;
import cn.cbapay.ympay.utils.LogUtil;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by YangHongfei on 2016/10/28.
 */
public class AreaModifyModelImpl implements AreaModifyModel {
    private static final String TAG = AreaModifyModelImpl.class.getSimpleName();
    private final CompositeSubscription compositeSubscription = new CompositeSubscription();
    private AreaModifyModel.AreaModifyListener listener;

    public AreaModifyModelImpl(AreaModifyListener listener) {
        this.listener = listener;
    }

    @Override
    public void updatePhoneAddress(AreaModifyRequestBean bean) {
        compositeSubscription
                .add(HttpUtils.updatePhoneAddress(bean)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ResultBean>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                LogUtil.e(TAG, "---onError--->>" + e.getMessage());
                                listener.onAreaModifyFail("修改地区失败");
                            }

                            @Override
                            public void onNext(ResultBean bean) {
                                LogUtil.e(TAG, "--onNext---->>" + bean);
                                if ("1".equals(bean.getResCode())) {
                                    listener.onAreaModifySuccess();
                                } else {
                                    listener.onAreaModifyFail(ErrorUtil.getErrorMsg(bean.getResCode()));
                                }
                            }
                        }));
    }

    @Override
    public void onDestroy() {
        compositeSubscription.clear();
    }
}
