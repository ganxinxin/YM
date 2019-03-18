package cn.cbapay.ympay.bean;

/**
 * 账户主档案表数据
 * Created by icewater on 2016/10/24.
 */

public class ArpAcProfileBean {
    private boolean isNewRecord;
    private String payAcNo; // 账号编号
    private String cashAcBal; // 账户余额
    private String frozBalance; // 已冻结余额
    private String disableBalance; // 不可用金额

    public boolean isIsNewRecord() {
        return isNewRecord;
    }

    public void setIsNewRecord(boolean isNewRecord) {
        this.isNewRecord = isNewRecord;
    }

    public String getPayAcNo() {
        return payAcNo;
    }

    public void setPayAcNo(String payAcNo) {
        this.payAcNo = payAcNo;
    }

    public String getCashAcBal() {
        return cashAcBal;
    }

    public void setCashAcBal(String cashAcBal) {
        this.cashAcBal = cashAcBal;
    }

    public String getFrozBalance() {
        return frozBalance;
    }

    public void setFrozBalance(String frozBalance) {
        this.frozBalance = frozBalance;
    }

    public String getDisableBalance() {
        return disableBalance;
    }

    public void setDisableBalance(String disableBalance) {
        this.disableBalance = disableBalance;
    }
}
