package cn.cbapay.ympay.mvp.model.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import cn.cbapay.ympay.bean.CustomerInfoResponseBean;
import cn.cbapay.ympay.bean.LoginResultBean;
import cn.cbapay.ympay.bean.MinePagerRequestBean;
import cn.cbapay.ympay.bean.RequestBean;
import cn.cbapay.ympay.bean.ResultBean;
import cn.cbapay.ympay.bean.StpusrinfBean;
import cn.cbapay.ympay.mvp.model.MinePagerModel;
import cn.cbapay.ympay.network.HttpUtils;
import cn.cbapay.ympay.utils.ErrorUtil;
import cn.cbapay.ympay.utils.LogUtil;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/10/25.
 */
public class MinePagerModelImpl implements MinePagerModel {
    private static final String TAG = "MinePagerModelImpl";

    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();
    private MinePagerModellListener listener;

    public MinePagerModelImpl(MinePagerModellListener listener) {
        this.listener = listener;
    }

    @Override
    public void getData(RequestBean bean) {
        mCompositeSubscription.add(HttpUtils.findCustomerInfo(bean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.e(TAG, "--onCompleted---");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "--onError------->>" + e.getMessage());
                        listener.getDataFail("获取用户信息失败");
                    }

                    @Override
                    public void onNext(ResultBean resultBean) {
                        LogUtil.e(TAG, "--onNext---" + resultBean);
                        System.out.println(resultBean.getResCode() + "gaowei");
                        String resCode = resultBean.getResCode();
                        if (resCode.equals("1")) {
                            CustomerInfoResponseBean customerInfoResponseBean = new Gson().fromJson(resultBean.getJsonData(), CustomerInfoResponseBean.class);
                            listener.getDataSuccess(customerInfoResponseBean);
                        }else  {
                            listener.getDataFail(ErrorUtil.getErrorMsg(resCode));
                            ErrorUtil.checkError(resCode);
                        }

                    }
                }));
    }
}
