package cn.cbapay.ympay.mvp.model.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import cn.cbapay.ympay.bean.LoginRequestBean;
import cn.cbapay.ympay.bean.LoginResultBean;
import cn.cbapay.ympay.bean.ResultBean;
import cn.cbapay.ympay.mvp.model.LoginModel;
import cn.cbapay.ympay.network.HttpUtils;
import cn.cbapay.ympay.utils.GlobalData;
import cn.cbapay.ympay.utils.LogUtil;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/10/24.
 */
public class LoginModelImpl implements LoginModel {

    private static final String TAG = "RegistModelImpl";

    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    private LoginModel.LoginListener loginListener;

    public LoginModelImpl(LoginModel.LoginListener loginListener) {
        this.loginListener = loginListener;

    }

    @Override
    public void login(LoginRequestBean bean) {
        mCompositeSubscription.add(HttpUtils.login(bean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.e(TAG, "--onCompleted---");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "--onError------->>" + e.getMessage());
                        loginListener.loginError("登录失败");
                    }

                    @Override
                    public void onNext(ResultBean resultBean) {
                        LogUtil.e(TAG, "--onNext---" + resultBean);
                        System.out.println(resultBean.getResCode() + "gaowei");
                        if (resultBean.getResCode().equals("1")) {
                            LoginResultBean loginResultBean = new Gson().fromJson(resultBean.getJsonData(), LoginResultBean.class);
                            loginListener.loginSuccess(loginResultBean);
                            String resMsg = resultBean.getResMsg();
                            loginListener.showMsg(resMsg);
                        } else {
                            loginListener.showMsg(resultBean.getResMsg());
                        }

                    }
                }));
    }
}
