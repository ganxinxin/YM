package cn.cbapay.ympay.mvp.presenter;

import java.util.List;

import cn.cbapay.ympay.bean.CareerBean;

/**
 * Created by YangHongfei on 2016/10/25.
 */
public interface AllJobPresenter {
    void getAllJob();

    void onDestroy();

    interface AllJobView {
        void getAllJobSuccess(List<CareerBean> jobList);

        void getAllJobFail(String msg);
    }
}
