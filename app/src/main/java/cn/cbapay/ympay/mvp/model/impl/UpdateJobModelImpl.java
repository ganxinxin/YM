package cn.cbapay.ympay.mvp.model.impl;

import cn.cbapay.ympay.bean.CareerUpdateRequestBean;
import cn.cbapay.ympay.bean.ResultBean;
import cn.cbapay.ympay.mvp.model.UpdateJobModel;
import cn.cbapay.ympay.network.HttpUtils;
import cn.cbapay.ympay.utils.ErrorUtil;
import cn.cbapay.ympay.utils.LogUtil;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by YangHongfei on 2016/10/25.
 */
public class UpdateJobModelImpl implements UpdateJobModel {
    private static final String TAG = UpdateJobModelImpl.class.getSimpleName();
    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();
    private UpdateJobModel.OnUpdateJobListener mListener;
    public UpdateJobModelImpl(OnUpdateJobListener listener){
        mListener = listener;
    }

    @Override
    public void updateJob(CareerUpdateRequestBean bean) {
        mCompositeSubscription.add(HttpUtils.updateUsrJob(bean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "---onError--->>" + e.getMessage());
                        mListener.onUpdateJobFail("更新职业失败");
                    }

                    @Override
                    public void onNext(ResultBean bean) {
                        LogUtil.e(TAG, "--onNext---->>" + bean);
                        if ("1".equals(bean.getResCode())) {
                            mListener.onUpdateJobSuccess();
                        } else {
                            mListener.onUpdateJobFail(ErrorUtil.getErrorMsg(bean.getResCode()));
                        }
                    }
                }));
    }

    @Override
    public void onDestroy() {
        mCompositeSubscription.clear();
    }
}
