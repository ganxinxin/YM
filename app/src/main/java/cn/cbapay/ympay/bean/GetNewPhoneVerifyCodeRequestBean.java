package cn.cbapay.ympay.bean;

/**
 * accountInformation/sendCode 接口请求参数
 * Created by icewater on 2016/10/24.
 */

public class GetNewPhoneVerifyCodeRequestBean {
    private String token;
    private String mOrE; // 新手机号或 email
    private String randomCode; // 随机串

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getmOrE() {
        return mOrE;
    }

    public void setmOrE(String mOrE) {
        this.mOrE = mOrE;
    }

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }
}
