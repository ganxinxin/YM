package cn.cbapay.ympay.mvp.presenter.impl;

import java.util.List;

import cn.cbapay.ympay.bean.CareerBean;
import cn.cbapay.ympay.mvp.model.AllJobModel;
import cn.cbapay.ympay.mvp.model.impl.AllJobModelImpl;
import cn.cbapay.ympay.mvp.presenter.AllJobPresenter;

/**
 * Created by YangHongfei on 2016/10/25.
 */
public class AllJobPresenterImpl implements AllJobPresenter, AllJobModel.OnGetAllJobListener {
    private static final String TAG = AllJobPresenterImpl.class.getSimpleName();
    private AllJobModel allJobModel;
    private AllJobView allJobView;

    public AllJobPresenterImpl(AllJobView allJobView) {
        this.allJobView = allJobView;
        allJobModel = new AllJobModelImpl(this);

    }

    @Override
    public void getAllJob() {
        allJobModel.getAllJob();
    }

    @Override
    public void onDestroy() {
        allJobModel.onDestroy();
    }

    @Override
    public void onGetAllJobSuccess(List<CareerBean> jobList) {
        allJobView.getAllJobSuccess(jobList);
    }

    @Override
    public void onGetAllJobFail(String msg) {
        allJobView.getAllJobFail(msg);
    }
}
