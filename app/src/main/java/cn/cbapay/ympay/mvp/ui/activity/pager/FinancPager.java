package cn.cbapay.ympay.mvp.ui.activity.pager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import cn.cbapay.ympay.mvp.ui.base.BasePager;

/**
 * Created by Administrator on 2016/9/19.
 */
public class FinancPager extends BasePager {
    public FinancPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        return super.initView();
    }

    @Override
    public void initData() {
        TextView tv = new TextView(mActivity);
        tv.setText("理财");
        tv.setTextColor(Color.RED);
        tv.setTextSize(28);
        tv.setGravity(Gravity.CENTER);
        fl_contaner.addView(tv);
    }
}
