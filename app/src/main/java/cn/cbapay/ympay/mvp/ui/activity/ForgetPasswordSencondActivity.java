package cn.cbapay.ympay.mvp.ui.activity;

import android.content.Intent;
import android.graphics.Color;
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
import cn.cbapay.ympay.bean.FrogetPasswordSenRequestBean;
import cn.cbapay.ympay.mvp.presenter.ForgetPasswordSencondPresenter;
import cn.cbapay.ympay.mvp.presenter.impl.ForgetPasswordSencondPresenterImpl;
import cn.cbapay.ympay.mvp.ui.base.BaseActivity;

/**
 * Created by Administrator on 2016/9/20.
 */
public class ForgetPasswordSencondActivity extends BaseActivity implements ForgetPasswordSencondPresenter.ForgetPasswordSencondView{

    @InjectView(R.id.title)
    TextView tytle;
    @InjectView(R.id.et_new_password)
    EditText etNewPassword;
    @InjectView(R.id.iv_close)
    ImageView ivClose;
    @InjectView(R.id.yes)
    Button mYes;
    private ForgetPasswordSencondPresenter forgetPasswordSencondPresenter;

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            forgetPasswordSencondPresenter.passwordLenth(s.length());
            forgetPasswordSencondPresenter.showConfirmState();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private String randomCode;
    private String phoneNumber;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_forget_password_sencond;
    }

    @Override
    protected void initViews() {
        forgetPasswordSencondPresenter = new ForgetPasswordSencondPresenterImpl(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        tytle.setText("忘记密码");
        etNewPassword.addTextChangedListener(watcher);
        etNewPassword.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
        etNewPassword.setTransformationMethod(PasswordTransformationMethod
                .getInstance());
        Intent intent = getIntent();
        randomCode = intent.getStringExtra("randomCode");
        phoneNumber = intent.getStringExtra("phoneNumber");
    }

    @OnClick({R.id.back, R.id.iv_close, R.id.yes})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.iv_close:
                forgetPasswordSencondPresenter.showOrHidePassword();
                break;
            case R.id.yes:
                //判断密码数字字母混合
                Boolean aBoolean = forgetPasswordSencondPresenter.checkInputBeforeCode(etNewPassword.getText().toString());
                if(aBoolean){
                    //请求数据
                    System.out.println("sadasdasdasd");
                    FrogetPasswordSenRequestBean bean = new FrogetPasswordSenRequestBean();
                    bean.setCustLogin(phoneNumber);
                    bean.setNewPassWord(etNewPassword.getText().toString());
                    bean.setRandomCode(randomCode);
                    forgetPasswordSencondPresenter.requestPassword(bean);
                }else {
                    System.out.println("1111111111");
                }
                break;
        }
    }

    @Override
    public void showPassword() {
        etNewPassword.setTransformationMethod(HideReturnsTransformationMethod
                .getInstance());
        ivClose.setImageResource(R.mipmap.close);
    }

    @Override
    public void hidePassword() {
        etNewPassword.setTransformationMethod(PasswordTransformationMethod
                .getInstance());
        ivClose.setImageResource(R.mipmap.open);
    }

    @Override
    public void noConfirm() {
        mYes.setBackgroundResource(R.mipmap.register);
    }

    @Override
    public void yesConfirm() {
        mYes.setBackgroundResource(R.mipmap.register_available);
        mYes.setTextColor(Color.WHITE);
        mYes.setEnabled(true);
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void changeSuccess() {
        this.finish();
    }

}
