package cn.cbapay.ympay.mvp.presenter;

import cn.cbapay.ympay.bean.CareerUpdateRequestBean;

/**
 * Created by YangHongfei on 2016/10/25.
 */
public interface UpdateJobPresenter {
    void updateJob(CareerUpdateRequestBean bean);

    void onDestroy();

    interface UpdateJobView {
        void updateJobSuccess();

        void updateJobFail(String msg);
    }
}
