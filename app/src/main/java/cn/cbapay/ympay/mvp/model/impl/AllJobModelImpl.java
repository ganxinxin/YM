package cn.cbapay.ympay.mvp.model.impl;

import com.google.gson.Gson;

import java.util.List;

import cn.cbapay.ympay.bean.AllJobListBean;
import cn.cbapay.ympay.bean.CareerBean;
import cn.cbapay.ympay.bean.ResultBean;
import cn.cbapay.ympay.mvp.model.AllJobModel;
import cn.cbapay.ympay.network.HttpUtils;
import cn.cbapay.ympay.utils.ErrorUtil;
import cn.cbapay.ympay.utils.LogUtil;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by YangHongfei on 2016/10/25.
 */
public class AllJobModelImpl implements AllJobModel {
    private static final String TAG = AllJobModelImpl.class.getSimpleName();
    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();
    private AllJobModel.OnGetAllJobListener mListener;

    public AllJobModelImpl(OnGetAllJobListener listener) {
        mListener = listener;
    }

    @Override
    public void getAllJob() {
        mCompositeSubscription.add(HttpUtils.getAllJob()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "---onError--->>" + e.getMessage());
                        mListener.onGetAllJobFail("获取全部职业失败");
                    }

                    @Override
                    public void onNext(ResultBean bean) {
                        LogUtil.e(TAG, "--onNext---->>" + bean);
                        if ("1".equals(bean.getResCode())) {
                            String jsonData = bean.getJsonData();
                            Gson gson = new Gson();
                            AllJobListBean listBean = gson.fromJson(jsonData, AllJobListBean.class);
                            List<CareerBean> jobList = listBean.getList();
                            mListener.onGetAllJobSuccess(jobList);
                        } else {
                            mListener.onGetAllJobFail(ErrorUtil.getErrorMsg(bean.getResCode()));
                        }
                    }
                }));
    }

    @Override
    public void onDestroy() {
        mCompositeSubscription.clear();
    }


}
