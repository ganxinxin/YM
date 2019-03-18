package cn.cbapay.ympay.bean;

/**
 * accountInformation/matching 接口请求参数
 * Created by icewater on 2016/10/24.
 */

public class OriginalPhoneVerifyRequestBean {
    private String token;
    private String validateCode;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }
}
