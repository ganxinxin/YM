package cn.cbapay.ympay.bean;

/**
 * 实名表数据
 * Created by icewater on 2016/10/24.
 */

public class StpcusinfBean {
    private boolean isNewRecord;
    private String custId; // 用户id
    private String payAcNo; // 账号编号
    private String registerDate; // 注册时间
    private String custName; // 客户名称
    private String custCredNo; // 证件号码
    private String custCredType; // 证件类型

    public boolean isIsNewRecord() {
        return isNewRecord;
    }

    public void setIsNewRecord(boolean isNewRecord) {
        this.isNewRecord = isNewRecord;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getPayAcNo() {
        return payAcNo;
    }

    public void setPayAcNo(String payAcNo) {
        this.payAcNo = payAcNo;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustCredNo() {
        return custCredNo;
    }

    public void setCustCredNo(String custCredNo) {
        this.custCredNo = custCredNo;
    }

    public String getCustCredType() {
        return custCredType;
    }

    public void setCustCredType(String custCredType) {
        this.custCredType = custCredType;
    }
}
