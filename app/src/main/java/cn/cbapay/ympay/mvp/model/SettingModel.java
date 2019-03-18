package cn.cbapay.ympay.mvp.model;

import cn.cbapay.ympay.bean.SettingRequestBean;
import cn.cbapay.ympay.bean.SettingResultBean;

/**
 * Created by Administrator on 2016/10/24.
 */
public interface SettingModel {
    void getData(SettingRequestBean bean);

    interface SettingModelListener {
        void getDataSuccess(SettingResultBean bean);

        void getDataFail(String s);

        void showMsg(String errorMsg);
    }
}
