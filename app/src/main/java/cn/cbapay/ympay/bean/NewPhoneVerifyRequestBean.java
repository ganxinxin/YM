package cn.cbapay.ympay.bean;

/**
 * accountInformation/updateMAlready 请求接口
 * Created by icewater on 2016/10/24.
 */

public class NewPhoneVerifyRequestBean {
    private String token;
    private String newM; // 新手机号
    private String validateCode; // 验证码
    private String randomCode;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewM() {
        return newM;
    }

    public void setNewM(String newM) {
        this.newM = newM;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }
}
