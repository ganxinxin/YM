package cn.cbapay.ympay.mvp.model;

import cn.cbapay.ympay.bean.CareerUpdateRequestBean;

/**
 * Created by YangHongfei on 2016/10/25.
 */
public interface UpdateJobModel {
    void updateJob(CareerUpdateRequestBean bean);

    void onDestroy();

    interface OnUpdateJobListener {
        void onUpdateJobSuccess();

        void onUpdateJobFail(String msg);
    }
}
