package cn.cbapay.ympay.bean;

/**
 * accountInformation/updateUsrJob 接口请求参数
 * Created by icewater on 2016/10/24.
 */

public class CareerUpdateRequestBean {
    private String token;
    private String usrJob;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsrJob() {
        return usrJob;
    }

    public void setUsrJob(String usrJob) {
        this.usrJob = usrJob;
    }
}
