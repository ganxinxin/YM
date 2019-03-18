package cn.cbapay.ympay.bean;

/**
 * Created by Administrator on 2016/10/24.
 */
public class SettingRequestBean {
   private String custLogin;
   private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCustLogin() {
        return custLogin;
    }

    public void setCustLogin(String custLogin) {
        this.custLogin = custLogin;
    }
}
