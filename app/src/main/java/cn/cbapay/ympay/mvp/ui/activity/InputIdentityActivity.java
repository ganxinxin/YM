package cn.cbapay.ympay.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.cbapay.ympay.R;
import cn.cbapay.ympay.app.MyApplication;
import cn.cbapay.ympay.bean.IDVerifyRequestBean;
import cn.cbapay.ympay.bean.IDVerifyRespondBean;
import cn.cbapay.ympay.mvp.presenter.VerifyIDPresenter;
import cn.cbapay.ympay.mvp.presenter.impl.VerifyIDPresenterImpl;
import cn.cbapay.ympay.mvp.ui.base.BaseActivity;
import cn.cbapay.ympay.utils.GlobalData;
import cn.cbapay.ympay.utils.LogUtil;
import cn.cbapay.ympay.utils.ShareUtil;
import cn.cbapay.ympay.utils.Tools;

/**
 * 身份证号输入页面
 * Created by icewater on 16/9/23.
 */

public class InputIdentityActivity extends BaseActivity implements VerifyIDPresenter.VerifyIDView {

    private Toolbar toolbar;
    private TextView toolbarTitle;

    private EditText editIdentity;
    private Button buttonNext;
    private String randomCode;
    private VerifyIDPresenter verifyIDPresenter;

    @Override
    protected void onNewIntent(Intent intent) {
        if (intent != null) {
            randomCode = intent.getStringExtra("randomCode");
        }
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_input_identity;
    }

    @Override
    protected void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        editIdentity = (EditText) findViewById(R.id.edit_identity);
        buttonNext = (Button) findViewById(R.id.button_next);
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
        verifyIDPresenter = new VerifyIDPresenterImpl(this);
        if (getIntent() != null) {
            randomCode = getIntent().getStringExtra("randomCode");
        }
        editIdentity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editIdentity.length() == 18) {
                    buttonNext.setEnabled(true);
                } else {
                    buttonNext.setEnabled(false);
                }
            }
        });
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IDVerifyRequestBean bean = new IDVerifyRequestBean();
                String custLogin = null;
                if (GlobalData.getStpusrinf() != null) {
                    custLogin = GlobalData.getStpusrinf().getCustLogin();
                } else {
                    custLogin = "13396483666";
                }
                bean.setToken(ShareUtil.getValue("token", InputIdentityActivity.this));
                bean.setRandomCode(randomCode);
                bean.setIdCard(editIdentity.getText().toString());
                bean.setCustLogin(custLogin);
                LogUtil.i(MyApplication.RANDOM_CODE, "InputIdentityActivity: buttonNext " + randomCode);
                verifyIDPresenter.verifyID(bean);
            }
        });
    }

    @Override
    public void onVerifyIDSuccess(IDVerifyRespondBean bean) {
        randomCode = bean.getRandomCode();
        Intent phoneIntent = new Intent();
        phoneIntent.setClass(this, InputPhoneActivity.class);
        phoneIntent.putExtra("randomCode", randomCode);
        LogUtil.i(MyApplication.RANDOM_CODE, "InputIdentityActivity: onVerifyIDSuccess " + randomCode);
        startActivityForResult(phoneIntent, 1);
    }

    @Override
    public void onVerifyIDFail(String msg) {
        Tools.showToast(this, msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
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
