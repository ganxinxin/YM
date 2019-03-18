package cn.cbapay.ympay.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.cbapay.ympay.R;
import cn.cbapay.ympay.mvp.ui.base.BaseActivity;

/**
 * 已实名修改手机号
 * Created by icewater on 16/9/23.
 */

public class ChangePhoneActivity extends BaseActivity {

    private Toolbar toolbar;
    private TextView toolbarTitle;

    private LinearLayout layPhoneCanUse;
    private LinearLayout layPhoneCannotUse;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_change_phone;
    }

    @Override
    protected void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        layPhoneCanUse = (LinearLayout) findViewById(R.id.lay_phone_can_use);
        layPhoneCannotUse = (LinearLayout) findViewById(R.id.lay_phone_cannot_use);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        toolbarTitle.setText("更换手机号");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        layPhoneCanUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phoneIntent = new Intent();
                phoneIntent.setClass(ChangePhoneActivity.this, InputAccountActivity.class);
                startActivityForResult(phoneIntent, 1);
            }
        });
        layPhoneCannotUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phoneIntent = new Intent();
                phoneIntent.setClass(ChangePhoneActivity.this, InputPayPasswordActivity.class);
                startActivityForResult(phoneIntent, 2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                case 2:
                    if (data != null) {
                        setResult(RESULT_OK, data);
                        finish();
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
