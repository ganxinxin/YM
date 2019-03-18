package cn.cbapay.ympay.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by a on 2016/7/18.
 */
public class PhoneUtils {
    public static boolean isMobileNO(String mobiles){

        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(17[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();

        }
}
