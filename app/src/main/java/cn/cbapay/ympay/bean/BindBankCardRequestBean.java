package cn.cbapay.ympay.bean;

import java.io.Serializable;

/**
 * realNameAuthent/toBindBankCard 接口请求参数
 * Created by icewater on 2016/10/24.
 */

public class BindBankCardRequestBean implements Serializable {
    private String token;//
    private String validateCode;//手机验证码
    private String bankName;//发卡行
    private String cardType;//银行卡类型编号
    private String cardName;//持卡人姓名
    private String idCardNum;//身份证号
    private String cardDeadline;//有效期
    private String bankPreMobile;//银行预留手机号
    private String bankNo;//银行简称
    private String bankAcNo;//银行卡号
    private String safetyCode;//信用卡安全码

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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public String getCardDeadline() {
        return cardDeadline;
    }

    public void setCardDeadline(String cardDeadline) {
        this.cardDeadline = cardDeadline;
    }

    public String getBankPreMobile() {
        return bankPreMobile;
    }

    public void setBankPreMobile(String bankPreMobile) {
        this.bankPreMobile = bankPreMobile;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getBankAcNo() {
        return bankAcNo;
    }

    public void setBankAcNo(String bankAcNo) {
        this.bankAcNo = bankAcNo;
    }

    public String getSafetyCode() {
        return safetyCode;
    }

    public void setSafetyCode(String safetyCode) {
        this.safetyCode = safetyCode;
    }

    @Override
    public String toString() {
        return "BindBankCardRequestBean{" +
                "token='" + token + '\'' +
                ", validateCode='" + validateCode + '\'' +
                ", bankName='" + bankName + '\'' +
                ", cardType='" + cardType + '\'' +
                ", cardName='" + cardName + '\'' +
                ", idCardNum='" + idCardNum + '\'' +
                ", cardDeadline='" + cardDeadline + '\'' +
                ", bankPreMobile='" + bankPreMobile + '\'' +
                ", bankNo='" + bankNo + '\'' +
                ", bankAcNo='" + bankAcNo + '\'' +
                ", safetyCode='" + safetyCode + '\'' +
                '}';
    }
}
