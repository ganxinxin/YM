package cn.cbapay.ympay.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xuetao on 16/4/6.
 */
public class Tools {


    private static Toast toast;

    public static void showToast(Context context, String message) {
        if (toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 格式化身份证号码
     *
     * @param idCardNum
     * @return
     */
    public static String formatIdCardNum(String idCardNum) {
        return idCardNum.substring(0, 10) + "****" + idCardNum.substring(14);
    }

    /**
     * 格式化手机号码
     *
     * @param phoneNum
     * @return
     */
    public static String formatPhoneNum(String phoneNum) {
        return phoneNum.substring(0, 3) + "****" + phoneNum.substring(7);
    }

    public static void showDialog(String info, Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage(info);
            builder.setTitle("提示");
            builder.setCancelable(false);
            builder.setPositiveButton("确认", null).create().show();
        }
    }

    public static void showFinishDialog(String info, final Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage(info);
            builder.setTitle("提示");
            builder.setCancelable(false);
            builder.setPositiveButton("确认", new AlertDialog.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    activity.finish();
                }
            }).create().show();
        }
    }

    /**
     * 银行卡四位加空格
     *
     * @param mEditText
     */
    public static void bankCardNumAddSpace(final EditText mEditText) {
        mEditText.addTextChangedListener(new TextWatcher() {
            int beforeTextLength = 0;
            int onTextLength = 0;
            boolean isChanged = false;

            int location = 0;//记录光标的位置
            private char[] tempChar;
            private StringBuffer buffer = new StringBuffer();
            int spaceNumberB = 0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                beforeTextLength = s.length();
                if (buffer.length() > 0) {
                    buffer.delete(0, buffer.length());
                }
                spaceNumberB = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == ' ') {
                        spaceNumberB++;
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                onTextLength = s.length();
                buffer.append(s.toString());
                if (onTextLength == beforeTextLength || onTextLength <= 3 || isChanged) {
                    isChanged = false;
                    return;
                }
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isChanged) {
                    location = mEditText.getSelectionEnd();
                    int index = 0;
                    while (index < buffer.length()) {
                        if (buffer.charAt(index) == ' ') {
                            buffer.deleteCharAt(index);
                        } else {
                            index++;
                        }
                    }

                    index = 0;
                    int spaceNumberC = 0;
                    while (index < buffer.length()) {
                        if ((index == 4 || index == 9 || index == 14 || index == 19)) {
                            buffer.insert(index, ' ');
                            spaceNumberC++;
                        }
                        index++;
                    }

                    if (spaceNumberC > spaceNumberB) {
                        location += (spaceNumberC - spaceNumberB);
                    }

                    tempChar = new char[buffer.length()];
                    buffer.getChars(0, buffer.length(), tempChar, 0);
                    String str = buffer.toString();
                    if (location > str.length()) {
                        location = str.length();
                    } else if (location < 0) {
                        location = 0;
                    }

                    mEditText.setText(str);
                    Editable editable = mEditText.getText();
                    Selection.setSelection(editable, location);
                    isChanged = false;
                }
            }
        });
    }

    /**
     * 保留两位小数
     *
     * @param s
     * @return
     */
    public static String formatMoney(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        NumberFormat formater = null;
        double num = Double.parseDouble(s);
        formater = new DecimalFormat("######0.00");
        return formater.format(num);
    }

    /**
     * 保留两位小数
     *
     * @param money
     * @return
     */
    public static String formatMoney(double money) {
        String s = String.valueOf(money);
        return formatMoney(s);
    }

    /**
     * **** **** **** 0655 格式化这种格式
     *
     * @param cardNum
     * @return
     */
    public static String formatBankcardNum(String cardNum) {
        if (cardNum == null || cardNum.length() < 4) {
            return "";
        }
        int length = cardNum.length();
        return "**** **** **** " + cardNum.substring(length - 4, length);
    }


    public static String getStringToday() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss",
                Locale.getDefault());
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static String getStringToday(String template) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(template,
                Locale.getDefault());
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static void formatPriceEditText(final EditText editText) {

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    } else if ((s.length() - s.toString().replace(".", "").length()) == 2) {
                        // 输入了两个点
                        s = s.subSequence(0, s.length() - 1);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }

                if (s.toString().contains("-")) {
                    if (s.toString().trim().substring(0, 1).equals("-")) {
                        // -必须放在开头
                        if ((s.length() - s.toString().replace("-", "").length()) == 2) {
                            // 输入了两个-
                            s = s.subSequence(0, s.length() - 1);
                            editText.setText(s);
                            editText.setSelection(s.length());
                        }

                    } else {
                        s = s.subSequence(0, s.length() - 1);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }

                }

                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(2);
                }

                if (s.toString().startsWith("0") && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                        return;
                    }
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public static ExecutorService FULL_TASK_EXECUTOR;

    static {
        FULL_TASK_EXECUTOR = Executors.newCachedThreadPool();
    }
}
