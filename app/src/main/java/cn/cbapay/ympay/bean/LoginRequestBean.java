package cn.cbapay.ympay.bean;

/**
 * Created by Administrator on 2016/10/24.
 */
public class LoginRequestBean {

    private String custLogin;
    private String custPwd;

    public String getCustLogin() {
        return custLogin;
    }

    public void setCustLogin(String custLogin) {
        this.custLogin = custLogin;
    }

    public String getCustPwd() {
        return custPwd;
    }

    public void setCustPwd(String custPwd) {
        this.custPwd = custPwd;
    }
}
