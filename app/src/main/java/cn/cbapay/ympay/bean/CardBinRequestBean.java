package cn.cbapay.ympay.bean;

/**
 * realNameAuthent/findBankBin 接口请求参数
 * Created by icewater on 2016/10/24.
 */

public class CardBinRequestBean {
    private String token;
    private String bankCardNo; // 银行卡号

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }
}
