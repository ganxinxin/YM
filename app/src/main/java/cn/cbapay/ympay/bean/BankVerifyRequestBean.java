package cn.cbapay.ympay.bean;

/**
 * realNameAuthent/getBankVerifyCode 接口请求参数
 * Created by icewater on 2016/10/24.
 */

public class BankVerifyRequestBean {
    private String token;
    private String bankPreMobile;// 银行预留手机号

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBankPreMobile() {
        return bankPreMobile;
    }

    public void setBankPreMobile(String bankPreMobile) {
        this.bankPreMobile = bankPreMobile;
    }
}
