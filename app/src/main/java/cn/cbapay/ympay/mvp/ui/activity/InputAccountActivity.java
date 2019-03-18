package cn.cbapay.ympay.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.cbapay.ympay.R;
import cn.cbapay.ympay.app.MyApplication;
import cn.cbapay.ympay.bean.OriginalPhoneVerifyRequestBean;
import cn.cbapay.ympay.bean.OriginalPhoneVerifyResponseBean;
import cn.cbapay.ympay.bean.RequestBean;
import cn.cbapay.ympay.mvp.presenter.impl.VerifyOriginalPhoneCodePresenterImpl;
import cn.cbapay.ympay.mvp.presenter.SendOriginalPhoneCodePresenter;
import cn.cbapay.ympay.mvp.presenter.VerifyOriginalPhoneCodePresenter;
import cn.cbapay.ympay.mvp.presenter.impl.SendOriginalPhoneCodePresenterImpl;
import cn.cbapay.ympay.mvp.ui.base.BaseActivity;
import cn.cbapay.ympay.utils.GlobalData;
import cn.cbapay.ympay.utils.LogUtil;
import cn.cbapay.ympay.utils.ShareUtil;
import cn.cbapay.ympay.utils.Tools;

/**
 * 输入账户名页面
 * Created by icewater on 16/9/23.
 */

public class InputAccountActivity extends BaseActivity implements View.OnClickListener, SendOriginalPhoneCodePresenter.SendOriginalPhoneView, VerifyOriginalPhoneCodePresenter.VerifyOriginalPhoneCodeView {

    private Toolbar toolbar;
    private TextView toolbarTitle;

    private EditText editAccount;
    private EditText editCode;
    private Button buttonVerify;
    private Button buttonNext;
    private TimeCount timeCount;
    private String randomCode;

    private SendOriginalPhoneCodePresenter sendOriginalPhoneCodePresenter;
    private VerifyOriginalPhoneCodePresenter verifyOriginalPhoneCodePresenter;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_input_account;
    }

    @Override
    protected void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        editAccount = (EditText) findViewById(R.id.edit_account);
        editCode = (EditText) findViewById(R.id.edit_code);
        buttonVerify = (Button) findViewById(R.id.button_verify);
        buttonNext = (Button) findViewById(R.id.button_next);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        sendOriginalPhoneCodePresenter = new SendOriginalPhoneCodePresenterImpl(this);
        verifyOriginalPhoneCodePresenter = new VerifyOriginalPhoneCodePresenterImpl(this);
        toolbarTitle.setText("更换手机号");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (GlobalData.getStpusrinf() != null) {
            editAccount.setText(GlobalData.getStpusrinf().getUsrMobile());
        } else {
            editAccount.setText("11111111111");
        }
        editAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                verifyInput();
            }
        });
        editCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                verifyInput();
            }
        });
        buttonVerify.setOnClickListener(this);
        buttonNext.setOnClickListener(this);
        timeCount = new TimeCount(60000, 1000);
    }

    private void verifyInput() {
        if (editCode.length() > 0 && editAccount.length() == 11) {
            buttonNext.setEnabled(true);
        } else {
            buttonNext.setEnabled(false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_verify:
                if (editAccount.length() != 11) {
                    Toast.makeText(getApplicationContext(), "手机号格式不正确", Toast.LENGTH_SHORT).show();
                } else {
                    timeCount.start();
                    RequestBean bean = new RequestBean();
                    bean.setToken(ShareUtil.getValue("token", InputAccountActivity.this));
                    sendOriginalPhoneCodePresenter.sendMCode(bean);
                }
                break;
            case R.id.button_next:
                OriginalPhoneVerifyRequestBean bean = new OriginalPhoneVerifyRequestBean();
                bean.setValidateCode(editCode.getText().toString());
                bean.setToken(ShareUtil.getValue("token", InputAccountActivity.this));
                verifyOriginalPhoneCodePresenter.verifyCode(bean);
                break;
            default:
                break;
        }
    }

    @Override
    public void onSendOriginalPhoneSuccess() {
        Tools.showToast(this, getString(R.string.verify_code_send_phone));

    }

    @Override
    public void onSendOriginalPhoneFail(String msg) {
        Tools.showToast(this, msg);
    }

    @Override
    public void onVerifyOriginalPhoneCodeSuccess(OriginalPhoneVerifyResponseBean bean) {
        randomCode = bean.getRandomCode();
        Intent phoneIntent = new Intent();
        phoneIntent.setClass(this, InputPhoneActivity.class);
        phoneIntent.putExtra("randomCode", randomCode);
        LogUtil.i(MyApplication.RANDOM_CODE, "InputAccountActivity: onClick " + randomCode);
        startActivityForResult(phoneIntent, 1);
    }

    @Override
    public void onVerifyOriginalPhoneCodeFail(String msg) {
        Tools.showToast(this, msg);
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            // 计时完毕
            buttonVerify.setText(getString(R.string.verify_code_get));
            buttonVerify.setEnabled(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            // 计时过程
            buttonVerify.setEnabled(false);
            //防止重复点击
            buttonVerify.setText(millisUntilFinished / 1000 + "s");
        }
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
