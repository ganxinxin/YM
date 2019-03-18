package cn.cbapay.ympay.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.cbapay.ympay.R;
import cn.cbapay.ympay.bean.EmailVerifyRequestBean;
import cn.cbapay.ympay.bean.GetNewPhoneVerifyCodeRequestBean;
import cn.cbapay.ympay.bean.GetNewPhoneVerifyCodeResponseBean;
import cn.cbapay.ympay.mvp.presenter.SendPhoneOrEmailCodePresenter;
import cn.cbapay.ympay.mvp.presenter.VerifyEmailPresenter;
import cn.cbapay.ympay.mvp.presenter.impl.SendPhoneOrEmailCodePresenterImpl;
import cn.cbapay.ympay.mvp.presenter.impl.VerifyEmailPresenterImpl;
import cn.cbapay.ympay.mvp.ui.base.BaseActivity;
import cn.cbapay.ympay.utils.GlobalData;
import cn.cbapay.ympay.utils.ShareUtil;
import cn.cbapay.ympay.utils.Tools;

/**
 * 邮箱设置页面
 * Created by icewater on 16/9/21.
 */

public class EmailActivity extends BaseActivity implements View.OnClickListener, SendPhoneOrEmailCodePresenter.SendPhoneOrEmailView, VerifyEmailPresenter.VerifyEmailView {
    private static final String TAG = EmailActivity.class.getSimpleName();

    private Toolbar toolbar;
    private TextView toolbarTitle;

    private EditText editEmail;
    private EditText editCode;
    private Button buttonVerify;
    private Button buttonOk;
    private String inputEmail;
    private SendPhoneOrEmailCodePresenter sendPhoneOrEmailCodePresenter;
    private VerifyEmailPresenter verifyEmailPresenter;
    private TimeCount timeCount;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_email;
    }

    @Override
    protected void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        editEmail = (EditText) findViewById(R.id.edit_email);
        editCode = (EditText) findViewById(R.id.edit_code);
        buttonVerify = (Button) findViewById(R.id.button_verify);
        buttonOk = (Button) findViewById(R.id.button_ok);

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        toolbarTitle.setText(getString(R.string.email_title));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        editCode.addTextChangedListener(mTextWatcher);
        editEmail.addTextChangedListener(mTextWatcher);
        buttonVerify.setOnClickListener(this);
        buttonOk.setOnClickListener(this);
        sendPhoneOrEmailCodePresenter = new SendPhoneOrEmailCodePresenterImpl(this);
        verifyEmailPresenter = new VerifyEmailPresenterImpl(this);
        timeCount = new TimeCount(60000, 1000);
    }

    public boolean isEmailLegal(String email) {
        String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();

    }

    TextWatcher mTextWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!TextUtils.isEmpty(editEmail.getText())) {
                if (getString(R.string.verify_code_get).equals(buttonVerify.getText().toString())) {
                    buttonVerify.setEnabled(true);
                }
                if (!TextUtils.isEmpty(editCode.getText())) {
                    buttonOk.setEnabled(true);
                } else {
                    buttonOk.setEnabled(false);
                }
            } else {
                buttonVerify.setEnabled(false);
                buttonOk.setEnabled(false);
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_ok:
                inputEmail = editEmail.getText().toString();
                if (isEmailLegal(inputEmail)) {
                    EmailVerifyRequestBean bean = new EmailVerifyRequestBean();
                    bean.setToken(ShareUtil.getValue("token", EmailActivity.this));
                    bean.setValidateCode(editCode.getText().toString());
                    bean.setEmail(inputEmail);
                    verifyEmailPresenter.verifyEmail(bean);
                } else {
                    Tools.showToast(this, getString(R.string.email_ilegal));
                }
                break;
            case R.id.button_verify:
                if (isEmailLegal(editEmail.getText().toString())) {
                    timeCount.start();
                    GetNewPhoneVerifyCodeRequestBean bean = new GetNewPhoneVerifyCodeRequestBean();
                    bean.setToken(ShareUtil.getValue("token", EmailActivity.this));
                    bean.setmOrE(editEmail.getText().toString());
                    sendPhoneOrEmailCodePresenter.sendPhoneOrEmailCode(bean);
                } else {
                    Tools.showToast(this, getString(R.string.email_ilegal));
                }
                break;
        }
    }


    @Override
    public void sendCodeSuccess(GetNewPhoneVerifyCodeResponseBean bean) {
        Tools.showToast(this, getString(R.string.verify_code_send));
    }

    @Override
    public void sendCodeFail(String msg) {
        Tools.showToast(this, msg);
    }

    @Override
    public void onVerifyEmailSuccess() {
        Intent intent = new Intent();
        intent.putExtra("email", inputEmail);
        setResult(RESULT_OK, intent);
        if (GlobalData.getStpusrinf() != null) {
            GlobalData.getStpusrinf().setUsrEmail(inputEmail);
        }
        finish();
    }

    @Override
    public void onVerifyEmailFail(String msg) {
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
    protected void onDestroy() {
        super.onDestroy();
        sendPhoneOrEmailCodePresenter.onDestroy();
        verifyEmailPresenter.onDestroy();
    }
}
