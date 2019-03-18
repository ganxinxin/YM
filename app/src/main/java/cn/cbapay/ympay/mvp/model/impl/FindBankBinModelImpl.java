package cn.cbapay.ympay.mvp.model.impl;

import com.google.gson.Gson;

import cn.cbapay.ympay.bean.CardBinRequestBean;
import cn.cbapay.ympay.bean.CardBinResponseBean;
import cn.cbapay.ympay.bean.ResultBean;
import cn.cbapay.ympay.mvp.model.FindBankBinModel;
import cn.cbapay.ympay.network.HttpUtils;
import cn.cbapay.ympay.utils.ErrorUtil;
import cn.cbapay.ympay.utils.LogUtil;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 获取卡bin验证银行卡是否已绑定
 * Created by icewater on 2016/10/26.
 */

public class FindBankBinModelImpl implements FindBankBinModel {
    private static final String TAG = "FindBankBinModelImpl";

    private FindBankBinListener mFindBankBinListener;
    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public FindBankBinModelImpl(FindBankBinListener l) {
        this.mFindBankBinListener = l;
    }

    @Override
    public void findBankBin(CardBinRequestBean bean) {
        Subscription o = HttpUtils.findBankBin(bean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "---onError--->>" + e.getMessage());
                        mFindBankBinListener.onFindBankBinFailed("获取卡Bin失败");
                    }

                    @Override
                    public void onNext(ResultBean resultBean) {
                        LogUtil.e(TAG, "--onNext---->>" + resultBean);
                        if ("1".equals(resultBean.getResCode())) {
                            CardBinResponseBean b = new Gson().fromJson(resultBean.getJsonData(), CardBinResponseBean.class);
                            mFindBankBinListener.onFindBankBinSuccess(b);
                        } else {
                            mFindBankBinListener.onFindBankBinFailed(ErrorUtil.getErrorMsg(resultBean.getResCode()));
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
