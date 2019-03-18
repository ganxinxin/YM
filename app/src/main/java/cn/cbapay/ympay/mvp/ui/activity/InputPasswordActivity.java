package cn.cbapay.ympay.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import cn.cbapay.ympay.R;
import cn.cbapay.ympay.app.MyApplication;
import cn.cbapay.ympay.bean.LoginPasswordVerifyRequestBean;
import cn.cbapay.ympay.bean.LoginPasswordVerifyResponseBean;
import cn.cbapay.ympay.mvp.presenter.VerifyLoginPasswordPresenter;
import cn.cbapay.ympay.mvp.presenter.impl.VerifyLoginPasswordPresenterImpl;
import cn.cbapay.ympay.mvp.ui.base.BaseActivity;
import cn.cbapay.ympay.utils.LogUtil;
import cn.cbapay.ympay.utils.ShareUtil;
import cn.cbapay.ympay.utils.Tools;

/**
 * 输入登录密码 修改手机号-未实名时
 * Created by icewater on 16/9/18.
 */
public class InputPasswordActivity extends BaseActivity implements View.OnClickListener, VerifyLoginPasswordPresenter.VerifyPasswordView {
    private static final String TAG = InputPasswordActivity.class.getSimpleName();
    private Toolbar toolbar;
    private TextView toolbarTitle;

    private EditText editPassword;
    private ImageView imageShow;
    private Button buttonNext;
    private VerifyLoginPasswordPresenter verifyLoginPasswordPresenter;
    private boolean hide = true;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_input_password;
    }

    @Override
    protected void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        editPassword = (EditText) findViewById(R.id.edit_password);
        imageShow = (ImageView) findViewById(R.id.image_show);
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
        verifyLoginPasswordPresenter = new VerifyLoginPasswordPresenterImpl(this);
        imageShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hide) {
                    editPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    hide = false;
                    editPassword.setSelection(editPassword.length());
                    imageShow.setImageResource(R.mipmap.ic_open);
                } else {
                    editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    hide = true;
                    editPassword.setSelection(editPassword.length());
                    imageShow.setImageResource(R.mipmap.ic_close);
                }
            }
        });
        editPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editPassword.length() > 5) {
                    buttonNext.setEnabled(true);
                } else {
                    buttonNext.setEnabled(false);
                }
            }
        });
        buttonNext.setOnClickListener(this);
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

    @Override
    public void onVerifyPasswordSuccess(LoginPasswordVerifyResponseBean bean) {
        Intent phoneIntent = new Intent();
        phoneIntent.setClass(this, InputPhoneActivity.class);
        phoneIntent.putExtra("randomCode", bean.getRandomCode());
        LogUtil.i(MyApplication.RANDOM_CODE, "InputPasswordActivity: onVerifyPasswordSuccess " + bean.getRandomCode());
        startActivityForResult(phoneIntent, 1);
    }

    @Override
    public void onVerifyPasswordFail(String code, String msg) {
        Tools.showToast(this, msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_next:
                LoginPasswordVerifyRequestBean bean = new LoginPasswordVerifyRequestBean();
                bean.setToken(ShareUtil.getValue("token", InputPasswordActivity.this));
                bean.setCustPwd(editPassword.getText().toString());
                verifyLoginPasswordPresenter.verifyPassword(bean);
                break;
        }
    }
}
