package cn.cbapay.ympay.mvp.model;

import cn.cbapay.ympay.bean.FrogetPasswordSenRequestBean;
import cn.cbapay.ympay.bean.LoginResultBean;

/**
 * Created by Administrator on 2016/10/27.
 */
public interface ForgetPasswordSencondModel {

    void requsetPassword(FrogetPasswordSenRequestBean bean);

    interface ForgetPasswordSencondListener {

        void changeSuccess();

        void chanangeError();

        void showMsg(String msg);

    }
}
