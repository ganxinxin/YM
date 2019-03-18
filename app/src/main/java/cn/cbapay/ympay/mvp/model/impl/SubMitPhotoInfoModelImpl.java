package cn.cbapay.ympay.mvp.model.impl;

import cn.cbapay.ympay.bean.ResultBean;
import cn.cbapay.ympay.bean.SubPhotoBean;
import cn.cbapay.ympay.mvp.model.SubMitPhotoInfoModel;
import cn.cbapay.ympay.network.HttpUtils;
import cn.cbapay.ympay.utils.ErrorUtil;
import cn.cbapay.ympay.utils.LogUtil;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/10/27.
 */

public class SubMitPhotoInfoModelImpl implements SubMitPhotoInfoModel {
    private static final String TAG = "SubMitPhotoInfoModelImpl";

    private SubMitPhotoInfoModel.SubMitListener mSubMitListener;
    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public SubMitPhotoInfoModelImpl(SubMitPhotoInfoModel.SubMitListener mSubMitListener) {
        this.mSubMitListener = mSubMitListener;
    }

    @Override
    public void subMitPhoto(SubPhotoBean bean) {
        Subscription o = HttpUtils.subPhoto(bean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "---onError--->>" + e.getMessage());
                        mSubMitListener.subMitFiled("获取客户信息失败");
                    }

                    @Override
                    public void onNext(ResultBean resultBean) {
                        LogUtil.e(TAG, "--onNext---->>" + resultBean);
                        if ("1".equals(resultBean.getResCode())) {
                            mSubMitListener.subMitSucceed(resultBean.getResMsg());
                            }else{
                            mSubMitListener.subMitFiled(ErrorUtil.getErrorMsg(resultBean.getResCode()));
                        }
                    }
                });
        mCompositeSubscription.add(o);

    }

    @Override
    public void Destroy() {
        mCompositeSubscription.clear();
    }
}
