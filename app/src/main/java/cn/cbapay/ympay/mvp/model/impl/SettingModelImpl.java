package cn.cbapay.ympay.mvp.model.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import cn.cbapay.ympay.bean.RegisterResultBean;
import cn.cbapay.ympay.bean.ResultBean;
import cn.cbapay.ympay.bean.SettingRequestBean;
import cn.cbapay.ympay.bean.SettingResultBean;
import cn.cbapay.ympay.mvp.model.SettingModel;
import cn.cbapay.ympay.network.HttpUtils;
import cn.cbapay.ympay.utils.ErrorUtil;
import cn.cbapay.ympay.utils.LogUtil;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/10/24.
 */
public class SettingModelImpl implements SettingModel {
    private SettingModelListener listener;
    private static final String TAG = "SettingModelImpl";
    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public SettingModelImpl(SettingModelListener listener) {
        this.listener = listener;
    }

    @Override
    public void getData(SettingRequestBean bean) {
        mCompositeSubscription.add(HttpUtils.setting(bean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.e(TAG, "--onCompleted---");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "--onError------->>" + e.getMessage());
                    }

                    @Override
                    public void onNext(ResultBean resultBean) {
                        LogUtil.e(TAG, "--onNext---" + resultBean);
                        if (resultBean.getResCode().equals("1")) {
                            SettingResultBean settingResultBean = new Gson().fromJson(resultBean.getJsonData(), SettingResultBean.class);
                            listener.getDataSuccess(settingResultBean);
                        }else {
                            listener.showMsg(ErrorUtil.getErrorMsg(resultBean.getResCode()));
                            ErrorUtil.checkError(resultBean.getResCode());
                        }

                    }
                }));
    }
}
