package cn.cbapay.ympay.network.api;

import cn.cbapay.ympay.bean.ResultBean;
import rx.Observable;

public interface HttpApi {

    Observable<ResultBean> call(String params);

}
