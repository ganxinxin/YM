package cn.cbapay.ympay.mvp.presenter;

import cn.cbapay.ympay.bean.ChangePayPswRequestBean;

/**
 * Created by Administrator on 2016/10/31.
 */
public interface ChangePayFourPresenter {

    void changePsw(ChangePayPswRequestBean bean);

    interface ChangePayFourView {

        void showMsg(String msg);

        void changeSuccess();

        void changeError();


    }
}
