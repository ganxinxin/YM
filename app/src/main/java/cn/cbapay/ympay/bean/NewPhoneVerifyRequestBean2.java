package cn.cbapay.ympay.bean;

/**
 * accountInformation/updateM 接口请求参数
 * Created by icewater on 2016/10/24.
 */

public class NewPhoneVerifyRequestBean2 {
    private String token;
    private String newM; // 新手机号
    private String newCode; // 验证码
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

    public String getNewCode() {
        return newCode;
    }

    public void setNewCode(String newCode) {
        this.newCode = newCode;
    }

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }
}
