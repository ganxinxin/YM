package cn.cbapay.ympay.bean;

/**
 * Created by Administrator on 2016/10/21.
 */
public class ForgetPasswordNextBean {

    private String custLogin;
    private String validateCode;

    public String getCustLogin() {
        return custLogin;
    }

    public void setCustLogin(String custLogin) {
        this.custLogin = custLogin;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }
}
