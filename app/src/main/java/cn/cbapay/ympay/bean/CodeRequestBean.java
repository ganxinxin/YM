package cn.cbapay.ympay.bean;

/**
 * Created by xuetao on 16/4/7.
 */
public class CodeRequestBean {

    public static final String TYPE_AUTH = "1"; // 认证
    public static final String TYPE_REGISTER = "2"; // 注册
    public static final String TYPE_MODIFTY_PWD = "3"; // 修改密码
    public static final String TYPE_TRADE = "4"; // 交易

    private String type; // 验证码类型
    private String mobile;// 手机号
    private String memberId; // 会员ID 如果是注册验证码，则不填；其他类型的验证码必填
    private String payOrderId;// 订单号

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId;
    }
}
