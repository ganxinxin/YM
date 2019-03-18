package cn.cbapay.ympay.utils;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import cn.cbapay.ympay.R;

/**
 * Created by Administrator on 2016/9/28.
 */
public class TimerOut {
    private static TimeCount timeCount;
    private Context mContext;
    private static Button mBt;

    public TimerOut(Context context, Button bt) {
        this.mContext = context;
        this.mBt = bt;

    }

    public static void start(int sec) {
        timeCount = new TimeCount(sec*1000,1000);
        timeCount.start();
    }
    static class TimeCount extends CountDownTimer {


        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onFinish() {// 计时完毕
            mBt.setText("重新发送");
            mBt.setClickable(true);
            mBt.setTextColor(Color.RED);
            mBt.setBackgroundResource(R.mipmap.code_selected);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            mBt.setClickable(false);//防止重复点击
            mBt.setText(millisUntilFinished / 1000 + " s秒后重发");
            mBt.setTextColor(Color.WHITE);
            mBt.setBackgroundResource(R.mipmap.register);
        }
    }
}
