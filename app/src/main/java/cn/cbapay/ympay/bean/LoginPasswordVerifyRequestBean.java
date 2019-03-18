package cn.cbapay.ympay.bean;

/**
 * accountInformation/findPwd 接口请求参数
 * Created by icewater on 2016/10/24.
 */

public class LoginPasswordVerifyRequestBean {
    private String token;
    private String custPwd; // 登录密码

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCustPwd() {
        return custPwd;
    }

    public void setCustPwd(String custPwd) {
        this.custPwd = custPwd;
    }
}
