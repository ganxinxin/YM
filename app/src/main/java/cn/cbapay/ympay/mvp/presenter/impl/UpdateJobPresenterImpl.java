package cn.cbapay.ympay.mvp.presenter.impl;

import cn.cbapay.ympay.bean.CareerUpdateRequestBean;
import cn.cbapay.ympay.mvp.model.UpdateJobModel;
import cn.cbapay.ympay.mvp.model.impl.UpdateJobModelImpl;
import cn.cbapay.ympay.mvp.presenter.UpdateJobPresenter;


/**
 * Created by YangHongfei on 2016/10/25.
 */
public class UpdateJobPresenterImpl implements UpdateJobPresenter, UpdateJobModel.OnUpdateJobListener {
    private static final String TAG = UpdateJobPresenterImpl.class.getSimpleName();
    private UpdateJobModel updateJobModel;
    private UpdateJobView updateJobView;

    public UpdateJobPresenterImpl(UpdateJobView updateJobView) {
        this.updateJobView = updateJobView;
        this.updateJobModel = new UpdateJobModelImpl(this);
    }

    @Override
    public void updateJob(CareerUpdateRequestBean bean) {
        updateJobModel.updateJob(bean);
    }

    @Override
    public void onDestroy() {
        updateJobModel.onDestroy();
    }

    @Override
    public void onUpdateJobSuccess() {
        updateJobView.updateJobSuccess();
    }

    @Override
    public void onUpdateJobFail(String msg) {
        updateJobView.updateJobFail(msg);
    }
}
