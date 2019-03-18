package cn.cbapay.ympay.mvp.presenter.impl;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.cbapay.ympay.app.MyApplication;
import cn.cbapay.ympay.bean.RequestBean;
import cn.cbapay.ympay.bean.StpusrinfBean;
import cn.cbapay.ympay.mvp.model.AccountInfoModel;
import cn.cbapay.ympay.mvp.model.impl.AccountInfoModelImpl;
import cn.cbapay.ympay.mvp.presenter.AccountInfoPresenter;
import cn.cbapay.ympay.utils.GlobalData;
import cn.cbapay.ympay.utils.ShareUtil;

/**
 * 获取账户信息页面
 * Created by icewater on 2016/10/31.
 */

public class AccountInfoPresenterImpl implements AccountInfoPresenter, AccountInfoModel.GetAccountInfoListener {
    private static final String TAG = "AccountInfoPresenterImpl";

    private AccountInfoModel mAccountInfoModel;
    private AccountInfoView mAccountInfoView;

    private String state = "";

    public AccountInfoPresenterImpl(AccountInfoView v) {
        this.mAccountInfoView = v;
        this.mAccountInfoModel = new AccountInfoModelImpl(this);
    }

    /**
     * 后台获取账户信息
     */
    @Override
    public void getAccountInfo() {
//        updateAccountInfo();
        RequestBean bean = new RequestBean();
        bean.setToken(ShareUtil.getValue("token", MyApplication.getContext()));
        mAccountInfoModel.getAccountInfo(bean);
    }

    /**
     * 从全局变量更新账户信息
     */
    @Override
    public void updateAccountInfo() {
        StpusrinfBean info = GlobalData.getStpusrinf();
        if (info != null) {
            if (TextUtils.isEmpty(info.getCustLogin())) {
                mAccountInfoView.setAccountName("");
            } else {
                mAccountInfoView.setAccountName(format(info.getCustLogin()));
            }
            String state = info.getUsrStatus();
            this.state = state;
            if ("-1".equals(state)) {
                mAccountInfoView.setAuthState("未实名");
            } else if ("0".equals(state)) {
                mAccountInfoView.setAuthState("未认证");
            } else if ("1".equals(state)) {
                mAccountInfoView.setAuthState("认证审核中");
            } else if ("2".equals(state)) {
                mAccountInfoView.setAuthState("已实名");
            } else if ("3".equals(state)) {
                mAccountInfoView.setAuthState("未通过认证");
            } else {
                this.state = "";
                mAccountInfoView.setAuthState("");
            }
            if (TextUtils.isEmpty(info.getUsrMobile())) {
                mAccountInfoView.setPhone("");
            } else {
                mAccountInfoView.setPhone(format(info.getUsrMobile()));
            }
            if (TextUtils.isEmpty(info.getUsrEmail())) {
                mAccountInfoView.setEmail("");
            } else {
                mAccountInfoView.setEmail(format(info.getUsrEmail()));
            }
            if (TextUtils.isEmpty(info.getUsrJob())) {
                mAccountInfoView.setCareer("");
            } else {
                mAccountInfoView.setCareer(info.getUsrJob());
            }
            if (TextUtils.isEmpty(info.getPhoneAddress())) {
                mAccountInfoView.setArea("");
            } else {
                mAccountInfoView.setArea(info.getPhoneAddress());
            }
        }
    }

    /**
     * 点击实名认证
     */
    @Override
    public void clickAuthState() {
        if ("-1".equals(state)) {
            mAccountInfoView.startAuthentication();
        } else if ("0".equals(state) || "1".equals(state) || "2".equals(state) || "3".equals(state)) {
            mAccountInfoView.startIdVerification();
        }
    }

    /**
     * 更换手机号
     */
    @Override
    public void clickPhone() {
        if ("-1".equals(state)) {
            mAccountInfoView.startInputPassword();
        } else {
            mAccountInfoView.startChangePhone();
        }
    }

    /**
     * 更换邮箱
     */
    @Override
    public void clickEmail() {
        mAccountInfoView.startEmail();
    }

    /**
     * 更换职业
     */
    @Override
    public void clickCareer() {
        mAccountInfoView.startCareer();
    }

    /**
     * 更换地区
     */
    @Override
    public void clickArea() {
        mAccountInfoView.startArea();
    }

    @Override
    public void onDestroy() {
        mAccountInfoModel.onDestroy();
    }

    /**
     * 获取账户信息成功
     *
     * @param stpusrinf 账户信息
     */
    @Override
    public void onGetAccountInfoSuccess(StpusrinfBean stpusrinf) {
        GlobalData.setStpusrinf(stpusrinf);
        updateAccountInfo();
    }

    /**
     * 获取账户信息失败
     *
     * @param msg 失败信息
     */
    @Override
    public void onGetAccountInfoFailed(String msg) {
        mAccountInfoView.showMessage(msg);
    }

    private String format(String s) {
        Pattern p = Pattern
                .compile("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$");
        Matcher m = p.matcher(s);
        if (m.matches()) {
            return formatPhone(s);
        }
        /// [\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?
        p = Pattern
                .compile("[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?");
        m = p.matcher(s);
        if (m.matches()) {
            return formatEmail(s);
        }
        return s;
    }

    private String formatPhone(String s) {
        return s.substring(0, 3) + "****" + s.substring(7, 11);
    }

    private String formatEmail(String s) {
        String[] str = s.split("@");
        if (str.length != 2) {
            return s;
        }
        if (str[0].length() == 0) {
            return s;
        } else if (str[0].length() == 1) {
            str[0] = "***";
        } else if (str[0].length() == 2) {
            str[0] = str[0].substring(0, 1) + "***";
        } else {
            str[0] = str[0].substring(0, 1) + "***" + str[0].substring(str[0].length() - 1, str[0].length());
        }
        return str[0] + "@" + str[1];
    }
}
