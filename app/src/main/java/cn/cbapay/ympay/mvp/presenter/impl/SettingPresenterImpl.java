package cn.cbapay.ympay.mvp.presenter.impl;

import cn.cbapay.ympay.bean.ProvincesBean;
import cn.cbapay.ympay.bean.SettingRequestBean;
import cn.cbapay.ympay.bean.SettingResultBean;
import cn.cbapay.ympay.mvp.model.SettingModel;
import cn.cbapay.ympay.mvp.model.impl.SettingModelImpl;
import cn.cbapay.ympay.mvp.presenter.SettingPresenter;
import cn.cbapay.ympay.utils.PhoneUtils;

/**
 * Created by Administrator on 2016/10/24.
 */
public class SettingPresenterImpl implements SettingPresenter,SettingModel.SettingModelListener {
    private SettingView settingView;
    private SettingModel settingModel;
    public SettingPresenterImpl(SettingView settingView) {
        this.settingView = settingView;
        this.settingModel = new SettingModelImpl(this);

    }

    @Override
    public void getData(SettingRequestBean bean) {
        settingModel.getData(bean);
    }

    @Override
    public void getDataSuccess(SettingResultBean bean) {
        settingView.getDataSuccess(bean);
    }

    @Override
    public void getDataFail(String s) {

    }

    @Override
    public void showMsg(String errorMsg) {
        settingView.showMsg(errorMsg);
    }


}
