package cn.cbapay.ympay.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/9/18.
 */
public class PassWordUtils {
    public static boolean isPassWordNO(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        for(int i=0 ; i<str.length() ;i++) {
            if (Character.isDigit(str.charAt(i))) {
                //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
                break;
            } else {
                isDigit = false;
            }
        }
            for(int i=0 ; i<str.length() ;i++) {
                if (Character.isLetter(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                    isLetter = true;
                    break;
                } else {
                    isLetter = false;
                }
            }

      if(isDigit&isLetter){
          return true;
      }else {
          return false;
      }


    }

    /**
     * 判断是否相同
     *
     * @param s
     * @return
     */
    public static boolean isSameSymbol(String s) {

        char chacter = s.charAt(0);
        for (int i = 1; i <= s.length() - 1; i++) {
            if (chacter != s.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否有顺序 true代表有顺序 false反之
     */
    public static boolean isOrdered(String s) {

        List<Integer> temp1 = new ArrayList<Integer>();
        List<Integer> temp2 = new ArrayList<Integer>();
        for (int i = 0; i < s.length(); i++) {
            temp1.add(Integer.valueOf(s.substring(i, i + 1)));
        }
        for (int i = 0; i < s.length(); i++) {
            temp2.add(Integer.valueOf(s.substring(i, i + 1)));
        }
        Collections.sort(temp1);
        StringBuffer orderedAsc = new StringBuffer();
        for (Integer i : temp1) {
            orderedAsc.append(i);
        }
        Collections.sort(temp2);
        Collections.reverse(temp2);
        StringBuffer orderedDec = new StringBuffer();
        for (Integer i : temp2) {
            orderedDec.append(i);
        }
        if (s.equals(orderedDec.substring(0)) || s.equals(orderedAsc.substring(0))) {

            return true;
        }
        return false;
    }


}
