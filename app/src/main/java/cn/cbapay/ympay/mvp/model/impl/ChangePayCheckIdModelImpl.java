package cn.cbapay.ympay.mvp.model.impl;

import com.google.gson.Gson;

import cn.cbapay.ympay.bean.ChangePayPswIdBean;
import cn.cbapay.ympay.bean.ChangePayPswIdResultBean;
import cn.cbapay.ympay.bean.ResultBean;
import cn.cbapay.ympay.mvp.model.ChangePayCheckIdModel;
import cn.cbapay.ympay.network.HttpUtils;
import cn.cbapay.ympay.utils.ErrorUtil;
import cn.cbapay.ympay.utils.LogUtil;
import cn.cbapay.ympay.utils.ShareUtil;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/10/28.
 */
public class ChangePayCheckIdModelImpl implements ChangePayCheckIdModel {

    private ChangePayCheckIdModel.ChangePayCheckIdModelListener listener;

    private static final String TAG = "ChangePayCheckIdModelImpl";

    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();


    public ChangePayCheckIdModelImpl(ChangePayCheckIdModel.ChangePayCheckIdModelListener listener) {
        this.listener = listener;
    }

    @Override
    public void checkIdNumber(ChangePayPswIdBean bean) {
        mCompositeSubscription.add(HttpUtils.checkIdNumber(bean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "--onError------->>" + e.getMessage());
                        listener.showMsg("校验失败，请重试");
                    }

                    @Override
                    public void onNext(ResultBean resultBean) {
                        LogUtil.e(TAG, "--onNext---" + resultBean);
                        System.out.println(resultBean.getResCode());
                        String resCode = resultBean.getResCode();
                        if (resCode.equals("1")) {
                            ChangePayPswIdResultBean changePayPswIdResultBean = new Gson().fromJson(resultBean.getJsonData(), ChangePayPswIdResultBean.class);
                            String randomCode = changePayPswIdResultBean.getRandomCode();
                            listener.checkSuccess(randomCode);

                        }else {
                            listener.showMsg(ErrorUtil.getErrorMsg(resCode));
                            ErrorUtil.checkError(resCode);
                        }
                    }
                }));
    }
}