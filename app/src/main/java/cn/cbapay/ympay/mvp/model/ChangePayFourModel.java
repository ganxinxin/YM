package cn.cbapay.ympay.mvp.model;

import cn.cbapay.ympay.bean.ChangePayPswRequestBean;

/**
 * Created by Administrator on 2016/10/31.
 */
public interface ChangePayFourModel {
    void changePsw(ChangePayPswRequestBean bean);

    void onDestroy();
    interface ChangePayFourListener{
        void  changeSuccess();

        void changeError();

        void showMessg(String msg);
    }
}
