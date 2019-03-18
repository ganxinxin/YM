package cn.cbapay.ympay.mvp.model.impl;

import com.google.gson.Gson;

import cn.cbapay.ympay.bean.AccountInfoResponseBean;
import cn.cbapay.ympay.bean.RequestBean;
import cn.cbapay.ympay.bean.ResultBean;
import cn.cbapay.ympay.mvp.model.AccountInfoModel;
import cn.cbapay.ympay.network.HttpUtils;
import cn.cbapay.ympay.utils.ErrorUtil;
import cn.cbapay.ympay.utils.LogUtil;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 获取账户信息
 * Created by icewater on 2016/10/26.
 */

public class AccountInfoModelImpl implements AccountInfoModel {
    private static final String TAG = "AccountInfoModelImpl";

    private GetAccountInfoListener mGetAccountInfoListener;
    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public AccountInfoModelImpl(GetAccountInfoListener l){
        this.mGetAccountInfoListener = l;
    }

    @Override
    public void getAccountInfo(RequestBean bean) {
        Subscription o = HttpUtils.findAll(bean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "---onError--->>" + e.getMessage());
                        mGetAccountInfoListener.onGetAccountInfoFailed("获取账户信息失败");
                    }

                    @Override
                    public void onNext(ResultBean resultBean) {
                        LogUtil.e(TAG, "--onNext---->>" + resultBean);
                        if ("1".equals(resultBean.getResCode())) {
                            AccountInfoResponseBean b = new Gson().fromJson(resultBean.getJsonData(), AccountInfoResponseBean.class);
                            mGetAccountInfoListener.onGetAccountInfoSuccess( b.getStpusrinf());
                        }else{
                            mGetAccountInfoListener.onGetAccountInfoFailed(ErrorUtil.getErrorMsg(resultBean.getResCode()));
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
