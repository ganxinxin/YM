package cn.cbapay.ympay.bean;

import cn.cbapay.ympay.utils.PinyinUtil;

/**
 * Created by YangHongfei on 2016/10/27.
 */
public class CityNameBean implements Comparable<CityNameBean> {
    private String name;
    private String pinyin;

    public CityNameBean(String name) {
        super();
        this.name = name;
        this.pinyin = PinyinUtil.getPinyin(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Override
    public int compareTo(CityNameBean another) {
        return this.pinyin.compareTo(another.pinyin);
    }


}
