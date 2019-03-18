package cn.cbapay.ympay.bean;

/**
 * realNameAuthent/updateArpAcCustRel 接口请求地址
 * Created by icewater on 2016/10/24.
 */

public class SetPayPasswordRequestBean {
    private String token;
    private String userPWD;// 支付密码
    private String newUserPWD;// 确认支付密码

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserPWD() {
        return userPWD;
    }

    public void setUserPWD(String userPWD) {
        this.userPWD = userPWD;
    }

    public String getNewUserPWD() {
        return newUserPWD;
    }

    public void setNewUserPWD(String newUserPWD) {
        this.newUserPWD = newUserPWD;
    }
}
