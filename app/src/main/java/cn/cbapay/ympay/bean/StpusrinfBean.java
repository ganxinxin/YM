package cn.cbapay.ympay.bean;

/**
 * 用户表数据
 * Created by icewater on 2016/10/24.
 */

public class StpusrinfBean {
    private boolean isNewRecord;
    private String custId; // 用户id
    private String custLogin; // 账户名
    private String usrEmail; // 邮箱
    private String usrJob; // 职业
    private String usrMobile; // 手机号
    private String usrStatus; // 认证状态 -1未实名认证 0,1,3都为待完善，2为已实名认证
    private String phoneAddress; // 定位地址

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

    public String getCustLogin() {
        return custLogin;
    }

    public void setCustLogin(String custLogin) {
        this.custLogin = custLogin;
    }

    public String getUsrEmail() {
        return usrEmail;
    }

    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }

    public String getUsrJob() {
        return usrJob;
    }

    public void setUsrJob(String usrJob) {
        this.usrJob = usrJob;
    }

    public String getUsrMobile() {
        return usrMobile;
    }

    public void setUsrMobile(String usrMobile) {
        this.usrMobile = usrMobile;
    }

    public String getUsrStatus() {
        return usrStatus;
    }

    public void setUsrStatus(String usrStatus) {
        this.usrStatus = usrStatus;
    }

    public String getPhoneAddress() {
        return phoneAddress;
    }

    public void setPhoneAddress(String phoneAddress) {
        this.phoneAddress = phoneAddress;
    }
}
