package cn.cbapay.ympay.mvp.model.impl;

import cn.cbapay.ympay.app.MyApplication;
import cn.cbapay.ympay.mvp.model.DataModel;
import cn.cbapay.ympay.utils.SPUtils;

/**
 * Created by xuetao on 16/4/7.
 */
public class SharedPreferencesModel implements DataModel {

    @Override
    public void setValue(String key, Object value) {
        SPUtils.put(MyApplication.getContext(), key, value);
    }

    @Override
    public Object getValue(String key, Object defaultObject) {
        return SPUtils.get(MyApplication.getContext(), key, defaultObject);
    }
}
