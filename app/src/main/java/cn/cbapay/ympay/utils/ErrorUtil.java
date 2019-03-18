package cn.cbapay.ympay.utils;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

import cn.cbapay.ympay.app.MyApplication;

/**
 * 错误码详细信息
 * @author ZhouAng
 *
 */
public class ErrorUtil {

    private static Map<String, String> errorMap = new HashMap<>();

    static {
        errorMap.put("1", "成功");
        errorMap.put("2", "登录超时，请重新登录");
        errorMap.put("3", "参数输入不完整");
        errorMap.put("4", "数据库操作出现异常");
        errorMap.put("5", "用户存在");
        errorMap.put("6", "用户不存在");
        errorMap.put("7", "输入的身份证号与实名信息不一致");
        errorMap.put("8", "支付密码和登录密码不能一致");
        errorMap.put("9", "新的支付密码和登录密码不能一致");
        errorMap.put("10", "支付密码修改失败");
        errorMap.put("11", "新的登录密码和原登录密码不能一致");
        errorMap.put("12", "修改登录密码失败");
        errorMap.put("13", "手机号已注册(登录和修改都可以用)");
        errorMap.put("14", "该用户未申请校验码");
        errorMap.put("15", "验证码已过期");
        errorMap.put("16", "校验码错误，请重新输入");
        errorMap.put("17", "插入失败（适用于各种插入操作）");
        errorMap.put("18", "邮箱未激活！不能登录");
        errorMap.put("19", "登录密码多次输入错误，账户已锁定，请3小时以后再试");
        errorMap.put("20", "密码错误，还可以输入\"+N+\"次\"");
        errorMap.put("21", "修改地区信息失败");
        errorMap.put("22", "职业信息不存在");
        errorMap.put("23", "修改职业信息失败");
        errorMap.put("24", "发送验证码相关操作失败");
        errorMap.put("25", "更改邮箱失败");
        errorMap.put("26", "登录密码不正确");
        errorMap.put("27", "更改手机号失败");
        errorMap.put("28", "证件信息不存在");
        errorMap.put("29", "查询认证渠道错误");
        errorMap.put("30", "验证手机运营商失败");
        errorMap.put("31", "新手机号认证未通过，请绑定您本人的手机号");
        errorMap.put("32", "短信验证码发送失败");
        errorMap.put("34", "邮箱验证码发送失败");
        errorMap.put("36", "请输入您上次收到的短信验证码");
        errorMap.put("37", "获取验证码失败");
        errorMap.put("38", "ftp关闭异常");
        errorMap.put("39", "图片上传失败");
        errorMap.put("40", "您的账户已在另一台设备登录，请重新登录");
        errorMap.put("41", "登录超时，请重新登录");
        errorMap.put("42", "将图片转换成流失败");
        errorMap.put("43", "用户未实名");
        errorMap.put("44", "解密失败");
        errorMap.put("45", "保存证件信息失败");
        errorMap.put("46", "暂不支持该银行卡，请使用其他银行卡");
        errorMap.put("47", "已绑定该银行卡");
        errorMap.put("48", "未设置支付密码");
        errorMap.put("49", "该账户未实名认证");
        errorMap.put("53", "首次实名绑定银行卡失败");
        errorMap.put("54", "插入实名信息失败");
        errorMap.put("55", "账户关联实名信息失败");
        errorMap.put("56", "真实姓名与已存在的实名信息不一致");
        errorMap.put("57", "该实名下绑定账号已达到上限");
        errorMap.put("58", "缺少必填字段");
        errorMap.put("60", "身份证号格式不正确");
    }

    public static String getErrorMsg (String code){
        if (TextUtils.isEmpty(code)) {
            return "";
        }
        if (errorMap.containsKey(code)) {
            return errorMap.get(code);
        }
        return "";
    }

    public static void checkError(String code){
        if ("2".equals(code)||"40".equals(code)||"41".equals(code)){
            MyApplication.StartLogin();
        }
    }
}
