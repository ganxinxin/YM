package cn.cbapay.ympay.bean;

import cn.cbapay.ympay.utils.PinyinUtil;

/**
 * Created by Administrator on 2016/9/21.
 */
public class ProvincesBean implements Comparable<ProvincesBean>{

    private String name;
    private String pinyin;

    public ProvincesBean(String name) {
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
    public int compareTo(ProvincesBean another) {
        return this.pinyin.compareTo(another.pinyin);
    }


}
