package cn.cbapay.ympay.mvp.model.impl;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import cn.cbapay.ympay.app.MyApplication;
import cn.cbapay.ympay.bean.AreaBean;
import cn.cbapay.ympay.mvp.model.AreaModel;
import cn.cbapay.ympay.utils.LogUtil;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/9/21.
 */
public class AreaModelImpl implements AreaModel {
    private static final String TAG = AreaModelImpl.class.getSimpleName();
    private AreaModel.AreaListener listener;
    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    /**
     * key - 省 value - 市s
     */
    private Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();


    public AreaModelImpl(AreaModel.AreaListener listener) {
        this.listener = listener;

    }


    @Override
    public void getArea() {
        mCompositeSubscription.add(Observable.just("city.json").map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return readFile(s);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "---onError--->>" + e.getMessage());
                        listener.getAreaFail(e.getMessage());
                    }

                    @Override
                    public void onNext(String areaJson) {
                        LogUtil.e(TAG, "--onNext---->>" + areaJson);
                        Gson gson = new Gson();
                        AreaBean bean = gson.fromJson(areaJson, AreaBean.class);
                        listener.getAreaSuccess(bean);

                    }
                }));

    }

    @Override
    public void onDestroy() {
        mCompositeSubscription.clear();
    }

    public String readFile(String file) {
        try {
            StringBuffer sb = new StringBuffer();
            InputStream is = MyApplication.getContext().getAssets().open(file);
            int len = -1;
            byte[] buf = new byte[is.available()];
            while ((len = is.read(buf)) != -1) {
                sb.append(new String(buf, 0, len, "gbk"));

            }
            is.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
