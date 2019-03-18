package cn.cbapay.ympay.mvp.model.impl;

import cn.cbapay.ympay.bean.CodeRequestBean;
import cn.cbapay.ympay.mvp.model.ValidateCodeModel;
import rx.subscriptions.CompositeSubscription;

public class ValidateCodeModelImpl implements ValidateCodeModel {

    private static final String TAG = "ValidateCodeModelImpl";

    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    private ValidateCodeModel.OnGetCodeListener mListener;

    public ValidateCodeModelImpl(OnGetCodeListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void getValidateCode(CodeRequestBean bean) {
//        mCompositeSubscription.add(HttpUtils.getValidateCode(bean)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<ResultBean>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        LogUtil.e(TAG, "---onError--->>" + e.getMessage());
//                        mListener.onGetCodeError("获取验证码失败，请重试。");
//                    }
//
//                    @Override
//                    public void onNext(ResultBean bean) {
//                        LogUtil.e(TAG, "--onNext----->>" + bean);
//                        if ("1".equals(bean.getRandomCode())) {
//                            mListener.onGetCodeSuccess();
//                        } else {
//                            mListener.onGetCodeNextFailure(ErrorUtil.getErrorMsg(bean.getRandomCode()));
//                        }
//                    }
//                }));
    }

    @Override
    public void onDestroy() {
        mCompositeSubscription.clear();
    }

}
