package cn.cbapay.ympay.bean;

/**
 * /user/findCustomerInfo 接口响应参数
 * Created by icewater on 2016/10/24.
 */

public class CustomerInfoResponseBean {
    private String payPwdStatus;// 支付密码设置状态 -1未设置，1已设置
    private ArpAcProfileBean arpAcProfile; // 账户主档案表数据
    private StpusrinfBean stpusrinf; // 用户表数据
    private StpcusinfBean stpcusinf; // 实名表数据

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

    public String getPayPwdStatus() {
        return payPwdStatus;
    }

    public void setPayPwdStatus(String payPwdStatus) {
        this.payPwdStatus = payPwdStatus;
    }
}
