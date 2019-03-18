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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.cbapay.ympay.R;
import cn.cbapay.ympay.app.MyApplication;
import cn.cbapay.ympay.bean.GetNewPhoneVerifyCodeRequestBean;
import cn.cbapay.ympay.bean.GetNewPhoneVerifyCodeResponseBean;
import cn.cbapay.ympay.bean.NewPhoneVerifyRequestBean;
import cn.cbapay.ympay.mvp.presenter.SendPhoneOrEmailCodePresenter;
import cn.cbapay.ympay.mvp.presenter.VerifyPhonePresenter;
import cn.cbapay.ympay.mvp.presenter.impl.SendPhoneOrEmailCodePresenterImpl;
import cn.cbapay.ympay.mvp.presenter.impl.VerifyPhonePresenterImpl;
import cn.cbapay.ympay.mvp.ui.base.BaseActivity;
import cn.cbapay.ympay.utils.GlobalData;
import cn.cbapay.ympay.utils.LogUtil;
import cn.cbapay.ympay.utils.ShareUtil;
import cn.cbapay.ympay.utils.Tools;

/**
 * 输入手机号页面
 * Created by icewater on 16/9/22.
 */

public class InputPhoneActivity extends BaseActivity implements View.OnClickListener, SendPhoneOrEmailCodePresenter.SendPhoneOrEmailView, VerifyPhonePresenter.VerifyPhoneView {
    private static final String TAG = InputPhoneActivity.class.getSimpleName();
    private Toolbar toolbar;
    private TextView toolbarTitle;

    private EditText editPhone;
    private EditText editCode;
    private Button buttonVerify;
    private Button buttonOk;
    private String sendRandomCode;
    private String verifyRandomCode;
    private String inputPhone;

    //    private TimeCount timeCount;
    private VerifyPhonePresenter verifyPhonePresenter;
    private SendPhoneOrEmailCodePresenter sendPhoneOrEmailCodePresenter;

    @Override
    protected void onNewIntent(Intent intent) {
        if (intent != null) {
            sendRandomCode = intent.getStringExtra("randomCode");
        }
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_input_phone;
    }

    @Override
    protected void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        editPhone = (EditText) findViewById(R.id.edit_phone);
        editCode = (EditText) findViewById(R.id.edit_code);
        buttonVerify = (Button) findViewById(R.id.button_verify);
        buttonOk = (Button) findViewById(R.id.button_ok);

    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (isPhoneIlegal(editPhone.getText().toString())) {
                if (editCode.getText().toString().length() == 6) {
                    buttonOk.setEnabled(true);
                } else {
                    buttonOk.setEnabled(false);
                }
                buttonVerify.setEnabled(true);
            } else {
                buttonOk.setEnabled(false);
                buttonVerify.setEnabled(false);
            }
        }
    };

    @Override
    protected void initData(Bundle savedInstanceState) {
        toolbarTitle.setText("更换手机号");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (getIntent() != null) {
            sendRandomCode = getIntent().getStringExtra("randomCode");
        }
//        timeCount = new TimeCount(60000, 1000);
        verifyPhonePresenter = new VerifyPhonePresenterImpl(this);
        sendPhoneOrEmailCodePresenter = new SendPhoneOrEmailCodePresenterImpl(this);
        editPhone.addTextChangedListener(textWatcher);
        editCode.addTextChangedListener(textWatcher);
        buttonOk.setOnClickListener(this);
        buttonVerify.setOnClickListener(this);
    }

    @Override
    public void sendCodeSuccess(GetNewPhoneVerifyCodeResponseBean bean) {
        verifyRandomCode = bean.getRandomCode();
        LogUtil.i(MyApplication.RANDOM_CODE, TAG + ":" + "sendCodeSuccess:" + verifyRandomCode);
    }

    @Override
    public void sendCodeFail(String msg) {
        Tools.showToast(this, msg);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_ok:
                inputPhone = editPhone.getText().toString();
                if (isPhoneIlegal(inputPhone)) {
                    NewPhoneVerifyRequestBean bean = new NewPhoneVerifyRequestBean();
                    bean.setToken(ShareUtil.getValue("token",InputPhoneActivity.this));
                    bean.setValidateCode(editCode.getText().toString());
                    bean.setNewM(inputPhone);
                    bean.setRandomCode(verifyRandomCode);
                    LogUtil.i(MyApplication.RANDOM_CODE, TAG + ":" + "button_ok:" + verifyRandomCode);
                    verifyPhonePresenter.verifyPhone(bean);
                } else {
                    Tools.showToast(this, getString(R.string.phone_ilegal));
                }
                break;
            case R.id.button_verify:
                if (isPhoneIlegal(editPhone.getText().toString())) {
//                    timeCount.start();
                    GetNewPhoneVerifyCodeRequestBean bean = new GetNewPhoneVerifyCodeRequestBean();
                    bean.setToken(ShareUtil.getValue("token",InputPhoneActivity.this));
                    bean.setmOrE(editPhone.getText().toString());
                    bean.setRandomCode(sendRandomCode);
                    LogUtil.i(MyApplication.RANDOM_CODE, TAG + ":" + "button_verify:" + sendRandomCode);
                    sendPhoneOrEmailCodePresenter.sendPhoneOrEmailCode(bean);
                } else {
                    Tools.showToast(this, getString(R.string.phone_ilegal));
                }

                break;
            default:
                break;
        }
    }

    public boolean isPhoneIlegal(String phone) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    @Override
    public void verifyPhoneSuccess() {
        Intent intent = new Intent();
        intent.putExtra("phone", inputPhone);
        setResult(RESULT_OK, intent);
        if (GlobalData.getStpusrinf() != null) {
            GlobalData.getStpusrinf().setUsrMobile(inputPhone);
        }
        Tools.showToast(this,getString(R.string.change_phone_success));
        finish();
    }

    @Override
    public void verifyPhoneFail(String msg) {
        Tools.showToast(this, msg);
    }

//    class TimeCount extends CountDownTimer {
//        public TimeCount(long millisInFuture, long countDownInterval) {
//            super(millisInFuture, countDownInterval);
//        }
//
//        @Override
//        public void onFinish() {
//            // 计时完毕
//            buttonVerify.setText(getString(R.string.verify_code_get));
//            buttonVerify.setEnabled(true);
//        }
//
//        @Override
//        public void onTick(long millisUntilFinished) {
//            // 计时过程
//            buttonVerify.setEnabled(false);
//            //防止重复点击
//            buttonVerify.setText(millisUntilFinished / 1000 + "s");
//        }
//    }
}
