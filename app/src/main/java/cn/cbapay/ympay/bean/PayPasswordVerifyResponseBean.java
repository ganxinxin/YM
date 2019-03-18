package cn.cbapay.ympay.bean;

/**
 * accountInformation/payPwd 响应参数
 * Created by icewater on 2016/10/24.
 */

public class PayPasswordVerifyResponseBean {
    private String randomCode;

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }
}
