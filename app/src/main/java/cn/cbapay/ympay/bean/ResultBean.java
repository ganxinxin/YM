package cn.cbapay.ympay.bean;

import com.google.gson.JsonObject;

/**
 * 请求返回结果
 * Created by xuetao on 16/4/1.
 */
public class ResultBean {
    private String resCode;
    private String resMsg;
    private JsonObject data;
    private String jsonData;

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public JsonObject getData() {
        return data;
    }

    public void setData(JsonObject data) {
        this.data = data;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "resCode=" + resCode +
                ", resMsg='" + resMsg + '\'' +
                ", data=" + data +
                ", jsonData='" + jsonData + '\'' +
                '}';
    }
}
