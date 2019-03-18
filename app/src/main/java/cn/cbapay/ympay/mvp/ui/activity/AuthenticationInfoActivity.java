package cn.cbapay.ympay.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.cbapay.ympay.R;
import cn.cbapay.ympay.mvp.ui.base.BaseActivity;

/**
 * 实名认证首页
 * Created by icewater on 16/9/26.
 */

public class AuthenticationInfoActivity extends BaseActivity {

    private Toolbar toolbar;
    private TextView toolbarTitle;

    private Button buttonNext;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_authentication_info;
    }

    @Override
    protected void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        buttonNext = (Button) findViewById(R.id.button_next);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        toolbarTitle.setText("实名认证");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(InputCardNumberActivity.class);
            }
        });
    }
}
