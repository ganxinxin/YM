package cn.cbapay.ympay.bean;

/**
 * accountInformation/sendCode 接口响应参数
 * Created by icewater on 2016/10/25.
 */

public class GetNewPhoneVerifyCodeResponseBean {
    private String randomCode;

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }
}
