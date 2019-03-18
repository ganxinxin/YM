package cn.cbapay.ympay.bean;

/**
 * accountInformation/payPwd 请求参数
 * Created by icewater on 2016/10/24.
 */

public class PayPasswordVerifyRequestBean {
    private String token;
    private String payPwd; // 支付密码

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPayPwd() {
        return payPwd;
    }

    public void setPayPwd(String payPwd) {
        this.payPwd = payPwd;
    }
}
