package cn.cbapay.ympay.bean;

/**
 * Created by Administrator on 2016/10/25.
 */
public class SettingResultBean {

    /**
     * payPwdStatus : 1
     * usrStatus : -1
     */

    private String payPwdStatus;
    private String usrStatus;

    public String getPayPwdStatus() {
        return payPwdStatus;
    }

    public void setPayPwdStatus(String payPwdStatus) {
        this.payPwdStatus = payPwdStatus;
    }

    public String getUsrStatus() {
        return usrStatus;
    }

    public void setUsrStatus(String usrStatus) {
        this.usrStatus = usrStatus;
    }
}
