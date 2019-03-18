package cn.cbapay.ympay.bean;

/**
 * Created by Administrator on 2016/10/27.
 */
public class ChangeLoginPassWordBean {

    /**
     * custLogin : 15263040492
     * custPwd : 123456
     * token : c07a11ab947cfb4810855b64b83233ff
     * randomCode : 664764552564ec8d466a028cf2dee10b
     */

    private String custLogin;
    private String custPwd;
    private String token;
    private String randomCode;

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
