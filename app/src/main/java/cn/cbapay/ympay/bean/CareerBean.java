package cn.cbapay.ympay.bean;

import java.io.Serializable;

/**
 * 职业
 * Created by icewater on 16/9/20.
 */
public class CareerBean implements Serializable {
    private String id;
    private boolean isNewRecord;
    private String usrJob;

    public CareerBean(String id, String usrJob) {
        this.id = id;
        this.usrJob = usrJob;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isNewRecord() {
        return isNewRecord;
    }

    public void setNewRecord(boolean newRecord) {
        isNewRecord = newRecord;
    }

    public String getUsrJob() {
        return usrJob;
    }

    public void setUsrJob(String usrJob) {
        this.usrJob = usrJob;
    }
}


