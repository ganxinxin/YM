package cn.cbapay.ympay.mvp.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.cbapay.ympay.R;
import cn.cbapay.ympay.bean.ChangeLoginPassWordBean;
import cn.cbapay.ympay.bean.FrogetPasswordSenRequestBean;
import cn.cbapay.ympay.mvp.presenter.ChangeLoginSecondPresenter;
import cn.cbapay.ympay.mvp.presenter.impl.ChangeLoginSecondPresenterImpl;
import cn.cbapay.ympay.mvp.ui.base.BaseActivity;
import cn.cbapay.ympay.utils.ActivityUtils;
import cn.cbapay.ympay.utils.ShareUtil;

/**
 * Created by gaowei on 2016/9/22.
 */
public class ChangeLoginPasswordSecondActivity extends BaseActivity implements ChangeLoginSecondPresenter.ChangeLoginSecondView{

    @InjectView(R.id.title)
    TextView tytle;
    @InjectView(R.id.et_password)
    EditText etPassword;
    @InjectView(R.id.iv_open)
    ImageView ivOpen;
    @InjectView(R.id.bt_ok)
    Button btOk;
    private ChangeLoginSecondPresenterImpl mChangeLoginSecondPresenter;
    private String randomCode;
    private String phoneNumber;

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mChangeLoginSecondPresenter.showPasswordLength(s.length());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    @Override
    protected int getLayoutResID() {
        return R.layout.activity_change_password_sencond;
    }

    @Override
    protected void initViews() {
        mChangeLoginSecondPresenter = new ChangeLoginSecondPresenterImpl(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        tytle.setText("修改登录密码");
        etPassword.addTextChangedListener(watcher);
        etPassword.setTransformationMethod(PasswordTransformationMethod
                .getInstance());
        etPassword.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
        Intent intent = getIntent();
        randomCode = intent.getStringExtra("randomCode");
        phoneNumber = intent.getStringExtra("phoneNumber");

    }

    @OnClick({R.id.back, R.id.iv_open, R.id.bt_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.iv_open:
                mChangeLoginSecondPresenter.showOrHidePassword();
                break;
            case R.id.bt_ok:
                mChangeLoginSecondPresenter.checkPassword(etPassword.getText().toString());
                //请求数据
                ChangeLoginPassWordBean bean = new ChangeLoginPassWordBean();
                bean.setCustLogin(phoneNumber);
                bean.setCustPwd(etPassword.getText().toString());
                bean.setRandomCode(randomCode);
                bean.setToken(ShareUtil.getValue("token",this));
                mChangeLoginSecondPresenter.requestPassword(bean);

                break;
        }
    }

    @Override
    public void yesSure() {
        btOk.setBackgroundResource(R.mipmap.register_available);
        btOk.setEnabled(true);
        btOk.setTextColor(Color.WHITE);



    }

    @Override
    public void noSure() {
        btOk.setBackgroundResource(R.mipmap.register);
        btOk.setEnabled(false);
    }

    @Override
    public void showErrorMsg(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPassword() {
        etPassword.setTransformationMethod(HideReturnsTransformationMethod
                .getInstance());
        ivOpen.setImageResource(R.mipmap.close);
    }

    @Override
    public void hidePassword() {
        etPassword.setTransformationMethod(PasswordTransformationMethod
                .getInstance());

        ivOpen.setImageResource(R.mipmap.open);
    }

    @Override
    public void changeSuccess() {
        this.finish();
        ShareUtil.setValue("token", "",this);
        ShareUtil.setValue("phoneNumber", "",this);
        ActivityUtils.getInstance().exit();
        openActivity(LoginActivity.class);
    }
}
