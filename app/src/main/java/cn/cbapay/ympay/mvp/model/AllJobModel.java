package cn.cbapay.ympay.mvp.model;

import java.util.List;

import cn.cbapay.ympay.bean.CareerBean;

/**
 * Created by YangHongfei on 2016/10/25.
 */
public interface AllJobModel {
    void getAllJob();

    void onDestroy();

    interface OnGetAllJobListener {
        void onGetAllJobSuccess(List<CareerBean> jobList);

        void onGetAllJobFail(String msg);
    }
}
