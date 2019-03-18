package cn.cbapay.ympay.mvp.model;

/**
 * Created by xuetao on 16/4/7.
 */
public interface DataModel {

    void setValue(String key, Object value);
    Object getValue(String key, Object defaultObject);

}
