package cn.cbapay.ympay.bean;

/**
 * accountInformation/verificationEmailCode 请求接口参数
 * Created by icewater on 2016/10/24.
 */

public class EmailVerifyRequestBean {
    private String token;
    private String email;
    private String validateCode;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }
}
