package cn.cbapay.ympay.bean;

/**
 * Created by Administrator on 2016/10/24.
 */
public class LoginResultBean {
    private String payPwdStatus;
    private String token;
    private ArpAcProfileBean arpAcProfile; // 账户主档案表数据
    private StpusrinfBean stpusrinf; // 用户表数据
    private StpcusinfBean stpcusinf; // 实名表数据
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public String getPayPwdStatus() {
        return payPwdStatus;
    }

    public void setPayPwdStatus(String payPwdStatus) {
        this.payPwdStatus = payPwdStatus;
    }
    public ArpAcProfileBean getArpAcProfile() {
        return arpAcProfile;
    }

    public void setArpAcProfile(ArpAcProfileBean arpAcProfile) {
        this.arpAcProfile = arpAcProfile;
    }

    public StpusrinfBean getStpusrinf() {
        return stpusrinf;
    }

    public void setStpusrinf(StpusrinfBean stpusrinf) {
        this.stpusrinf = stpusrinf;
    }

    public StpcusinfBean getStpcusinf() {
        return stpcusinf;
    }

    public void setStpcusinf(StpcusinfBean stpcusinf) {
        this.stpcusinf = stpcusinf;
    }
}
