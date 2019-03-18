package cn.cbapay.ympay.bean;

import java.io.Serializable;

/**
 * realNameAuthent/findBankBin 接口响应参数
 * Created by icewater on 2016/10/24.
 */

public class CardBinResponseBean implements Serializable {
    private String bankNo;//银行简称
    private String bankName;//发卡行
    private String cardType;//银行卡类型编号 01（借记卡），02（贷记卡）03(准贷记卡) 04（预付卡）
    private String bankCardType;//银行卡类型名称
    private String bankAcNo;//银行卡号
    // TODO 银行 logo

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
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

    public String getBankCardType() {
        return bankCardType;
    }

    public void setBankCardType(String bankCardType) {
        this.bankCardType = bankCardType;
    }

    public String getBankAcNo() {
        return bankAcNo;
    }

    public void setBankAcNo(String bankAcNo) {
        this.bankAcNo = bankAcNo;
    }
}
