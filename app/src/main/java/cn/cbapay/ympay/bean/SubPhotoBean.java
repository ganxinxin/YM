package cn.cbapay.ympay.bean;

/**
 * Created by Administrator on 2016/10/24.
 * wangkezheng
 */
public class SubPhotoBean {
    private String token;
    private String validity;
    private String validityStart;
    private String front;
    private String back;
    private String handheld;

    public SubPhotoBean() {
    }

    public SubPhotoBean(String token, String validity, String validityStart, String front, String back, String handheld) {
        this.token = token;
        this.validity = validity;
        this.validityStart = validityStart;
        this.front = front;
        this.back = back;
        this.handheld = handheld;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getValidityStart() {
        return validityStart;
    }

    public void setValidityStart(String validityStart) {
        this.validityStart = validityStart;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public String getHandheld() {
        return handheld;
    }

    public void setHandheld(String handheld) {
        this.handheld = handheld;
    }
}
