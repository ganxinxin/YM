package cn.cbapay.ympay.bean;

/**
 * Created by Administrator on 2016/10/27.
 */
public class FrogetPasswordSenRequestBean {

    /**
     * custLogin : 18363975175
     * newPassWord : Abcd123456
     * randomCode : be4f9c82c0caa5f2cf555fdfc3cf1b8d
     */

    private String custLogin;
    private String newPassWord;
    private String randomCode;

    public String getCustLogin() {
        return custLogin;
    }

    public void setCustLogin(String custLogin) {
        this.custLogin = custLogin;
    }

    public String getNewPassWord() {
        return newPassWord;
    }

    public void setNewPassWord(String newPassWord) {
        this.newPassWord = newPassWord;
    }

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }
}
