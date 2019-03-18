package cn.cbapay.ympay.bean;

/**
 * Created by Administrator on 2016/10/31.
 */
public class ChangePayPswRequestBean {
    private String custLogin;
    private String newPayPass;
    private String token;
    private String randomCode;

    public String getCustLogin() {
        return custLogin;
    }

    public void setCustLogin(String custLogin) {
        this.custLogin = custLogin;
    }

    public String getNewPayPass() {
        return newPayPass;
    }

    public void setNewPayPass(String newPayPass) {
        this.newPayPass = newPayPass;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }
}
