package cn.cbapay.ympay.mvp.presenter;

import cn.cbapay.ympay.bean.SettingRequestBean;
import cn.cbapay.ympay.bean.SettingResultBean;

/**
 * Created by Administrator on 2016/10/24.
 */
public interface SettingPresenter {

    void getData(SettingRequestBean bean);

    interface SettingView {

        void showMsg(String msg);

        void getDataSuccess(SettingResultBean bean);
    }
}
