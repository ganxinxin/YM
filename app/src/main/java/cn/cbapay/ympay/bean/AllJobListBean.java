package cn.cbapay.ympay.bean;

import java.util.List;

/**
 * Created by YangHongfei on 2016/10/26.
 */
public class AllJobListBean {

    /**
     * isNewRecord : true
     * usrJob : 生产/运输工人
     */

    private List<CareerBean> list;

    public List<CareerBean> getList() {
        return list;
    }

    public void setList(List<CareerBean> list) {
        this.list = list;
    }
}
