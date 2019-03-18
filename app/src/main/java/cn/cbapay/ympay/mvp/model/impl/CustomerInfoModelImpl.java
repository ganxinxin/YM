package cn.cbapay.ympay.mvp.model.impl;

import com.google.gson.Gson;

import cn.cbapay.ympay.bean.CustomerInfoResponseBean;
import cn.cbapay.ympay.bean.RequestBean;
import cn.cbapay.ympay.bean.ResultBean;
import cn.cbapay.ympay.mvp.model.CustomerInfoModel;
import cn.cbapay.ympay.network.HttpUtils;
import cn.cbapay.ympay.utils.ErrorUtil;
import cn.cbapay.ympay.utils.LogUtil;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 获取客户的详细信息
 * Created by icewater on 2016/10/26.
 */

public class CustomerInfoModelImpl implements CustomerInfoModel {
    private static final String TAG = "CustomerInfoModelImpl";

    private FindCustomerInfoListener mFindCustomerInfoListener;
    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public CustomerInfoModelImpl(FindCustomerInfoListener l){
        this.mFindCustomerInfoListener = l;
    }

    @Override
    public void findCustomerInfo(RequestBean bean) {
        Subscription o = HttpUtils.findCustomerInfo(bean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "---onError--->>" + e.getMessage());
                        mFindCustomerInfoListener.onFindCustomerInfoFailed("获取客户信息失败");
                    }

                    @Override
                    public void onNext(ResultBean resultBean) {
                        LogUtil.e(TAG, "--onNext---->>" + resultBean);
                        if ("1".equals(resultBean.getResCode())) {
                            CustomerInfoResponseBean b = new Gson().fromJson(resultBean.getJsonData(), CustomerInfoResponseBean.class);
                            mFindCustomerInfoListener.onFindCustomerInfoSuccess(b.getArpAcProfile(), b.getStpusrinf(), b.getStpcusinf(), b.getPayPwdStatus());
                        }else{
                            mFindCustomerInfoListener.onFindCustomerInfoFailed(ErrorUtil.getErrorMsg(resultBean.getResCode()));
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
