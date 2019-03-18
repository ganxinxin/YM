package cn.cbapay.ympay.utils;


import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {
    private static final String TAG = PinyinUtil.class.getSimpleName();

    /**
     * 根据指定的汉字字符串, 返回其对应的拼音
     *
     * @param string
     * @return
     */
    public static String getPinyin(String string) {

        //特殊的多音字处理
        switch (string.trim()) {
            case "重庆":
                return "CHONGQING";
            default:
                break;
        }
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        // 不需要音标
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        // 设置转换出大写字母
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);

        char[] charArray = string.toCharArray();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];

            // 如果是空格, 跳过当前循环
            if (Character.isWhitespace(c)) {
                continue;
            }

            if (c >= -128 && c < 127) {
                // 不可能是汉字, 直接拼接
                sb.append(c);
            } else {
                try {
                    // 获取某个字符对应的拼音. 可以获取到多音字. 单 ->DAN, SHAN
                    String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(c, format);
                    if (pinyin != null && pinyin.length > 0) {
                        sb.append(pinyin[0]);
                    } else {
                        LogUtil.e(TAG, "Error:" + string);
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            }

        }

        return sb.toString();
    }

}
