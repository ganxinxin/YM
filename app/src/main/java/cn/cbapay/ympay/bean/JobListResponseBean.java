package cn.cbapay.ympay.bean;

import java.util.List;

/**
 * accountInformation/allJob 接口响应参数
 * Created by icewater on 2016/10/24.
 */

public class JobListResponseBean {

    private List<CareerBean> list; // 职业列表

    public List<CareerBean> getList() {
        return list;
    }

    public void setList(List<CareerBean> list) {
        this.list = list;
    }
}
