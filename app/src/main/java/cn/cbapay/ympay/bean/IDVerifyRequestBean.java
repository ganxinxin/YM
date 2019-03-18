package cn.cbapay.ympay.bean;

/**
 * set/isIdRight 接口请求参数
 * Created by icewater on 2016/10/24.
 */

public class IDVerifyRequestBean {
    private String custLogin; // 登录账号
    private String token; //
    private String idCard; // 身份证号
    private String randomCode; // 随机数

    public String getCustLogin() {
        return custLogin;
    }

    public void setCustLogin(String custLogin) {
        this.custLogin = custLogin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }
}
